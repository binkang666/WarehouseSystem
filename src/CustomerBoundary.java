import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerBoundary {
    CustomerController customerController;


    public CustomerBoundary(CustomerController customerController){
        this.customerController = customerController;
    }

    void showCustomerUI() throws IOException, ClassNotFoundException {

        Scanner input = new Scanner(System.in);
        sop("""
                            1. add customers
                            2. Show all customers
                            3. Search for Specific customer
                            4. Return to main menu"""
        );
        int choice_customer = input.nextInt();
        switch (choice_customer){
            case 1 -> {
                // Retrieve the existing customers from the text file
                if (new File("Customer.txt").exists()) {
                    customerController.getCustomers();
                }

                sop("\n*********************************************");
                sop("adding customers");

                sop("Enter first name for new customer");
                String fn = input.nextLine();
                while(!isValidName(fn)){
                    fn = input.nextLine();
                    if(!isValidName(fn)){
                        sop("Invalid, please enter a valid first name again");}
                }


                sop("Enter last name for new customer");
                String ln = input.nextLine();
                while(!isValidName(ln)){
                    ln = input.nextLine();
                    if(!isValidName(ln)){
                        sop("Invalid, please enter a valid last name again");}
                }

                sop("Enter phone for new customer");
                String phone = input.nextLine();

                sop("Enter address for new customer");
                String add = input.nextLine();

                sop("Enter salesTax for new customer");
                double tax = input.nextDouble();

                int id = customerController.findNextCustomerID();

                // It's not the first time we're adding a customer, so retrieve the existing ones from the txt file
                if (new File("Customer.txt").exists()) {
                    customerController.getCustomers();
                }
                customerController.writeCustomer(fn, ln, phone, add, tax, id);

//                Main.writeCustomer(Main.customers);
                sop("New Customers added");

                //TODO: Possibly Uncomment
//                sop("\n*********************************************");
//                sop("adding customers");
//                Main.customers = new HashMap<Customer, String>();
//                Customer c= new Customer();
//
//                sop("Enter first name for new customer");
//                String fn = input.nextLine();
//                while(!isValidName(fn)){
//                    fn = input.nextLine();
//                    if(!isValidName(fn)){
//                    sop("Invalid, please enter a valid first name again");}
//                }
//                c.setFirst_name(fn);
//                Main.customers.put(c,fn);;
//
//
//                sop("Enter last name for new customer");
//                String ln = input.nextLine();
//                while(!isValidName(ln)){
//                    ln = input.nextLine();
//                    if(!isValidName(ln)){
//                    sop("Invalid, please enter a valid last name again");}
//                }
//                c.setLast_name(ln);
//                Main.customers.put(c,ln);
//
//
//                sop("Enter phone for new customer");
//                String phone = input.nextLine();
//                c.setPhone(phone);
//                Main.customers.put(c,phone);
//
//
//                sop("Enter address for new customer");
//                String add = input.nextLine();
//                c.setAddress(add);
//                Main.customers.put(c, add);;
//
//
//                sop("Enter salesTax for new customer");
//                double tax = input.nextDouble();
//                c.setSalesTax(tax);
//                Main.customers.put(c, String.valueOf(tax));
//
//                String id = String.valueOf(c.getCustomerID());
//                Main.customers.put(c, id);
//
//
//
//                Main.writeCustomer(Main.customers);
//                sop("New Customers added");

            }  //OK

            // Now in controller vs. main because the file is serialized.
            // TODO: Verify customers exist, maybe make separate print method in controller
            case 2 -> {sop("Showing all customers");
                customerController.getCustomers();
                for (Customer c : Main.customers.values()) {
                    System.out.println(c.toString());
                }
//                Main.displayAll("Customer.txt");
            }  //OK


            case 3 -> {sop("Enter the customer ID for searching specific customer");
                int id = input.nextInt();
                customerController.searchCustomerID(id);
//                String id = input.nextLine();
//                Main.searchCustomerId(id);
            }  //Need implementation

            case 4 -> {Main.showMainUI();} //OK
            default -> sop("Please choose number from 1 - 3");
        }
    }


    private static void sop(String s){
        System.out.println(s);
    }

    public static boolean isValidName(String input){
        return Pattern.matches("[a-zA-Z]+", input);
    }

    /*public static boolean isValidNumber(Double input){
        return Pattern.matches("[0-9]+[.[0-9]+]?", String.valueOf(input));
    }*/

}
