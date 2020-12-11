import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;
public class InvoiceBoundary {

    InvoiceController invoiceController;
    CustomerController customerController;
    SalespersonController salespersonController;
    WarehouseController warehouseController;

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
                6. Go back""");
        int input = sc.nextInt();

        // Apply 30 day late fees to any applicable invoices
        if (new File("Customer.txt").exists()) {
            customerController = new CustomerController();
            customerController.getCustomers();
        }
        invoiceController.applyLateFinance();

        switch (input) {
            // OPEN INVOICE
            case 1 -> {
                // Load in existing customers, warehouses, and salespersons
                if (new File("Customer.txt").exists() && new File("Warehouse.txt").exists() && new File("Salesperson.txt").exists()) {
                    customerController = new CustomerController();
                    salespersonController = new SalespersonController();
                    warehouseController = new WarehouseController();
                    warehouseController.getWarehouses();
                    salespersonController.getSalespersons();


                    System.out.println("**********************Employees*********************");
                    customerController.displayCustomers();
                    System.out.println("\nEnter the customer ID you want to open an invoice for customer ID");

                    int key = invoiceController.getValidInt(sc);
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

                    // Buffer flush
                    sc.nextLine();

                    // Get products user wants
                    ArrayList<Product> products = new ArrayList<>();
                    System.out.println("**********************In-Stock Products*********************");
                    boolean stockExists = warehouseController.displayInStockProducts();
                    if (stockExists) {
                        System.out.println("Type in the name of the item you want to add");
                        String productName = sc.next();
                        // If user doesn't add products, don't allow them to continue
                        while ((products.size() <= 0) || !productName.equals("-1")) {
                            // search through warehouses to see if item exists in them
                            if (warehouseController.productExists(productName)) {
                                int quantity;
                                int productQuantity = warehouseController.getQuantityForAllWarehouses(productName);
                                // Make sure product actually has quantity
                                if (productQuantity > 0) {
                                    do {
                                        System.out.println("Enter quantity");
                                        quantity = sc.nextInt();
                                    } while (quantity > productQuantity);

                                    // REMOVE
                                    Product p = warehouseController.removeProduct(productName, quantity);
                                    p.setQuantity(quantity);
                                    products.add(p);

                                    // Get warehouses again
                                    System.out.println("**********************In-Stock Products*********************");
                                    warehouseController.getWarehouses();
                                    warehouseController.displayInStockProducts();
                                    System.out.println("Product added, type in another product name or -1 to finish adding items");
                                } else {
                                    System.out.println("Product doesn't exist! Re-enter the exact name.");
                                }
                                productName = sc.next();
                            }
                        }
                    }
                    else {
                        System.out.println("No products have any stock! To add stock, select the \"Product\" from the main menu.");
                        break;
                    }
                    System.out.println("**********************Current Employees*********************");
                    salespersonController.displaySalespersons();
                    System.out.println("\nEnter the ID of the salesperson who is making this transaction:");
                    key = invoiceController.getValidInt(sc);
                    Salesperson salesperson;
                    if (!Main.customers.containsKey(key)) {
                        System.out.println("Salesperson doesn't exist!");
                        break;
                    }
                    salesperson = Main.salespeople.get(key);
                    // if a warehouse shares items and and the first doesnt run out, it sets the second warehouse's quantity to quantity for some reason
                    // Buffer for now
                    sc.nextLine();
                    System.out.println("""
                        Enter the delivery method:
                        D. Delivery
                        T. Take-out""");

                    // Validate
                    char delivery = sc.nextLine().toLowerCase().charAt(0);
                    while (!Character.toString(delivery).matches("^[dt]$")) {
                        System.out.println("Invalid, enter either d or t.");
                        delivery = sc.nextLine().toLowerCase().charAt(0);
                    }

                    BigDecimal deliveryCharge = BigDecimal.ZERO; // Will be 0 if the delivery method is T
                    String address = "N/A"; // will be N/A if the delivery method is T
                    if (delivery == 'd') {
                        System.out.println("Enter the customer's shipping address:");
                        address = sc.next();
                        System.out.println("Enter the delivery charge: ");
                        deliveryCharge = invoiceController.getBigDecimal(sc);
                    }

                    int invoiceNumber = invoiceController.findNextInvoiceNumber();

                    invoiceController.openInvoice(customer, salesperson, address, delivery, deliveryCharge, invoiceNumber, products);
                    System.out.println("New invoice added.");
                }

                else {
                    System.out.println("Either no customers, warehouses, or salespersons exist!");
                }
            }
            // PAYOFF INVOICE
            case 2 -> {
                if (new File("Customer.txt").exists()) {
                    customerController = new CustomerController();

                    System.out.println("*********************************************");
                    ArrayList<Invoice> open = invoiceController.getOpenInvoices();
                    if (open.size() == 0) {
                        System.out.println("No open invoices exist!");
                        break;
                    }
                    invoiceController.showOpenInvoices(open);
                    System.out.println("Enter the invoice number you want to pay off: ");
                    int key = invoiceController.getValidInt(sc);
                    Invoice invoice = null;
                    Customer customer = null;
                    try {
                        for (Customer c: Main.customers.values()) {
                            if (c.getInvoiceAssociated().containsKey(key) && (c.getInvoiceAssociated().get(key).getStatus())) {
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
                    BigDecimal amount = invoiceController.getBigDecimal(sc);
                    // removed set scale since amount and remaining total should always be setscale 2
                    while ((amount.compareTo(invoice.getRemainingTotal()) > 0)
                            && !(invoice.getRemainingTotal().compareTo(BigDecimal.valueOf(.01)) < 0)) {
                        System.out.println("Payment cannot be greater than amount owed. Re-enter: ");
                        amount = invoiceController.getBigDecimal(sc);
                    }

                    invoiceController.updateRemainingTotal(invoice, amount);

                    // Check if the invoice has been fully payed off
                    // if remaining total is less than .01, assume its closed
                    if (invoice.getRemainingTotal().compareTo(BigDecimal.valueOf(.01)) < 0) {
                        //TODO:REMOVE
                        System.out.println("Invoice has been closed.");
                        invoiceController.closeInvoice(invoice);


                        // Apply early discount, if applicable
                        invoiceController.applyEarlyFinance(invoice);
                    }
                    // Overwrite the existing invoice in the customers hashmap
                    invoiceController.modifyInvoice(invoice, customer);

                }
                else {
                    System.out.println("Either no open invoices or customers exist!");
                }

            }
            // SHOW OPEN INVOICES
            case 3 -> {
                System.out.println("**********************Open Invoices*********************");
                if (new File("Customer.txt").exists()) {
                    customerController = new CustomerController();
                    ArrayList<Invoice> open = invoiceController.getOpenInvoices();
                    if (open.size() == 0) {
                        System.out.println("No open invoices exist!");
                    }
                    else {
                        invoiceController.showOpenInvoices(open);
                    }
                }
                else System.out.println("No customers exist!");
            }
            // SHOW CLOSED INVOICES
            case 4 -> {
                System.out.println("**********************Closed Invoices*********************");
                if (new File("Customer.txt").exists()) {
                    customerController = new CustomerController();
                    ArrayList<Invoice> closed = invoiceController.getClosedInvoices();
                    if (closed.size() == 0) {
                        System.out.println("No closed invoices exist!");
                    }
                    else {
                        invoiceController.showClosedInvoices(closed);
                    }
                }
                else System.out.println("No customers exist!");
            }
            // SHOW ALL INVOICES
            case 5 -> {
                System.out.println("**********************All Invoices*********************");
                if (new File("Customer.txt").exists()) {
                    customerController = new CustomerController();
                    invoiceController.showAllInvoices();
                }
                else System.out.println("No customers exist!");
            }
        }
    }
}
