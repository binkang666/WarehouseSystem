import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Invoice implements Serializable {
    private Calendar cal1 = Calendar.getInstance();
    private Calendar cal2 = Calendar.getInstance();
    private final DecimalFormat df = new DecimalFormat("#.##");
    private String cName;
    private ArrayList<Product> products;
    private int invoiceNumber;
    private boolean status; // 1 if active, 0 otherwise
    private String shippingAddress;
    private char deliveryMethod; // D if delivery, T otherwise
    private double financeLateCharge = 0; // 2% finance charge if customer is late in paying. Increments by .2 every 30 days
    private double financeEarlyCharge = 0; // always 0 or 1%
    private Date orderDate;

    private BigDecimal remainingTotal;

    private Date currentLateDate; // holds a value that is 30*n days after orderDate

    private BigDecimal deliverCharge;
    private BigDecimal totalCharge = new BigDecimal("0");
//    private double finalTotal;
    private BigDecimal finalTotal;
    private BigDecimal salesTax;

    // If we're not delivering, deliver charge will be 0
    public Invoice(String cName, String shippingAddress, char deliveryMethod, BigDecimal deliverCharge, BigDecimal salesTax, int invoiceNumber, ArrayList<Product> products) {
        this.products = products;
        this.invoiceNumber = invoiceNumber;
        this.cName = cName;
        this.shippingAddress = shippingAddress;
        this.deliveryMethod = deliveryMethod;

        orderDate = cal1.getTime();
        cal2.setTime(orderDate);
        cal2.add(Calendar.DAY_OF_MONTH, 30);
        currentLateDate = cal2.getTime();

        df.setRoundingMode(RoundingMode.DOWN);
        this.salesTax = salesTax;
        for (Product p : products) {
            totalCharge = totalCharge.add(BigDecimal.valueOf(p.getSellingPrice()));
        }
        status = true; // All invoices start as open
        // maybe create methods to apply tax and delivery charge since salespersons don't get payed based on tax
        this.deliverCharge = deliverCharge;
        // (totalCharge + ((salesTax * .01) * totalCharge)) + deliverCharge
        finalTotal = totalCharge.add((salesTax.multiply(BigDecimal.valueOf(.01))).multiply(totalCharge)).add(deliverCharge).setScale(2, RoundingMode.HALF_UP);
        remainingTotal = finalTotal; // Amount that's modified as user pays off the invoice
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
                        "Late Interest " + df.format((getFinanceLateCharge() * 100)) + "%\n" +
                        "Early Deduction " + df.format((getFinanceEarlyCharge() * 100)) + "%\n" +
                        "Sales tax: " + getSalesTax() + "%\n" +
                        "Final Total: $" + getFinalTotal() + "\n" +
                        "Remaining Total: $" + getRemainingTotal() + "\n");

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

    public BigDecimal getDeliverCharge() {
        return deliverCharge;
    }

    public BigDecimal getSalesTax() {
        return salesTax;
    }

    public BigDecimal getTotalCharge() {
        return totalCharge;
    }

    public BigDecimal getFinalTotal() {
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


    public Date getCurrentLateDate() {
        return currentLateDate;
    }

    public void setCurrentLateDate(Date currentLateDate) {
        this.currentLateDate = currentLateDate;
    }

    public void setFinanceLateCharge(double financeLateCharge) {
        this.financeLateCharge = financeLateCharge;
    }

    public double getFinanceLateCharge() {
        return financeLateCharge;
    }

    public BigDecimal getRemainingTotal() {
        return remainingTotal;
    }

    public void setRemainingTotal(BigDecimal remainingTotal) {
        this.remainingTotal = remainingTotal;
    }

    public void setFinalTotal(BigDecimal finalTotal) {
        this.finalTotal = finalTotal;
    }

    public double getFinanceEarlyCharge() {
        return financeEarlyCharge;
    }


    public void setFinanceEarlyCharge(double financeEarlyCharge) {
        this.financeEarlyCharge = financeEarlyCharge;
    }
}
