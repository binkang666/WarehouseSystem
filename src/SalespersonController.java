import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;

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

    public void writeSalesperson(String fn, String ln, String phone, String add,
                                 double commission, double sales,
                                 LocalDate date, double rate, int id) throws IOException {
        salesperson = new Salesperson(fn, ln, phone, add, commission, sales, date, rate, id);
        Main.salespeople.put(id, salesperson);

        FileOutputStream f = new FileOutputStream("Salesperson.txt");
        ObjectOutputStream o = new ObjectOutputStream(f);

        o.writeObject(Main.salespeople);
        o.flush();
        f.close();
        o.close();
    }

    // Modifies a salesperson's existing fields
    public void modifySalesperson(Salesperson salesperson) throws IOException {
        Main.salespeople.put(salesperson.getSalespersonID(), salesperson);
        FileOutputStream f = new FileOutputStream("Salesperson.txt");
        ObjectOutputStream o = new ObjectOutputStream(f);

        o.writeObject(Main.salespeople);
        o.flush();
        f.close();
        o.close();
    }

    // Loads the hashmap in salesperson.txt and places it in the hashtable in the Main
    public void getSalesperson() throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream("Salesperson.txt");
        ObjectInputStream oi = new ObjectInputStream(fi);

        Main.salespeople = (HashMap<Integer, Salesperson>) oi.readObject();
        oi.close();
        fi.close();
    }

    public void displaySalesperson() {
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

}
