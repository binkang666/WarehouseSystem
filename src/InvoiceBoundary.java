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
                2. Pay off an invoice
                3. Show open invoices
                4. Show closed invoices
                5. Show all invoices
                6. Mark an invoice shipped
                7. Go back
                """);
        int input = sc.nextInt();
        switch (input) {
            // Open Invoice
            case 1 -> {
                // Load in existing customers
                if (new File("Customer.txt").exists()) {
                    customerController = new CustomerController();
                    customerController.getCustomers();
                    customerController.displayCustomers();

                    System.out.println("Enter the customer ID you would like to open an invoice for: ");
                    int key = sc.nextInt();
                    Customer customer;

                    // Check to see of customer exists return to main menu if they don't.
                    if (!Main.customers.containsKey(key)) {
                        System.out.println("Customer doesn't exist!");
                        break;
                    }

                    customer = Main.customers.get(key);

                    // Check if the customer has an open invoice
                    if (invoiceController.hasOpenInvoice(customer)) {
                        System.out.println("Customer already has an open invoice!");
                        break;
                    }

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
                        T. Take-out""");
                    char delivery = sc.next().toLowerCase().charAt(0);

                    double deliveryCharge = 0; // Will be 0 if the delivery method is T
                    boolean shipped = true;
                    if (delivery == 'd') {
                        shipped = false;
                        System.out.println("Enter the delivery charge: ");
                        deliveryCharge = sc.nextDouble();
                    }

                    int invoiceNumber = invoiceController.findNextInvoiceNumber();

                    invoiceController.openInvoice(customer, address, delivery, deliveryCharge, invoiceNumber, products, shipped);
                    System.out.println("New invoice added.");
                }

                else {
                    System.out.println("No customers exist!");
                }
            }
            case 2 -> {
                if (new File("Customer.txt").exists()) {
                    customerController = new CustomerController();
                    customerController.getCustomers();

                    System.out.println("Enter the invoice number you want to pay off: ");
                    invoiceController.showOpenInvoices();
                    int key = sc.nextInt();
                    Invoice invoice = null;
                    Customer customer = null;

                    try {
                        for (Customer c: Main.customers.values()) {
                            if (c.getInvoiceAssociated().containsKey(key)) {
                                customer = c;
                                invoice = c.getInvoiceAssociated().get(key);

                            }
                        }
                    }
                    catch (NullPointerException n) {
                        System.out.println("Invoice doesn't exist!");
                        break;
                    }


                    System.out.println("Enter amount to pay off: ");
                    double amount = sc.nextDouble();
                    while (amount > invoice.getFinalTotal()) {
                        System.out.println("Payment cannot be greater than amount owed. Re-enter: ");
                        amount = sc.nextDouble();
                    }
                    invoice.setFinalTotal(invoice.getFinalTotal() - amount);

                    // Check if the invoice has been fully payed off
                    if (invoice.getFinalTotal() == 0) {
                        System.out.println("Invoice has been closed.");
                        invoiceController.closeInvoice(invoice);
                    }

                    // Overwrite the existing invoice int the customers hashmap
                    invoiceController.modifyInvoice(invoice, customer);

                    // Ive modified an invoice. I need to write it back into the invoice file AND i need to modify the customers arraylist
                }
                else {
                    System.out.println("Either no open invoices or customers exist!");
                }

            }
            case 3 -> {
                if (new File("Customer.txt").exists()) {
                    customerController = new CustomerController();
                    customerController.getCustomers();
                    invoiceController.showOpenInvoices();
                }
            }
            case 4 -> {
                if (new File("Customer.txt").exists()) {
                    customerController = new CustomerController();
                    customerController.getCustomers();
                    invoiceController.showClosedInvoices();
                }
            }
            case 5 -> {
                if (new File("Customer.txt").exists()) {
                    customerController = new CustomerController();
                    customerController.getCustomers();
                    invoiceController.showAllInvoices();
                }
            }

            case 6 -> {
                if (new File("Customer.txt").exists()) {
                    customerController = new CustomerController();
                    customerController.getCustomers();

                    invoiceController.showUnshippedInvoices();
                    System.out.println("Enter the invoice number you want to mark shipped: ");
                    int key = sc.nextInt();

                    for (Customer c: Main.customers.values()) {
                        if (c.getInvoiceAssociated().containsKey(key)) {
                            Invoice i = c.getInvoiceAssociated().get(key);
                            invoiceController.markShipped(c, i);
                        }
                    }

                    System.out.println("Invoice marked shipped.");
                }
            }
            default -> System.out.println("Going back");
        }
    }
}
