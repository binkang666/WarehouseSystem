import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

public class SalespersonController {
    SalespersonBoundary salespersonBoundary = new SalespersonBoundary(this);
    Salesperson salesperson;

    public int findNextSalespersonID() {
        // If the file doesn't exist, that implies it's the first salesperson
        if (!new File("Salesperson.txt").exists()) {
            return 1;
        }
        int max = 0;
        for (Integer i : Main.salespeople.keySet()) {
            if (i > max) {
                max = i;
            }
        }
        return max + 1;
    }

    public void writeSalespersons(String fn, String ln, String phone, String add, LocalDate date, BigDecimal rate, int id) throws IOException {
        salesperson = new Salesperson(fn, ln, phone, add, date, rate, id);
        Main.salespeople.put(id, salesperson);

        FileOutputStream f = new FileOutputStream("Salesperson.txt");
        ObjectOutputStream o = new ObjectOutputStream(f);

        o.writeObject(Main.salespeople);
        o.flush();
        f.close();
        o.close();
    }

    // Modifies a salesperson's existing fields
    public void modifySalespersons(Salesperson salesperson) throws IOException {
        Main.salespeople.put(salesperson.getSalespersonID(), salesperson);
        FileOutputStream f = new FileOutputStream("Salesperson.txt");
        ObjectOutputStream o = new ObjectOutputStream(f);

        o.writeObject(Main.salespeople);
        o.flush();
        f.close();
        o.close();
    }

    // Loads the hashmap in salesperson.txt and places it in the hashtable in the Main
    public void getSalespersons() throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream("Salesperson.txt");
        ObjectInputStream oi = new ObjectInputStream(fi);

        Main.salespeople = (HashMap<Integer, Salesperson>) oi.readObject();
        oi.close();
        fi.close();
    }

    public void displaySalespersons() {
        for (Salesperson sp : Main.salespeople.values()) {
            System.out.println(sp.toString());
        }
    }

    public void searchSalespersonID(int id)  {
        try{
            System.out.println(Main.salespeople.get(id).toString());}
        catch (Exception e){
            System.out.println("No salesperson with given ID was found");
        }
    }

    public void payCommission(Salesperson salesperson, BigDecimal total) {
        salesperson.setTotalSales(salesperson.getTotalSales().add(total));
        salesperson.setTotalCommissionEarned(salesperson.getTotalCommissionEarned().add
                ((total.multiply((salesperson.getCommissionRate().multiply(BigDecimal.valueOf(.01)))))).setScale(2, RoundingMode.HALF_UP));
    }

    public void setRate(Salesperson sp) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the commission rate for the salesperson");
        BigDecimal choice = input.nextBigDecimal();
        sp.setCommissionRate(choice);
        if(Main.salespeople.containsKey(sp.getSalespersonID())){
            modifySalespersons(sp);
        }
    }

}
