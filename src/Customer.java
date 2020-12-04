import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerBoundary {
    CustomerController customerController;

    public CustomerBoundary(CustomerController customerController){
        this.customerController = customerController;
    }

    void showCustomerUI(){

        Scanner input = new Scanner(System.in);
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
                String fn = input.nextLine();
                while(!isValidName(fn)){
                    fn = input.nextLine();
                    if(!isValidName(fn)){
                    sop("Invalid, please enter a valid first name again");}
                }
                c.setFirst_name(fn);


                sop("Enter last name for new customer");
                String ln = input.nextLine();
                while(!isValidName(ln)){
                    ln = input.nextLine();
                    if(!isValidName(ln)){
                    sop("Invalid, please enter a valid last name again");}
                }
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

                Main.writeCustomer(c);
                sop("New Customers added");
                
            }  //OK
            
            
            case 2 -> {sop("showing all customers");
                Main.displayAllCustomers();
            }  //OK
            
            
            case 3 -> {sop("Enter the customer ID for the customer who you want change status");
                

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
