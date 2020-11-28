import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Main {

    private static Map<String, Customer> customers;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Login
        LoginController loginController = new LoginController();
        loginController.loginBoundary.showLoginUI();

        if(args.length < 1 ){
            sop("Need to pass the name of the input file as argument");
            System.exit(1);
        }
        customers = new HashMap<String, Customer>();




        /* Testing
        Customer c = new Customer();
        c.setLastOrderDate(LocalDate.of(2018,11,1));
        System.out.println(c.toString());
        */

    }

    private static void displayAllCustomers(){
        ArrayList<String> names = new ArrayList<>(customers.keySet());
        Collections.sort(names);
        for(String name : names)
        {
            sop("" + customers.get(name));
        }
    }

    private static ArrayList<String> readInput(String fileName)
    {
        ArrayList<String> input = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                input.add(line);
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            sop("Unable to open file '" + fileName + "'");
            System.exit(1);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return input;
    }

    private static void sop(String s){
        System.out.println(s);
    }
}
