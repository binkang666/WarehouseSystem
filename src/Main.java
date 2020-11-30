import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Main {

    private static Map<String, Customer> customers;

    public static void main(String[] args) {

        //login process here

        //need to try login first, if correct, then proceed the following
        showMainUI();
        int choice;
        Scanner input = new Scanner(System.in);
        do {
            choice = input.nextInt();
            switch (choice) {
                case 1 -> {
                    sop("""
                            1. add customers
                            2. Show all customers
                            3. Return to main menu"""
                    );
                    int choice_customer = input.nextInt();
                    switch (choice_customer){
                        case 1 -> sop("adding customers");
                        case 2 -> sop("showing all customers");
                        case 3 -> showMainUI();
                        default -> sop("Please choose number from 1 - 3");
                    }
                }
                case 2 -> sop("Invoice");
                case 3 -> sop("Warehouse");
                case 4 -> {
                     sop("1. Show all products\n" +
                            "2. Show products that have 5 or fewer in the warehouse\n" +
                            "3. Return to main menu"
                     );
                         int choice2 = input.nextInt();
                         switch (choice2){
                             case 1 -> {
                                 sop("showing all products");
                                 //Call showAllProducts method here;
                                 //break;
                             }
                             case 2 ->{
                                 sop("showing products less then 5");
                                 //Call showProductsLessThan5 method here;
                                 //break;
                             }
                             case 3 -> showMainUI();
                             default -> sop("Please select one of the options from 1 - 3");
                         }
                }
                case 5 -> sop("Salesperson");
                case 6 -> {
                    sop("Quitting now");
                    return;
                }
                default -> sop("Please select one of the options from 1 - 6");
            }
        }while(choice < 7);

      /*  if(args.length <1 ){
            sop("Need to pass the name of the input file as argument");
            System.exit(1);
        }
        customers = new HashMap<String, Customer>();*/




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

    private static void showMainUI(){
        sop("*********************************************");
        sop( "** Welcome to Warehouse Management System  **" );
        sop("""
                **            1. Customer                  **
                **            2. Invoice                   **
                **            3. Warehouse                 **
                **            4. Product                   **
                **            5. Salesperson               **
                **            6. Quit                      **""");
        sop("*********************************************\n");
        sop("How can I help you today? (Enter one of the above numbers to proceed)");
    }
}
