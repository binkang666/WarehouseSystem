import java.time.LocalDate;

public class Salesperson extends Person implements Comparable<Salesperson> {
    private static int salespersonID = 0;
    private double totalCommissionEarned;
    private double totalSales;
    private LocalDate startDate;
    private double commissionRate;

    public Salesperson(){
        this.totalCommissionEarned = 0;
        this.totalSales = 0;
        this.startDate = LocalDate.of(2020,12,11);
        this.commissionRate = 0;
        salespersonID++;

    }


    public Salesperson(String firstName, String lastName, String phone, String address,
                       double totalCommissionEarned, double totalSales, LocalDate startDate, double commissionRate) {
        super(firstName, lastName, phone, address);
        this.totalCommissionEarned =totalCommissionEarned;
        this.totalSales = totalSales;
        this.startDate = startDate;
        this.commissionRate = commissionRate;
        salespersonID++;
    }
    @Override
    public String getType() {
        return "salesPerson";
    }
    @Override
    public String toString()
    {
        return " (Salesperson ID), Type: "+ getType() +" "+ super.toString() +  ", Start Date: " + getStartDate() +
                ", Commission Rate: " + getCommissionRate() + ", Commission Earned: "+ getTotalCommissionEarned()
                +", Total sales: " + getTotalSales();
    }

  /*  public String showAllPerformances(){
        return " (Salesperson ID), " + super.getName() +", Commission Earned: "+ getTotalCommissionEarned() +
                ", Total sales: " + getTotalSales();
    }*/

    //getter - Boundary
    public double getTotalCommissionEarned() { return totalCommissionEarned; }
    public double getTotalSales() { return totalSales; }
    public LocalDate getStartDate() { return startDate; }
    public double getCommissionRate() { return commissionRate; }
    public int getSalespersonID(){return salespersonID;}
    //setter - Controller
    public void setTotalCommissionEarned(double totalCommissionEarned) { this.totalCommissionEarned = totalCommissionEarned; }
    public void setTotalSales(double totalSales) { this.totalSales = totalSales; }
    public void setStartDate(int yy, int mm, int dd) {
        this.startDate = LocalDate.of(yy,mm,dd); }
    public void setCommissionRate(double commissionRate) { this.commissionRate = commissionRate; }


    @Override
    public int compareTo(Salesperson o) {
        if(o == null)
            return 0;
        else
        {
            Salesperson s = (Salesperson)o;
            return (getName().compareTo(s.getName()));
        }
    }
}
