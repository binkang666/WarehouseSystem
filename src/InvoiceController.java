import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class InvoiceController {

    InvoiceBoundary invoiceBoundary = new InvoiceBoundary(this);
    CustomerController customerController = new CustomerController();

    // Look at the ID value in the last line Invoice.txt and add 1 to it.
    public int findNextInvoiceNumber() {
        int max = 1;
        for (Customer c: Main.customers.values()) {
            for (Integer i: c.getInvoiceAssociated().keySet()) {
                if (i > max) {
                    max = i;
                }
            }
        }
        return max;
    }

    // To open an invoice, place the invoices in the customers hashmap and save them
    public void openInvoice(Customer c, String address, char delivery, double deliveryCharge, int invoiceNumber, ArrayList<Product> products) throws IOException {
        Invoice invoice = new Invoice(c.getName() , address, delivery, deliveryCharge, c.getSalesTax(), invoiceNumber, products);
        c.getInvoiceAssociated().put(invoiceNumber, invoice);
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
        for (Customer c: Main.customers.values()) {
            for (Invoice i : c.getInvoiceAssociated().values()) {
                if (i.getStatus()) {
                    System.out.println(i.toString());
                    break; // break because only 1 invoice should should be open
                }
            }
        }
    }
    // To modify an invoice, just replace it in the customers hashmap and save them
    public void modifyInvoice(Invoice invoice, Customer c) throws IOException {
        c.getInvoiceAssociated().put(invoice.getInvoiceNumber(), invoice);
        customerController.modifyCustomer(c);
    }

    public void showClosedInvoices() {
        for (Customer c: Main.customers.values()) {
            for (Invoice i : c.getInvoiceAssociated().values()) {
                if (!i.getStatus()) {
                    System.out.println(i.toString());
                }
            }
        }
    }

    public void markShipped() {
        // mark shipped and remove items from warehouse
    }

    public void payInvoice() {


    }

    public void showAllInvoices() {
        for (Customer c: Main.customers.values()) {
            for (Invoice i : c.getInvoiceAssociated().values()) {
                System.out.println(i.toString());
            }
        }
    }
}
