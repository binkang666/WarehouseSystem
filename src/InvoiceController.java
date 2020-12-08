import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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
    public void openInvoice(Customer c, String address, char delivery, BigDecimal deliveryCharge, int invoiceNumber, ArrayList<Product> products) throws IOException {
        Invoice invoice = new Invoice(c.getName(), address, delivery, deliveryCharge, c.getSalesTax(), invoiceNumber, products);
        c.getInvoiceAssociated().put(invoiceNumber, invoice);
        customerController.modifyCustomer(c);
    }

    // To modify an invoice, just replace it in the customers hashmap and save them
    public void modifyInvoice(Invoice invoice, Customer c) throws IOException {
        c.getInvoiceAssociated().put(invoice.getInvoiceNumber(), invoice);
        customerController.modifyCustomer(c);
    }

    public void updateRemainingTotal(Invoice invoice, double amount) {
        invoice.setRemainingTotal(invoice.getRemainingTotal().subtract(BigDecimal.valueOf(amount)));
    }

    public void closeInvoice(Invoice invoice) {
        invoice.setStatus(!invoice.getStatus());
    }

    public ArrayList<Invoice> getOpenInvoices() {
        ArrayList<Invoice> open = new ArrayList<>();
        for (Customer c: Main.customers.values()) {
            for (Invoice i : c.getInvoiceAssociated().values()) {
                if (i.getStatus()) {
                    open.add(i);
                    break; // break because only 1 invoice should should be open
                }
            }
        }
        // Lambda expression to sort invoices by ascending date
        open.sort((o1, o2) -> o1.getOrderDate().compareTo(o2.getOrderDate()));
        return open;
    }

    // Sort by ascending date
    public void showOpenInvoices(ArrayList<Invoice> open) {
        for (Invoice i : open) {
            System.out.println(i.toString());
        }
    }

    public ArrayList<Invoice> getClosedInvoices() {
        ArrayList<Invoice> closed = new ArrayList<>();
        for (Customer c: Main.customers.values()) {
            for (Invoice i : c.getInvoiceAssociated().values()) {
                if (!i.getStatus()) {
                    closed.add(i);
                }
            }
        }
        // Lambda expression to sort invoices by descending price
        closed.sort((o1, o2) -> o2.getFinalTotal().compareTo(o1.getFinalTotal()));
        return closed;
    }

    // Sort by descending amount paid
    public void showClosedInvoices(ArrayList<Invoice> closed) {
        for (Invoice i : closed) {
            System.out.println(i.toString());
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

    public Boolean hasOpenInvoice(Customer c) {
        for (Invoice i : c.getInvoiceAssociated().values()) {
            if (i.getStatus()) {
                return true;
            }
        }
        return false;
    }

    // Applies a 10% invoice reduction if the invoice is paid within 10 days
    public void applyEarlyFinance(Invoice i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date()); // sets time to now
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(i.getOrderDate());
        cal2.add(Calendar.DAY_OF_MONTH, 10);
        // if order date + 10 days is before current time.
        if (cal.getTime().before(cal2.getTime())) {
            i.setFinanceEarlyCharge(.1);
            i.setFinalTotal(i.getFinalTotal().subtract((i.getFinalTotal()).multiply(BigDecimal.valueOf(i.getFinanceEarlyCharge()))).setScale(2, RoundingMode.HALF_UP));

        }

    }
    // Applies a 2% late fee every time a product is 30 days late
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
                    modifyInvoice(i, c);
                }
            }
        }
    }
}
