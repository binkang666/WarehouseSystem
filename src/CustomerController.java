import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerController {
    CustomerBoundary customerBoundary = new CustomerBoundary(this);
    Customer customer;

    // Look at the ID value in the last line Customer.txt and add 1 to it.
    public int findNextCustomerID() {
        // If the file doesn't exist, that implies it's the first customer
        if (!new File("Customer.txt").exists()) {
            return 1;
        }
        int max = 0;
        for (Integer i : Main.customers.keySet()) {
            if (i > max) {
                max = i;
            }
        }
        return max + 1;
//
//        BufferedReader input = new BufferedReader(new FileReader("Customer.txt"));
//        String last = null, thisLine;
//
//        while ((thisLine = input.readLine()) != null) {
//            last = thisLine;
//        }
//
//        assert last != null;
//        return Integer.parseInt(last.substring(0, last.indexOf(" "))) + 1;
    }

    public void writeCustomer(String fn, String ln, String phone, String add, double tax, int id) throws IOException {
        customer = new Customer(fn, ln, phone, add, tax, id);
        Main.customers.put(id, customer);

        FileOutputStream f = new FileOutputStream("Customer.txt");
        ObjectOutputStream o = new ObjectOutputStream(f);

        o.writeObject(Main.customers);
        o.flush();
        f.close();
        o.close();
    }

    public void getCustomers() throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream("Customer.txt");
        ObjectInputStream oi = new ObjectInputStream(fi);

        Main.customers = (HashMap<Integer, Customer>) oi.readObject();
        oi.close();
        fi.close();
    }
    // TODO: Verify customers exist
    public void searchCustomerID(int id) {
        System.out.println(Main.customers.get(id).toString());
    }
}
