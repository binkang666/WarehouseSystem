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
                            4. Change Status
                            5. Return to main menu"""
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
                else{
                    sop("NO customer with given ID exist");
                }
            }

            case 4 ->{
                sop("changing status");
                if (new File("Customer.txt").exists()) {
                    customerController = new CustomerController();
                    customerController.getCustomers();
                    customerController.displayCustomers();
                    sop("\n*********************************************");
                    System.out.println("Enter the customer ID number you want to change status");
                    int key;
                    try{
                        key = input.nextInt();}
                    catch (Exception e){
                        sop("Please enter an correct ID (numbers only) !");
                        return;
                    }
                    Customer customer = null;
                    try {
                        for (Customer c: Main.customers.values()) {
                            if (Main.customers.containsKey(key)) {
                                customer = Main.customers.get(key);
                            }
                        }
                    }
                    catch (NullPointerException n) {
                        System.out.println("Customer doesn't exist!");
                        break;
                    }
                    sop("Enter Y for changing status to active and N for changing status to inactive");
                    input.nextLine();
                    String check = input.nextLine().toUpperCase();
                    switch (check){
                        case "N" -> {
                            assert customer != null;
                            customer.setStatus(false);
                            customerController.changeStatusToFalse(customer);
                            sop("The customer is now set to inactive.");
                        }
                        case "Y" -> {
                            assert customer != null;
                            customer.setStatus(true);
                            customerController.changeStatusToTrue(customer);
                            sop("The customer is now set to active.");
                        }
                        default -> {
                            sop("choose Y or N");
                        }
                    }

                }
                else{
                    sop("No customers exist!");
                }

            }

            case 5 -> Main.showMainUI(); //OK
            default -> sop("Please choose number from 1 - 3.");
        }
    }


    private static void sop(String s){
        System.out.println(s);
    }

    public static boolean isValidName(String input){
        return Pattern.matches("[a-zA-Z]+", input);
    }


}
