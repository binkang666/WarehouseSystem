import java.util.List;
import java.util.Map;

public class Customer extends Person {

    private int customerID;
    private boolean isActive;
    private Map<Integer, String> orderHistory;
    private double salesTax;

    public Customer(){
        this.customerID = 0;
        this.isActive = false;
    }


    public Customer(String firstName, String lastName, String phone, String address,int customerID,
                    boolean isActive, Map<Integer, String> orderHistory, double salesTax) {
        super(firstName, lastName, phone, address);
        this.customerID = customerID;
        this.isActive = isActive;
        this.orderHistory = orderHistory;
        this.salesTax = salesTax;
    }

    @Override
    public String getType() {
        return "customer";
    }
    @Override
    public String toString()
    {
        return "Type: "+ getType() + ", Customer ID: " + getCustomerID() + super.toString() +
                ", Status: " + isActive() + ", Order History: " + getOrderHistory() +
                ", Sales Tax: " + getSalesTax();
    }

    //getter
    public int getCustomerID() { return customerID; }
    public boolean isActive() { return isActive; }
    public Map<Integer, String> getOrderHistory() { return orderHistory; }
    public double getSalesTax() { return salesTax; }
    //setter
    public void setCustomerID(int customerID) { this.customerID = customerID; }
    public void setActive(boolean active) { isActive = active; }
    public void setOrderHistory(Map<Integer, String> orderHistory) { this.orderHistory = orderHistory; }
    public void setSalesTax(double salesTax) { this.salesTax = salesTax; }

    public void changeStatus(boolean status){
    }
}
