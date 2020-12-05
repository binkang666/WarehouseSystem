import java.util.ArrayList;
import java.util.Scanner;

public class InvoiceBoundary {

    InvoiceController invoiceController;

    public InvoiceBoundary(InvoiceController invoiceController) {
        this.invoiceController = invoiceController;
    }

    void showInvoiceUI() {
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
                if (Main.customers != null) {
                    System.out.println(Main.customers.size());
                    System.out.println("Select a customer to open an invoice for:");
                    for (int i = 1; i < Main.customers.size() + 1; i++) {
                        System.out.println(Main.customers.get(i).getName());
                    }
                    String key = sc.next();
//                    Customer customer = Main.getCustomers().get(key);
//                    System.out.println(customer.toString());
                    System.out.println("Enter the customer's shipping address: ");
                    String address = sc.next();

                    //TODO: ADD PRODUCTS TO INVOICE


                    System.out.println("""
                        Enter the delivery method:
                        D. Delivery
                        T. Take-out
                        """);
                    char delivery = sc.next().toLowerCase().charAt(0);
                    double deliveryCharge = 0;
                    if (delivery == 'd') {
                        System.out.println("Enter the delivery charge: ");
                        deliveryCharge = sc.nextDouble();
                    }
//                    invoiceController.openInvoice(customer, address, delivery, deliveryCharge);
                }
                else {
                    System.out.println("No customers are in the system.");
                }
            }
            case 2 -> invoiceController.closeInvoice();
            case 3 -> invoiceController.showOpenInvoices();
            case 4 -> invoiceController.showClosedInvoices();
            case 5 -> invoiceController.markShipped();
            default -> System.out.println("Going back");
        }
    }
}
