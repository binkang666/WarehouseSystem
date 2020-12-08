import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

public class InvoiceController {

    InvoiceBoundary invoiceBoundary = new InvoiceBoundary(this);
    CustomerController customerController = new CustomerController();

    // Look at the ID value in the last line Invoice.txt and add 1 to it.
    public int findNextInvoiceNumber() {
        int max = 0;
        for (Customer c: Main.customers.values()) {
            for (Integer i: c.getInvoiceAssociated().keySet()) {
                if (i >= max) {
                    max = i;
                }
            }
        }
        if (max == 0) {
            return 1;
        }
        return max + 1;
    }

    // To open an invoice, place the invoices in the customers hashmap and save them
    public void openInvoice(Customer c, String address, char delivery, double deliveryCharge, int invoiceNumber, ArrayList<Product> products, boolean shipped) throws IOException {
        Invoice invoice = new Invoice(c.getName(), address, delivery, deliveryCharge, c.getSalesTax(), invoiceNumber, products, shipped);
        c.getInvoiceAssociated().put(invoiceNumber, invoice);
        customerController.modifyCustomer(c);
    }

    // To modify an invoice, just replace it in the customers hashmap and save them
    public void modifyInvoice(Invoice invoice, Customer c) throws IOException {
        c.getInvoiceAssociated().put(invoice.getInvoiceNumber(), invoice);
        customerController.modifyCustomer(c);
    }

    public void closeInvoice(Invoice invoice) {
        invoice.setStatus(!invoice.getStatus());
    }

    public void showOpenInvoices() {
        boolean flag = false;
        for (Customer c: Main.customers.values()) {
            for (Invoice i : c.getInvoiceAssociated().values()) {
                if (i.getStatus()) {
                    System.out.println(i.toString());
                    flag = true;
                    break; // break because only 1 invoice should should be open
                }
            }
        }
        if (!flag) {
            System.out.println("No open invoices exist!");
        }
    }

    public void showClosedInvoices() {
        boolean flag = false;
        for (Customer c: Main.customers.values()) {
            for (Invoice i : c.getInvoiceAssociated().values()) {
                if (!i.getStatus()) {
                    flag = true;
                    System.out.println(i.toString());
                }
            }
        }
        if (!flag) {
            System.out.println("No closed invoices exist!");
        }
    }

    public void showAllInvoices() {
        boolean flag = false;
        for (Customer c : Main.customers.values()) {
            for (Invoice i : c.getInvoiceAssociated().values()) {
                System.out.println(i.toString());
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("No open/closed invoices exist!");
        }
    }

    public void showUnshippedInvoices() {
        boolean flag = false;
        for (Customer c: Main.customers.values()) {
            for (Invoice i : c.getInvoiceAssociated().values()) {
                if(!i.isShipped()) {
                    System.out.println(i.toString());
                    flag = true;
                }
            }
        }
        if (!flag) {
            System.out.println("No unshipped invoices exist!");
        }
    }

    public void markShipped(Customer c, Invoice i) throws IOException {
        i.setShipped(true);
        c.getInvoiceAssociated().put(i.getInvoiceNumber(), i);
        customerController.modifyCustomer(c);
    }
    // mark shipped and remove items from warehouse

    public Boolean hasOpenInvoice(Customer c) {
        for (Invoice i : c.getInvoiceAssociated().values()) {
            if (i.getStatus()) {
                return true;
            }
        }
        return false;
    }

//    public void applyEarlyFinance() {
//
//    }

    public void applyLateFinance() throws IOException {
        for (Customer c : Main.customers.values()) {
            for (Invoice i : c.getInvoiceAssociated().values()) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date()); // sets time to now

//                cal.add(Calendar.DAY_OF_MONTH, 200); //test late invoice
                if (cal.getTime().after(i.getCurrentLateDate())) {
                    Calendar cal2 = Calendar.getInstance();
                    cal2.setTime(i.getCurrentLateDate());
                    cal2.add(Calendar.DAY_OF_MONTH, 30);
                    i.setCurrentLateDate(cal2.getTime()); // sets late date to +30 days its previous date
                    i.setFinanceLateCharge(i.getFinanceLateCharge() + .02); // financial charge increments by 2%
                    i.setRemainingTotal((i.getFinalTotal().multiply(BigDecimal.valueOf(i.getFinanceLateCharge())).add(i.getRemainingTotal())).setScale(2, RoundingMode.HALF_UP));
                    System.out.println("new late" + i.getCurrentLateDate());
                    modifyInvoice(i, c);
                }
            }
        }
    }
}
