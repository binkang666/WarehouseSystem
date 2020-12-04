
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer extends Person implements Comparable<Customer> {

    private static int customerID = 0;
    private int status;
    private ArrayList<Invoice> InvoiceAssociated;
    private double salesTax;
    private LocalDate lastOrderDate;

    public Customer(){
        customerID++;
        status= 1;
    }


    public Customer(String firstName, String lastName, String phone, String address, double salesTax) {
        super(firstName, lastName, phone, address);
        customerID++;
        status = 1;
        this.salesTax = salesTax;
    }

    public void addInvoiceAssociated(Invoice ia){
        InvoiceAssociated.add(ia);
    }

    public void getInvoiceAssociated(){
        for(Invoice invoice: InvoiceAssociated){
            System.out.println(invoice);
        }
    }

    public void changeStatus() {
        if(status == 0){
            status = 1;
            System.out.println("the customer is now marked active");}
        else if (status == 1){
            status = 0;
            System.out.println("the customer is now marked inactive");}
    }
    public int getCustomerID() { return Customer.customerID; }
    public int getStatus() { return status; }
    public double getSalesTax() { return salesTax; }
    public void setSalesTax(double salesTax) { this.salesTax = salesTax; }


    @Override
    public String getType() {
        return "customer";
    }
    @Override
    public String toString()
    {
        return "Type: "+ getType() + ", Customer ID: " + getCustomerID() + ", " + super.toString() +
                ", Status: " + getStatus()  + ", Sales Tax: " + getSalesTax();
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
