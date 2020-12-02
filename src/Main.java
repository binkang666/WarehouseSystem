import java.io.*;
import java.time.LocalDate;
import java.util.*;


public class Main {

    private static Map<String, Person> mPersons;
    private static Map<String, Customer> mCustomers;
    private static Map<String, Salesperson> mSalesperson;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //login process here
        LoginController loginController = new LoginController();
        loginController.loginBoundary.showLoginUI();


        //need to try login first, if correct, then proceed the following
        showMainUI();
        int choice;
        Scanner input = new Scanner(System.in);
        menu:do {
            choice = input.nextInt();
            switch (choice) {
                case 1 -> {
                    sop("""
                            1. add customers
                            2. Show all customers
                            3. Change customer status
                            4. Return to main menu"""
                    );
                    int choice_customer = input.nextInt();
                    switch (choice_customer){
                        case 1 -> {
                            sop("\n*********************************************");
                            sop("adding customers");
                            Customer c = new Customer();

                            sop("Enter first name for new customer");
                            input.nextLine();
                            String fn = input.nextLine();
                            c.setFirst_name(fn);

                            sop("Enter last name for new customer");
                            String ln = input.nextLine();
                            c.setLast_name(ln);

                            sop("Enter phone for new customer");
                            String phone = input.nextLine();
                            c.setPhone(phone);

                            sop("Enter address for new customer");
                            String add = input.nextLine();
                            c.setAddress(add);

                            sop("Enter salesTax for new customer");
                            double tax = input.nextDouble();
                            c.setSalesTax(tax);

                            writeCustomer(c);
                            sop("New Customers added");
                            showMainUI();

                        }  //OK
                        case 2 -> {sop("showing all customers");
                            displayAll("Customer.txt");
                        }  //OK
                        case 3 -> {sop("Enter the customer ID for the customer who you want change status");
                            int id = input.nextInt();


                        }  //Need implementation, prob gonna delete this
                        case 4 -> {showMainUI(); continue menu;} //OK
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
                case 5 -> {sop("Salesperson");
                    sop("""
                            1. Add new Salesperson
                            2. Check current Salesperson Performance
                            3. return to main menu""");
                    int sp_choice = input.nextInt();
                    switch (sp_choice){
                        case 1 -> {sop("adding new salesperson");
                            Salesperson sp = new Salesperson();
                            sop("Enter first name for new salesperson");
                            input.nextLine();
                            String fn = input.nextLine();
                            sp.setFirst_name(fn);
                            sop("Enter last name for new salesperson");
                            String ln = input.nextLine();
                            sp.setLast_name(ln);
                            sop("Enter phone for new salesperson");
                            String phone = input.nextLine();
                            sp.setPhone(phone);
                            sop("Enter address for new salesperson");
                            String address = input.nextLine();
                            sp.setAddress(address);
                            sop("Enter commission rate for new salesperson");
                            double rate = input.nextDouble();
                            sp.setCommissionRate(rate);

                            writeSalesperson(sp);
                            sop("salesperson added");
                            showMainUI();

                        }

                        case 2 -> {
                            sop("display all salesperson performance");
                            displayAll("Salesperson.txt");
                        }

                        case 3 -> {
                            displayAll("Salesperson.txt");
                        }
                    }
                }
                case 6 -> {
                    sop("Quitting now");
                    return;
                }
                default -> sop("Please select one of the options from 1 - 6");
            }
        }while(choice < 7);


    }
    private static void writeCustomer(Customer data){
        File file = new File("Customer.txt");
        try{
            try(FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
                BufferedWriter out = new BufferedWriter(fileWriter);) {
                out.write(data.toString() + "\n");
                out.flush();
            }
        }catch (IOException e){
           e.printStackTrace();
        }
    }

    private static void modify(){

    }

    private static void writeSalesperson(Salesperson data){
        File file = new File("Salesperson.txt");
        try{
            try(FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
                BufferedWriter out = new BufferedWriter(fileWriter);) {
                out.write(data.toString() + "\n");
                out.flush();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void displayAll(String pathname)
    {
        try (FileReader reader =  new FileReader(pathname);
            BufferedReader br =  new BufferedReader(reader)){
            String line;
            while((line = br.readLine())!=null){
                sop(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
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
