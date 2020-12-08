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
					EnterToContinue();
					showMainUI();
				}

				case 3 -> {
					sop("Warehouse");
					sop("1. Add warehouse\n" +
							"2. Show quantity for each product\n" +
							"3. Return to main menu");
					int choice2 = input.nextInt();
					switch (choice2) {
						case 1 -> {
							Scanner scanner = new Scanner(System.in);
							sop("Adding warehouse\n");
							Warehouse wh = new Warehouse(null, null, null, null, null, null);
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

							warehouses.put(name, new Warehouse(name, address, city, state, zip, phoneNumber));
							sop("New warehouse added..");
							EnterToContinue();
							showMainUI();
						}
						case 2 -> {
							Scanner scanner = new Scanner(System.in);
							sop(" Enter warehouse's name: ");
							String warehouse = scanner.nextLine();
							if (warehouses.containsKey(warehouse)) {
								Warehouse foundwarehouse = (Warehouse) warehouses.get(warehouse);
								foundwarehouse.showQuantity();
							} else {
								sop("Warehouse not found");
							}
							EnterToContinue();
							showMainUI();
						}
						case 3 -> showMainUI();

					}
				}


				case 4 -> {
					sop("Products");
					sop("1. Add Product\n"
							+ "2. Show all products\n" +
							"3. Show products that have n or fewer in the warehouse\n" +
							"4. Replenish Stock\n" +
							"5. Return to main menu"
					);
					int choice2 = input.nextInt();
					switch (choice2) {
						case 1 -> {
							sop("Adding Product\n");
							Scanner scanner = new Scanner(System.in);
							sop("Enter name of the warehouse: ");
							String warehouse = scanner.nextLine();
							if (warehouses.containsKey(warehouse)) {
								sop("Enter name of the product: ");
								String product = scanner.nextLine();
								sop("Enter costPrice: ");
								String cprice = scanner.nextLine();
								sop("Enter sellingPrice: ");
								String sprice = scanner.nextLine();
								sop("Enter quantity: ");
								String quantity = scanner.nextLine();
								sop("Enter quantity sold: ");
								String quansold = scanner.nextLine();
								Warehouse foundwarehouse = (Warehouse) warehouses.get(warehouse);
								foundwarehouse.addProduct(product, Double.parseDouble(cprice), Double.parseDouble(sprice),
										Integer.parseInt(quantity), Integer.parseInt(quansold));
								sop("New prodduct added..");
							} else {
								sop("Unavailable warehouse: ");
							}
							EnterToContinue();
							showMainUI();

						}
						case 2 -> {
							sop("Show all products\n");
							sop("Enter name of the warehouse: ");
							Scanner scanner = new Scanner(System.in);
							String warehouse = scanner.nextLine();
							if (warehouses.containsKey(warehouse)) {
								Warehouse foundwarehouse = (Warehouse) warehouses.get(warehouse);
								foundwarehouse.displayAllProducts();
							} else {
								sop("Unavailable warehouse: ");

							}
							EnterToContinue();
							showMainUI();
						}
						case 3 -> {
							sop("Show products that have n or fewer in the warehouse\n");
							Scanner scanner = new Scanner(System.in);
							sop("Enter name of the warehouse: ");
							String warehouse = scanner.nextLine();
							sop("Enter quantity: ");
							String quantity = scanner.nextLine();
							if (warehouses.containsKey(warehouse)) {
								Warehouse foundwarehouse = (Warehouse) warehouses.get(warehouse);
								foundwarehouse.showProductsUnder(Integer.parseInt(quantity));
							} else {
								sop("Unavailable warehouse");

							}
							EnterToContinue();
							showMainUI();

						}
						case 4 -> {
							sop("Replenish Stock\n");
							Scanner scanner = new Scanner(System.in);
							sop("Enter name of the warehouse: ");
							String warehouse = scanner.nextLine();
							if (warehouses.containsKey(warehouse)) {
								sop("Enter product name: ");
								String product = scanner.nextLine();
								sop("Enter quantity: ");
								String quantity = scanner.nextLine();

								Warehouse foundwarehouse = (Warehouse) warehouses.get(warehouse);
								foundwarehouse.replenishStock(product, Integer.parseInt(quantity));

								sop("stock replenished..");

							} else {
								sop("Unavailable warehouse");
							}
							EnterToContinue();
							showMainUI();

						}//product
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
				}
			}
				}while(choice <7);


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


			public static void EnterToContinue(){
				System.out.println("Press any key to return to main menu...");
				try{System.in.read();}
				catch(Exception e){	e.printStackTrace();}
			}



			static Map<Integer, Customer> customers = new HashMap<>();
			static Map<Integer, Salesperson> salespeople =  new HashMap<>();
			static Map<String, Warehouse> warehouses = new HashMap<String, Warehouse>();
//    static Map<Integer, Invoice> invoices = new HashMap<>();
		}