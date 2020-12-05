import java.time.LocalDate;
import java.util.ArrayList;

public class Invoice {
    private ArrayList<Product> products;
    private static Integer invoiceNumber = 0; // invoice increments by 1 each time one is created
    private int status; // 1 if active, 0 otherwise
    private String shippingAddress;
    private char deliveryMethod; // D if delivery, T otherwise
    private final double financeCharge = .2; // 2% finance charge if customer is late in paying
    private LocalDate orderDate;
    private double deliverCharge;
    private double totalCharge;
    private double finalTotal;
    private Customer customer;
    private double salesTax;

    // If we're not delivering, deliver charge will be 0
    public Invoice(Customer customer, String shippingAddress, char deliveryMethod, double deliverCharge) {
        invoiceNumber++;
        this.customer = customer;
        this.shippingAddress = shippingAddress;
        this.deliveryMethod = deliveryMethod;
        orderDate = LocalDate.now();
        salesTax = customer.getSalesTax();
        for (Product p : products) {
            totalCharge += p.getSellingPrice();
        }
        // maybe create methods to apply tax and delivery charge since salespersons don't get payed based on tax
        this.deliverCharge = deliverCharge;
        finalTotal = (salesTax * totalCharge) + deliverCharge;
    }

    @Override
    public String toString() {
        return  "Invoice Number: " + invoiceNumber + "\n" +
                "Customer: " + customer.getName() + "\n" +
                "Shipping address: " + shippingAddress + "\n" +
                "Delivery method: " + deliveryMethod + "\n" +
                "Delivery Charge: " + deliverCharge + "\n" +
                "Sales tax: " + customer.getSalesTax() + "\n" +
                "Total: " + totalCharge + "\n" +
                "Final Total" + finalTotal + "\n";
    }

    public Integer getInvoiceNumber() {
        return invoiceNumber;
    }
}
