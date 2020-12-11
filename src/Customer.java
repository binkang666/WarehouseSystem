import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class Customer extends Person implements Comparable<Customer>, Serializable {
    private int customerID;
    private boolean status;
    private HashMap<Integer, Invoice> InvoiceAssociated;
    private BigDecimal salesTax;


    public Customer(String firstName, String lastName, String phone, String address, BigDecimal salesTax, int customerID) {
        super(firstName, lastName, phone, address);
        this.customerID = customerID;
        status = true;
        this.salesTax = salesTax;
        InvoiceAssociated = new HashMap<>();
    }

    public HashMap<Integer, Invoice> getInvoiceAssociated(){
        return InvoiceAssociated;
    }

    public int getCustomerID() { return customerID; }
    public boolean getStatus() { return status; }
    public BigDecimal getSalesTax() { return salesTax; }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String getType() {
        return "Customer";
    }
    @Override
    public String toString()
    {
        return "Customer ID: " + getCustomerID() + ", Type: "+ getType() + ", " + super.toString() +
                ", Status: " + getStatus()  + ", Sales Tax: " + getSalesTax() + "%";
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
