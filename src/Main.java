import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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

                } //Customers
                case 2 -> {
                    InvoiceController invoiceController = new InvoiceController();
                    invoiceController.invoiceBoundary.showInvoiceUI();
                }
             
                case 3 -> {sop("Warehouse");
                	sop("1. Add warehouse\n"+
                		"2. Show quantity for each product\n"+
                		"3. Return to main menu");
                	int choice2 = input.nextInt();
                	switch(choice2) {
                	case 1 -> {
                		Scanner scanner=new Scanner(System.in);
                		
                		sop("name: ");
                		String name = scanner.nextLine();
                		sop("address: ");
                		String address = scanner.nextLine();
                		sop("city: ");
                		String city = scanner.nextLine();
                		sop("state: ");
                		String state = scanner.nextLine();
                		sop("zip: ");
                		String zip = scanner.nextLine();
                		sop("Phone number: ");
                		String phoneNumber = scanner.nextLine();
                		
                		warehouses.put(name, new Warehouse(name, address, city, state,zip,phoneNumber));
                	}
                	case 2 -> {
                		Scanner scanner=new Scanner(System.in);
                		sop(" Enter warehouse's name: ");
                		String warehouse = scanner.nextLine();
                		if(warehouses.containsKey(warehouse)) {
                			sop(" Unavailable warehouse ");
                		}
                		else {
                			Warehouse foundwarehouse=(Warehouse)warehouses.get(warehouse);
                			foundwarehouse.showQuantity();
                		}
                	}
                	case 3 -> showMainUI();
                	
                	}
                }
                
                case 4 -> {sop("Products");
                    sop("1. Show all products\n" +
                            "2. Show products that have n or fewer in the warehouse\n" +
                    		"3. Replenish Stock\n" +
                            "4. Return to main menu"
                    );
                    int choice2 = input.nextInt();
                    switch (choice2){
                        case 1 -> {
                            sop("Enter name of the warehouse: ");
                            Scanner scanner=new Scanner(System.in);
                    		String warehouse = scanner.nextLine();
                    		if(warehouses.containsKey(warehouse)) {
                    			sop(" Unavailable warehouse ");
                    		}
                    		else {
                    			Warehouse foundwarehouse=(Warehouse)warehouses.get(warehouse);
                    			foundwarehouse.displayAllProducts();
                            
                    		}
                        }
                        case 2 ->{
                        	Scanner scanner=new Scanner(System.in);
                        	sop("Enter name of the warehouse: ");
                        	String warehouse = scanner.nextLine();
                            sop("Enter quantity: ");
                    		String quantity = scanner.nextLine();
                    		if(warehouses.containsKey(warehouse)) {
                    			sop(" Unavailable warehouse ");
                    		}
                    		else {
                    			Warehouse foundwarehouse=(Warehouse)warehouses.get(warehouse);
                    			foundwarehouse.showProductsUnder(Integer.parseInt(quantity));
                    		
                    		}
                            
                        }
                        case 3 -> {
                        	Scanner scanner=new Scanner(System.in);
                        	sop("Enter name of the warehouse: ");
                        	String warehouse = scanner.nextLine();                     
                        	sop("Enter product name: ");
                        	String product = scanner.nextLine();
                            sop("Enter quantity: ");
                    		String quantity = scanner.nextLine();
                    		if(warehouses.containsKey(warehouse)) {
                    			sop(" Unavailable warehouse ");
                    		}
                    		else {
                    			Warehouse foundwarehouse=(Warehouse)warehouses.get(warehouse);
                    			foundwarehouse.replenishStock(product,Integer.parseInt(quantity));
                    		}
                        	
                        }
                        case 4 -> showMainUI();
                        default -> sop("Please select one of the options from 1 - 3");
                    }
                } //products
                case 5 -> {
                    SalespersonController salespersonController = new SalespersonController();
                    salespersonController.salespersonBoundary.showSalespersonUI();
                    EnterToContinue();
                    showMainUI();
                }
                case 6 -> {
                    sop("Quitting now");
                    return;
                }
                default -> sop("Please select one of the options from 1 - 6");
            }
        }while(choice <7);


    }
    //TODO: Possibly Uncomment
//    static void writeCustomer(Map<Customer, String> data){
//        final File file = new File("Customer.txt");
//
//        try{
//            FileOutputStream fos = new FileOutputStream(file, true);
//            PrintStream pw = new PrintStream(fos);
//
//            for(Map.Entry<Customer, String> m: data.entrySet()){
//                pw.println(m.getValue() + m.getKey());
//            }
//            pw.flush();
//            pw.close();
//            fos.close();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }



//    static void writeCustomer(Map<Integer, Customer> data){
//        final File file = new File("Customer.txt");
//
//        try{
//            FileOutputStream fos = new FileOutputStream(file, true);
//            PrintStream pw = new PrintStream(fos);
//
//            for(Map.Entry<Integer, Customer> m: data.entrySet()){
//                pw.println(String.valueOf(m.getKey()) + m.getValue());
//            }
//            pw.flush();
//            pw.close();
//            fos.close();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }


    private static void modify(){

    }

    static void writeSalesperson(Map<Salesperson, String> data){
        final File file = new File("Salesperson.txt");

        try{
            FileOutputStream fos = new FileOutputStream(file, true);
            PrintStream pw = new PrintStream(fos);

            for(Map.Entry<Salesperson, String> m: data.entrySet()){
                pw.println(m.getValue() + m.getKey());
            }
            pw.flush();
            pw.close();
            fos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }



    static void displayAll(String pathname)
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

//    public static Map<String, Customer> getCustomers() {
//        return null;
//    }

    public static <K, V> Map<K,V> getInvoices() {
        return null;
    }

    public static void EnterToContinue(){
        System.out.println("Press any key to return to main menu...");
        try{System.in.read();}
        catch(Exception e){	e.printStackTrace();}
    }

//    static void searchCustomerId(String id) throws IOException {
//        Scanner input = new Scanner(System.in);
//        id = input.nextLine();
//        List<String> lines = Files.readAllLines(Paths.get("Customer.txt"));
//        for (String line : lines) {
//            if (line.contains(id + " (")) {
//                sop(line);
//            }
//        }
//
//    }

    static void searchSalespersonId(String id) throws IOException {
        Scanner input = new Scanner(System.in);
        id = input.nextLine();
        List<String> lines = Files.readAllLines(Paths.get("Salesperson.txt"));
        for (String line : lines) {
            if (line.contains(id + " (")) {
                sop(line);
            }
        }

    }
    static Map<Integer, Customer> customers = new HashMap<>();
//    static Map<Customer, String> customers;
    static Map<Salesperson, String> salespeople;
    static Map<String, Warehouse> warehouses;
    static Map<Integer, Invoice> invoices = new HashMap<>();
}
