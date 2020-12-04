import java.util.Scanner;

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

                Main.writeSalesperson(sp);
                sop("salesperson added");

            }

            case 2 -> {
                sop("display all salesperson performance");
                Main.displayAll("Salesperson.txt");
            }

            case 3 -> {
                Main.displayAll("Salesperson.txt");
            }
        }
    }
    private static void sop(String s){
        System.out.println(s);
    }
}
