import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import java.util.regex.Pattern;

public class CustomerBoundary {
    CustomerController customerController;


    public CustomerBoundary(CustomerController customerController){
        this.customerController = customerController;
    }

    void showCustomerUI() throws IOException, ClassNotFoundException {

        Scanner input = new Scanner(System.in);
        sop("""
                            1. Add customers
                            2. Show all customers
                            3. Search for Specific customer
                            4. Return to main menu"""
        );
        int choice_customer = input.nextInt();
        switch (choice_customer){
            // Add customer
            case 1 -> {
                // Retrieve customers from file
                if (new File("Customer.txt").exists()) {
                    customerController.getCustomers();
                }

                sop("\n*********************************************");
                sop("adding customers");

                sop("Enter first name for the new customer");
                String fn = input.nextLine();
                while(!isValidName(fn)){
                    fn = input.nextLine();
                    if(!isValidName(fn)){
                        sop("Invalid, please enter a valid first name again");}
                }


                sop("Enter last name for the new customer");
                String ln = input.nextLine();
                while(!isValidName(ln)){
                    ln = input.nextLine();
                    if(!isValidName(ln)){
                        sop("Invalid, please enter a valid last name again");}
                }

                sop("Enter phone for new the customer (e.g. 666-666-6666)");
                String phone = input.nextLine();

                sop("Enter address for new the customer");
                String add = input.nextLine();

                sop("Enter sales tax for the new customer (e.g. 2.5 for 2.5%)");
                BigDecimal tax;
                try{
                    tax = input.nextBigDecimal();}
                catch (Exception e){
                    sop("Failed adding Customer, please enter only arabic numeric numbers for tax!");
                    return;
                }

                int id = customerController.findNextCustomerID();

                // It's not the first time we're adding a customer, so retrieve the existing ones from the txt file
                //TODO: might not be needed since its loaded at the start of this case?
                if (new File("Customer.txt").exists()) {
                    customerController.getCustomers();
                }
                customerController.writeCustomer(fn, ln, phone, add, tax, id);
                sop("New Customers added.");


            }  //OK
            // Show all customers
            case 2 -> {
                sop("\n*********************************************");
                sop("Showing all customers:");
                if (new File("Customer.txt").exists()) {
                    // Retrieve customers from file
                    customerController.getCustomers();
                    customerController.displayCustomers();
                }
                else {
                    sop("No customers exist!");
                }
            }  //OK

            // Search for a customer
            case 3 -> {
                sop("\n*********************************************");
                sop("Enter the customers ID: ");
                if (new File("Customer.txt").exists()) {
                    // Retrieve customers from file
                    customerController.getCustomers();
                    int id = input.nextInt();
                    customerController.searchCustomerID(id);
                }
            }

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


}
