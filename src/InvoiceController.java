import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class InvoiceController {

    InvoiceBoundary invoiceBoundary = new InvoiceBoundary(this);
    CustomerController customerController = new CustomerController();

    // Look at the ID value in the last line Invoice.txt and add 1 to it.
    public int findNextInvoiceNumber() {
        // If the file doesn't exist, that implies it's the first customer
        if (!new File("Invoice.txt").exists()) {
            return 1;
        }
        int max = 0;
        for (Integer i : Main.invoices.keySet()) {
            if (i > max) {
                max = i;
            }
        }
        return max + 1;
    }

    public void openInvoice(Customer c, String address, char delivery, double deliveryCharge, int invoiceNumber, ArrayList<Product> products) throws IOException {
        Invoice invoice = new Invoice(c.getName(), address, delivery, deliveryCharge, c.getSalesTax(), invoiceNumber, products);
        // need to figure out if modifying a customer in Customer.txt overwrites the val associated with the key
        c.getInvoiceAssociated().add(invoice);
        customerController.modifyCustomer(c);
        Main.invoices.put(invoice.getInvoiceNumber(), invoice);

        FileOutputStream f = new FileOutputStream("Invoice.txt");
        ObjectOutputStream o = new ObjectOutputStream(f);

        o.writeObject(Main.customers);
        o.flush();
        f.close();
        o.close();
    }

    public void getInvoices() throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream("Invoice.txt");
        ObjectInputStream oi = new ObjectInputStream(fi);

        Main.invoices = (HashMap<Integer, Invoice>) oi.readObject();
        oi.close();
        fi.close();
    }

    public void closeInvoice() {
    }

    public void showOpenInvoices() throws IOException, ClassNotFoundException {
        customerController.getCustomers();
        for (Customer c: Main.customers.values()) {
            c.displayInvoiceAssociated();
        }

    }

    public void showClosedInvoices() {

    }

    public void markShipped() {
        // mark shipped and remove items from warehouse
    }

    public void displayInvoices() {
        for (Invoice i : Main.invoices.values()) {
            System.out.println(i.toString());
        }
    }
}
