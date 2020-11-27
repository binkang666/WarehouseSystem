import java.time.LocalDate;

public class Salesperson extends Person {
    private double totalCommissionEarned;
    private double totalSales;
    private LocalDate startDate;
    private double commissionRate;

    public Salesperson(){
        this.totalCommissionEarned = 0;
        this.totalSales = 0;
        this.startDate = LocalDate.of(2020,12,11);
        this.commissionRate = 0;

    }


    public Salesperson(String firstName, String lastName, String phone, String address,
                       double totalCommissionEarned, double totalSales, LocalDate startDate, double commissionRate) {
        super(firstName, lastName, phone, address);
        this.totalCommissionEarned =totalCommissionEarned;
        this.totalSales = totalSales;
        this.startDate = startDate;
        this.commissionRate = commissionRate;
    }
    @Override
    public String getType() {
        return "salesPerson";
    }
    @Override
    public String toString()
    {
        return "Type: "+ getType() + super.toString() + ", Commission Earned:" + getTotalCommissionEarned() +
                ", Total sales: " + getTotalSales() + ", Start Date: " + getStartDate() +
                ", Commission Rate: " + getCommissionRate();
    }

    //getter - Boundary
    public double getTotalCommissionEarned() { return totalCommissionEarned; }
    public double getTotalSales() { return totalSales; }
    public LocalDate getStartDate() { return startDate; }
    public double getCommissionRate() { return commissionRate; }

    //setter - Controller
    public void setTotalCommissionEarned(double totalCommissionEarned) { this.totalCommissionEarned = totalCommissionEarned; }
    public void setTotalSales(double totalSales) { this.totalSales = totalSales; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setCommissionRate(double commissionRate) { this.commissionRate = commissionRate; }
}
