import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customer extends Person {

    private static int customerID;
    private boolean isActive;
    private String orderHistory;
    private double salesTax;
    private LocalDate lastOrderDate;

    public Customer(){
        customerID ++;
        this.isActive = true;
    }


    public Customer(String firstName, String lastName, String phone, String address, double salesTax) {
        super(firstName, lastName, phone, address);
        customerID++;
        this.isActive = true;
        this.salesTax = salesTax;
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
        return isActive;
    }
    public String getOrderHistory() {
        return orderHistory;
    }
    public double getSalesTax() { return salesTax; }

    //setter - Controller
    public void setCustomerID(int customerID) { this.customerID = customerID; }
    public void changeActiveStatus() {
        if(isActive = false)
            isActive = true;
    }
    public void changeNonactiveStatus(){
        if(isActive = true)
            isActive = false;
    }
    public void setOrderHistory(String orderHistory) { this.orderHistory = orderHistory; }
    public void setSalesTax(double salesTax) { this.salesTax = salesTax; }
    public void setLastOrderDate(LocalDate lastOrderDate) {
        this.lastOrderDate = lastOrderDate;
    }
}
