import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SalespersonBoundary {
    SalespersonController salespersonController;

    public SalespersonBoundary(SalespersonController salespersonController){
        this.salespersonController = salespersonController;
    }

    void showSalespersonUI(){
        Scanner input = new Scanner(System.in);
        sop("Salesperson");
        sop("""
                            1. Add new Salesperson
                            2. Check current Salesperson Performance
                            3. return to main menu""");
        int sp_choice = input.nextInt();
        switch (sp_choice){
            case 1 -> {sop("adding new salesperson");
                Salesperson sp = new Salesperson();
                sop("Enter first name for new salesperson");
                Main.salespeople = new HashMap<Salesperson, String>();


                String fn = input.nextLine();
                while(!isValidName(fn)){
                    fn = input.nextLine();
                    if(!isValidName(fn)){
                        sop("Invalid, please enter a valid last name again");}
                }
                sp.setFirst_name(fn);
                Main.salespeople.put(sp, fn);

                sop("Enter last name for new salesperson");
                String ln = input.nextLine();
                while(!isValidName(ln)){
                    ln = input.nextLine();
                    if(!isValidName(ln)){
                        sop("Invalid, please enter a valid last name again");}
                }
                sp.setLast_name(ln);
                Main.salespeople.put(sp, ln);

                sop("Enter phone for new salesperson");
                String phone = input.nextLine();
                sp.setPhone(phone);
                Main.salespeople.put(sp, phone);

                sop("Enter address for new salesperson");
                String address = input.nextLine();
                sp.setAddress(address);
                Main.salespeople.put(sp, address);

                sop("Enter start year for new salesperson");
                int yy  = input.nextInt();
                sop("Enter start month for new salesperson");
                int mm  = input.nextInt();
                sop("Enter start day for new salesperson");
                int dd  = input.nextInt();
                sp.setStartDate(yy,mm,dd);
                LocalDate temp = sp.getStartDate();
                Main.salespeople.put(sp, String.valueOf(temp));

                sop("Enter commission rate for new salesperson");
                double rate = input.nextDouble();
                sp.setCommissionRate(rate);
                Main.salespeople.put(sp, String.valueOf(rate));

                Main.writeSalesperson(Main.salespeople);
                sop("salesperson added");

            }

            case 2 -> {
                sop("display all salesperson performance");
                Main.displayAll("Salesperson.txt");
            }

            case 3 -> {
                Main.showMainUI();
            }
        }
    }
    private static void sop(String s){
        System.out.println(s);
    }
    public static boolean isValidName(String input){
        return Pattern.matches("[a-zA-Z]+", input);
    }

 /*  public static boolean isValidTime(int input){
        return Pattern.matches("[+-]?[0-9]+", String.valueOf(input));
    }*/
}
