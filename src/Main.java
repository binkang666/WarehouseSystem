import java.io.*;
import java.time.LocalDate;
import java.util.*;


public class Main {

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
                    CustomerController customerController = new CustomerController();
                    customerController.customerBoundary.showCustomerUI();
                    EnterToContinue();
                    showMainUI();

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
        }while(choice <7);


    }
    static void writeCustomer(Customer data){
        File file = new File("Customer.txt");
        try{
            try(FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
                BufferedWriter out = new BufferedWriter(fileWriter);) {
                out.write(data + "\n");
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

    static void showMainUI(){
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

    public static void displayAllCustomers() {
        displayAll("Customer.txt");
    }

    public static Map<String, Customer> getCustomers() {
        return null;
    }

    public static <K, V> Map<K,V> getInvoices() {
        return null;
    }

    public static void EnterToContinue(){
        System.out.println("Press any key to return to main menu...");
        try{System.in.read();}
        catch(Exception e){	e.printStackTrace();}
    }

    private static Map<String, Customer> customers;
}
