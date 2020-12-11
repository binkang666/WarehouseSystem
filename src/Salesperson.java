import java.math.BigDecimal;
import java.time.LocalDate;

public class Salesperson extends Person implements Comparable<Salesperson> {
    private final int salespersonID;
    private BigDecimal totalCommissionEarned;
    private BigDecimal totalSales;
    private LocalDate startDate;
    private BigDecimal commissionRate;

    public Salesperson(String firstName, String lastName, String phone, String address, LocalDate startDate,
                       BigDecimal commissionRate, int salespersonID) {
        super(firstName, lastName, phone, address);
        // Salespersons should start with no sales or commission earned
        totalCommissionEarned = BigDecimal.ZERO;
        totalSales = BigDecimal.ZERO;
        this.startDate = startDate;
        this.commissionRate = commissionRate;
        this.salespersonID = salespersonID;
    }
    @Override
    public String getType() {
        return "salesPerson";
    }
    @Override
    public String toString()
    {
        return "Salesperson ID: " + getSalespersonID() +", Type: " + getType() +", "+ super.toString() +  ", Start Date: " + getStartDate() +
                ", Commission Rate: " + getCommissionRate() + "%" + ", Commission Earned: $" + getTotalCommissionEarned()
                + ", Total sales: $" + getTotalSales();
    }

    //getter - Boundary
    public BigDecimal getTotalCommissionEarned() { return totalCommissionEarned; }
    public BigDecimal getTotalSales() { return totalSales; }
    public LocalDate getStartDate() { return startDate; }
    public BigDecimal getCommissionRate() { return commissionRate; }
    public int getSalespersonID(){return salespersonID;}
    //setter - Controller
    public void setTotalCommissionEarned(BigDecimal totalCommissionEarned) { this.totalCommissionEarned = totalCommissionEarned; }
    public void setTotalSales(BigDecimal totalSales) { this.totalSales = totalSales; }

    public void setCommissionRate(BigDecimal commissionRate) { this.commissionRate = commissionRate; }

    @Override
    public int compareTo(Salesperson o) {
        if(o == null)
            return 0;
        else
        {
            return (getName().compareTo(o.getName()));
        }
    }
}
