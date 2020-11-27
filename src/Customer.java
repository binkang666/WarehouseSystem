import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customer extends Person {

    private int customerID;
    private boolean isActive;
    private List<Invoice> orderHistory;
    private double salesTax;
    private LocalDate lastOrderDate;

    public Customer(){
        this.customerID = 0;
        this.isActive = false;
    }


    public Customer(String firstName, String lastName, String phone, String address,int customerID,
                    boolean isActive,  double salesTax) {
        super(firstName, lastName, phone, address);
        this.customerID = customerID;
        this.isActive = isActive;
        this.salesTax = salesTax;
        orderHistory = new ArrayList<>();
    }

    @Override
    public String getType() {
        return "customer";
    }
    @Override
    public String toString()
    {
        return "Type: "+ getType() + ", Customer ID: " + getCustomerID() + ", " + super.toString() +
                ", Status: " + isActive() + ", Order History: " + getOrderHistory() +
                ", Sales Tax: " + getSalesTax();
    }

    //getter - Boundary
    public int getCustomerID() { return customerID; }
    public boolean isActive() {
        LocalDate today = LocalDate.now();
        Period p = Period.between(lastOrderDate, today);
        long p2 = ChronoUnit.DAYS.between(lastOrderDate, today);
        return p2 <= 360;
    }
    public ArrayList<Invoice> getOrderHistory(){
        LocalDate today = LocalDate.now();
        return getOrderHistory(today);
    }
    public ArrayList<Invoice> getOrderHistory(LocalDate today) {
        ArrayList<Invoice> previousOrders = new ArrayList<>();
        for(Invoice i: orderHistory){
            if(ChronoUnit.DAYS.between(lastOrderDate, today) <= 0)
            previousOrders.add(i);
        }
        return previousOrders;
    }
    public double getSalesTax() { return salesTax; }

    //setter - Controller
    public void setCustomerID(int customerID) { this.customerID = customerID; }
    public void changeStatus(boolean status) {
        if(status = false)
            status = true;
        if(status = true)
            status = false;
    }
    public void setOrderHistory(ArrayList<Invoice> orderHistory) { this.orderHistory = orderHistory; }
    public void setSalesTax(double salesTax) { this.salesTax = salesTax; }
    public void setLastOrderDate(LocalDate lastOrderDate) {
        this.lastOrderDate = lastOrderDate;
    }
}
