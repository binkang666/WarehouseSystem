import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class InvoiceBoundary {

    InvoiceController invoiceController;
    CustomerController customerController;

    public InvoiceBoundary(InvoiceController invoiceController) {
        this.invoiceController = invoiceController;
    }

    void showInvoiceUI() throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println(""" 
                Select an option:
                1. Open an invoice
                2. Close an invoice
                3. Show open invoices
                4. Show closed invoices
                5. Mark an invoice shipped
                6. Pay off an invoice?
                7. Go back
                """);
        int input = sc.nextInt();
        switch (input) {
            // Open Invoice
            case 1 -> {
                customerController = new CustomerController();
                if (new File("Customer.txt").exists()) {
                    customerController.getCustomers();
                    customerController.displayCustomers();
                }
                // TODO: MAKE SURE CUSTOMER DOESN'T HAVE AN ACTIVE INVOICE
                System.out.println("Enter the customer ID you would like to open an invoice for: ");
                int key = sc.nextInt();
                Customer customer = Main.customers.get(key);

                //TODO: LOOK AT PRODUCTS IN EACH WAREHOUSE AND PRINT THEM
                ArrayList<Product> products = new ArrayList<>();
                System.out.println("Adding generic items...");
                products.add(new Product("Apple", 2, 3, 0, 0));
                products.add(new Product("Banana", 1, 5, 0, 0));


                System.out.println("Enter the customer's shipping address: ");
                String address = sc.next();

                System.out.println("""
                        Enter the delivery method:
                        D. Delivery
                        T. Take-out
                        """);
                char delivery = sc.next().toLowerCase().charAt(0);

                double deliveryCharge = 0; // Will be 0 if the delivery method is T
                if (delivery == 'd') {
                    System.out.println("Enter the delivery charge: ");
                    deliveryCharge = sc.nextDouble();
                }

                int invoiceNumber = invoiceController.findNextInvoiceNumber();

                if (new File("Invoice.txt").exists()) {
                    invoiceController.getInvoices();
                }
                invoiceController.openInvoice(customer, address, delivery, deliveryCharge, invoiceNumber, products);
                System.out.println("New invoice added.");

            }
            case 2 -> invoiceController.closeInvoice();
            case 3 -> invoiceController.showOpenInvoices();
            case 4 -> invoiceController.showClosedInvoices();
            case 5 -> invoiceController.markShipped();
            default -> System.out.println("Going back");
        }
    }
}
