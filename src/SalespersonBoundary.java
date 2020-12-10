import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SalespersonBoundary {
    SalespersonController salespersonController;

    public SalespersonBoundary(SalespersonController salespersonController){
        this.salespersonController = salespersonController;
    }

    void showSalespersonUI() throws IOException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        sop("Salesperson");
        sop("""
                            1. Add new Salesperson
                            2. Check current Salesperson Performance
                            3. Search ID for specific Salesperson information
                            4. Set commission rate for salesperson
                            5. return to main menu""");
        int sp_choice = input.nextInt();
        switch (sp_choice){
            case 1 -> {
                // Retrieve salespeople from file
                if(new File("Salesperson.txt").exists()){
                    salespersonController.getSalespersons();
                }

                sop("\n*********************************************");
                sop("adding new salesperson");

                sop("Enter first name for new salesperson");
                String fn = input.nextLine();
                while(!isValidName(fn)){
                    fn = input.nextLine();
                    if(!isValidName(fn)){
                        sop("Invalid, please enter a valid last name again");}
                }


                sop("Enter last name for new salesperson");
                String ln = input.nextLine();
                while(!isValidName(ln)){
                    ln = input.nextLine();
                    if(!isValidName(ln)){
                        sop("Invalid, please enter a valid last name again");}
                }


                sop("Enter phone for new salesperson");
                String phone = input.nextLine();


                sop("Enter address for new salesperson");
                String address = input.nextLine();

//                sop("Enter Total commission Earned by salesperson");
//                double commission = 0;
//                try{
//                    commission = input.nextDouble();}
//                catch (Exception e){
//                    sop("Failed adding Salesperson, please enter only arabic numeric numbers for commission earned!");
//                    return;
//                }

//                sop("Enter Total sales Earned by salesperson");
//                double sales = 0;
//                try{
//                    sales = input.nextDouble();}
//                catch (Exception e){
//                    sop("Failed adding Salesperson, please enter only arabic numeric numbers for total sales!");
//                    return;
//                }

                LocalDate date = LocalDate.now();
                try{
                    sop("Enter start year for new salesperson");
                    int yy  = input.nextInt();
                    sop("Enter start month for new salesperson");
                    int mm  = input.nextInt();
                    sop("Enter start day for new salesperson");
                    int dd  = input.nextInt();
                    date = LocalDate.of(yy, mm, dd);}
                catch (Exception e){
                    sop("Failed adding Salesperson, please enter correct dates");
                    return;
                }


                sop("Enter commission rate for new salesperson");
                BigDecimal rate = BigDecimal.ZERO;
                try{
                    rate = input.nextBigDecimal();}
                catch (Exception e){
                    sop("Failed adding Salesperson, please enter only arabic numeric numbers for commission rate!");
                    return;
                }

                int id = salespersonController.findNextSalespersonID();

                // It's not the first time we're adding a salesperson, so retrieve the existing ones from the txt file
                if (new File("Salesperson.txt").exists()) {
                    salespersonController.getSalespersons();
                }

                salespersonController.writeSalespersons(fn, ln, phone, address, date, rate, id);
                sop("salesperson added");

            }

            case 2 -> {
                sop("\n*********************************************");
                sop("display all salesperson performance");
                if (new File("Salesperson.txt").exists()) {
                    // Retrieve salespeople from file
                    salespersonController.getSalespersons();
                    salespersonController.displaySalespersons();
                }
                else {
                    sop("No salesperson exist!");
                }
            }

            case 3 -> {
                sop("\n*********************************************");
                sop("Enter the salesperson ID");
                if (new File("salesperson.txt").exists()) {
                    // Retrieve salespeople from file
                    salespersonController.getSalespersons();
                    int id = input.nextInt();
                    salespersonController.searchSalespersonID(id);
                }

            }

            case 4 -> {
                sop("setting commission rate");
                if (new File("Salesperson.txt").exists()) {
                    salespersonController = new SalespersonController();
                    salespersonController.getSalespersons();
                    salespersonController.displaySalespersons();
                    sop("\n*********************************************");
                    System.out.println("Enter the salesperson ID number you want to set commission rate");
                    int key;
                    try{
                        key = input.nextInt();}
                    catch (Exception e){
                        sop("Please enter an correct ID (numbers only) !");
                        return;
                    }
                    Salesperson salesperson = null;
                    try {
                        for (Salesperson sp: Main.salespeople.values()) {
                            if (Main.salespeople.containsKey(key)) {
                                salesperson = sp;
                                salesperson = Main.salespeople.get(key);
                            }
                        }
                    }
                    catch (NullPointerException n) {
                        System.out.println("Salesperson doesn't exist!");
                        break;
                    }

                    assert salesperson !=null;
                    salespersonController.setRate(salesperson);
                    sop("the commission rate has been applied to the salesperson");
                }
                else{
                    sop("currently the store do not have any salesperson");
                }

            }
            case 5 -> {
                Main.showMainUI();
            }
            default -> sop("Please choose number from 1 - 4");
        }
    }
    private static void sop(String s){
        System.out.println(s);
    }
    public static boolean isValidName(String input){
        return Pattern.matches("[a-zA-Z]+", input);
    }


}
