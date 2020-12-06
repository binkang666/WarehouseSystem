import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Customer extends Person implements Comparable<Customer>, Serializable {
    private int customerID;
    private boolean status;
    private ArrayList<Invoice> InvoiceAssociated;
    private double salesTax;
    private LocalDate lastOrderDate;

    public Customer(){
        this.customerID = 0;
        status= true;
        lastOrderDate = null;
    }


    public Customer(String firstName, String lastName, String phone, String address, double salesTax, int customerID) {
        super(firstName, lastName, phone, address);
        this.customerID = customerID;
        status = true;
        this.salesTax = salesTax;
        lastOrderDate = null;
        InvoiceAssociated = new ArrayList<>();
    }

    public void addInvoiceAssociated(Invoice ia){
        InvoiceAssociated.add(ia);
    }

    public ArrayList<Invoice> getInvoiceAssociated(){
        return InvoiceAssociated;
    }

    public void displayInvoiceAssociated() {
        for(Invoice invoice: InvoiceAssociated){
            System.out.println(invoice);
        }
    }

    public boolean isActive(){
        if(lastOrderDate == null){
            return status = true; //active
        }
        else {
            LocalDate today = LocalDate.now();
            long p = ChronoUnit.DAYS.between(lastOrderDate, today);
            if(p < 365){
                return status = true; //active
            }
            else
                return status = false; //inactive, last order more than 365 days
        }

    }
    public void changeStatus() {
        if(status = false){
            status = true;
            System.out.println("the customer is now marked active");}
        else if (status = true){
            status = false;
            System.out.println("the customer is now marked inactive");}
    }
    public int getCustomerID() { return customerID; }
    public boolean getStatus() { return status; }
    public double getSalesTax() { return salesTax; }
    public void setSalesTax(double salesTax) { this.salesTax = salesTax; }


    @Override
    public String getType() {
        return "customer";
    }
    @Override
    public String toString()
    {
        return "Customer ID: " + getCustomerID() + ", Type: "+ getType() + ", " + super.toString() +
                ", Status: " + isActive()  + ", Sales Tax: " + getSalesTax();
    }

    @Override
    public int compareTo(Customer o)
    {
        if(o == null)
            return 0;
        else
            return (getName().compareTo(o.getName()));
    }

}
