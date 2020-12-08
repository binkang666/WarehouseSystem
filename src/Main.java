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
					//TODO: delete, was used to see if the warehouse and its products are persistent.
					if (new File("Warehouse.txt").exists()) {
						getWarehouse();
					}
					for (Warehouse w: warehouses.values()) {
						System.out.println(w.toString());
					}



					sop("Warehouse");
					sop("1. Add warehouse\n" +
							"2. Show quantity for each product\n" +
							"3. Return to main menu");
					int choice2 = input.nextInt();
					switch (choice2) {
						case 1 -> {
							// load up warehouses, since were adding a new one
							if (new File("Warehouse.txt").exists()) {
								getWarehouse();
							}

							Scanner scanner = new Scanner(System.in);
							sop("Adding warehouse\n");
							Warehouse wh;
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

							writeWarehouse(name, address, city, state, zip, phoneNumber);

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
							// load up warehouses
							if (new File("Warehouse.txt").exists()) {
								getWarehouse();
							}
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
								Warehouse foundWarehouse = warehouses.get(warehouse);
								addProduct(foundWarehouse, product, Double.parseDouble(cprice), Double.parseDouble(sprice));
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
								Warehouse foundWarehouse = warehouses.get(warehouse);
								foundWarehouse.displayAllProducts();
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
								Warehouse foundWarehouse = warehouses.get(warehouse);
								foundWarehouse.showProductsUnder(Integer.parseInt(quantity));
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

								Warehouse foundWarehouse = warehouses.get(warehouse);
								foundWarehouse.replenishStock(product, Integer.parseInt(quantity));

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
				    
			public static void writeWarehouse(String name, String address, String city, String state, String zip, String phoneNumber)throws IOException {
    				warehouses.put(name, new Warehouse(name, address, city, state,zip,phoneNumber));

        			FileOutputStream f = new FileOutputStream("Warehouse.txt");
        			ObjectOutputStream o = new ObjectOutputStream(f);

       				o.writeObject(warehouses);
        			o.flush();
        			f.close();
        			o.close();

			}

			public static void getWarehouse() throws IOException, ClassNotFoundException {
				FileInputStream fi = new FileInputStream("Warehouse.txt");
				ObjectInputStream oi = new ObjectInputStream(fi);

				warehouses = (HashMap<String, Warehouse>) oi.readObject();
				oi.close();
				fi.close();
			}

			// If a warehouses productList is modified, this is called to update the warehouses map
			public static void modifyWarehouse(Warehouse warehouse) throws IOException {
				Main.warehouses.put(warehouse.getName(), warehouse);
				FileOutputStream f = new FileOutputStream("Warehouse.txt");
				ObjectOutputStream o = new ObjectOutputStream(f);

				o.writeObject(warehouses);
				o.flush();
				f.close();
				o.close();
			}
			// Before you add a product, make sure to load up the Warehouses.txt with getWarehouse
			public static void addProduct(Warehouse w, String productName, double costPrice, double sellingPrice) throws IOException {
				Product product = new Product(productName, costPrice, sellingPrice);
				// Make sure product isn't in list already?!
				w.getProductList().add(product);
				modifyWarehouse(w);

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
