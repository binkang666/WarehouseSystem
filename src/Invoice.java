import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Invoice implements Serializable {
    private Calendar cal = Calendar.getInstance();
    private final DecimalFormat df = new DecimalFormat("#.##");
    private String cName;
    private ArrayList<Product> products;
    private int invoiceNumber;
    private boolean status; // 1 if active, 0 otherwise
    private boolean shipped;
    private String shippingAddress;
    private char deliveryMethod; // D if delivery, T otherwise
    private final double financeCharge = .2; // 2% finance charge if customer is late in paying
    private Date orderDate;
    private double deliverCharge;
    private double totalCharge;
    private double finalTotal;
    private double salesTax;

    // If we're not delivering, deliver charge will be 0
    public Invoice(String cName, String shippingAddress, char deliveryMethod, double deliverCharge, double salesTax, int invoiceNumber, ArrayList<Product> products, boolean shipped) {
        this.products = products;
        this.invoiceNumber = invoiceNumber;
        this.cName = cName;
        this.shippingAddress = shippingAddress;
        this.deliveryMethod = deliveryMethod;
        orderDate = cal.getTime();
        this.salesTax = salesTax;
        for (Product p : products) {
            totalCharge += p.getSellingPrice();
        }
        status = true; // All invoices start as open
        // maybe create methods to apply tax and delivery charge since salespersons don't get payed based on tax
        this.deliverCharge = deliverCharge;
        // rounded 2 decimal places
        finalTotal = ((double)((int)((totalCharge + ((salesTax * .01) * totalCharge) + deliverCharge) * 100)))/100.0;
        this.shipped = shipped;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Invoice Number: " + getInvoiceNumber() + "\n" +
                "Order Date: " + getOrderDate() + "\n" +
                "Customer: " + getCName() + "\n" +
                "Status: " + getStatus() + "\n" +
                "Products: ");
                sb.append(getProducts().get(0).getProductName());
                for (int i =  1; i < getProducts().size(); i++) {
                    sb.append(", ").append(getProducts().get(i).getProductName());
                }
                sb.append("\nShipping address: " + getShippingAddress() + "\n" +
                        "Delivery method: " + getDeliveryMethod() + "\n" +
                        "Delivery Charge: $" + df.format(getDeliverCharge()) + "\n" +
                        "Total: $" + df.format(getTotalCharge()) + "\n" +
                        "Sales tax: " + getSalesTax() + "%\n" +
                        "Final Total: $" + df.format(getFinalTotal()) + "\n");
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

    public Date getOrderDate() {
        return orderDate;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    //apply finance charge?

    public void setFinalTotal(double finalTotal) {
        this.finalTotal = finalTotal;
    }

    public boolean isShipped() {
        return shipped;
    }

    public void setShipped(boolean shipped) {
        this.shipped = shipped;
    }
}
