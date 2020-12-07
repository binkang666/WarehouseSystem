import java.io.*;
import java.util.ArrayList;

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


//    public void getInvoices() throws IOException, ClassNotFoundException {
//        FileInputStream fi = new FileInputStream("Invoice.txt");
//        ObjectInputStream oi = new ObjectInputStream(fi);
//
//        Main.invoices = (HashMap<Integer, Invoice>) oi.readObject();
//        oi.close();
//        fi.close();
//    }

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
}
