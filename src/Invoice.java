import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Invoice implements Serializable {
    private String cName;
    private ArrayList<Product> products;
    private int invoiceNumber;
    private int status; // 1 if active, 0 otherwise
    private String shippingAddress;
    private char deliveryMethod; // D if delivery, T otherwise
    private final double financeCharge = .2; // 2% finance charge if customer is late in paying
    private LocalDate orderDate;
    private double deliverCharge;
    private double totalCharge;
    private double finalTotal;
    private double salesTax;

    // If we're not delivering, deliver charge will be 0
    public Invoice(String cName, String shippingAddress, char deliveryMethod, double deliverCharge, double salesTax, int invoiceNumber, ArrayList<Product> products) {
        this.products = products;
        this.invoiceNumber = invoiceNumber;
        this.cName = cName;
        this.shippingAddress = shippingAddress;
        this.deliveryMethod = deliveryMethod;
        orderDate = LocalDate.now();
        this.salesTax = salesTax;
        for (Product p : products) {
            totalCharge += p.getSellingPrice();
        }
        // maybe create methods to apply tax and delivery charge since salespersons don't get payed based on tax
        this.deliverCharge = deliverCharge;
        finalTotal = (salesTax * totalCharge) + deliverCharge;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("Invoice Number: " + getInvoiceNumber() + "\n" +
                "Order Date: " + getOrderDate() + "\n" +
                "Customer: " + getCName() + "\n" +
                "Products: ");
                for (Product product : getProducts()) {
                    sb.append(product.getProductName());
                }
                sb.append("\nShipping address: " + getShippingAddress() + "\n" +
                        "Delivery method: " + getDeliveryMethod() + "\n" +
                        "Delivery Charge: " + getDeliverCharge() + "\n" +
                        "Sales tax: " + getSalesTax() + "\n" +
                        "Total: " + getTotalCharge() + "\n" +
                        "Final Total" + getFinalTotal() + "\n");
        return sb.toString();
    }

    public Integer getInvoiceNumber() {
        return invoiceNumber;
    }

    public String getCName() {
        return cName;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public char getDeliveryMethod() {
        return deliveryMethod;
    }

    public double getDeliverCharge() {
        return deliverCharge;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public double getTotalCharge() {
        return totalCharge;
    }

    public double getFinalTotal() {
        return finalTotal;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
