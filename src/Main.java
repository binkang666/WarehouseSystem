import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;


public class Main {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		// LOGIN
		LoginController loginController = new LoginController();
		loginController.loginBoundary.showLoginUI();
		// Only reached if user logs in
		showMainUI();
		int choice;
		Scanner input = new Scanner(System.in);
		do {
			choice = input.nextInt();
			switch (choice) {
				// CUSTOMER
				case 1 -> {
					CustomerController customerController = new CustomerController();
					customerController.customerBoundary.showCustomerUI();
					EnterToContinue();
					showMainUI();
				}
				// INVOICES
				case 2 -> {
					InvoiceController invoiceController = new InvoiceController();
					invoiceController.invoiceBoundary.showInvoiceUI();
					EnterToContinue();
					showMainUI();
				}
				// WAREHOUSES
				case 3 -> {
					WarehouseController warehouseController = new WarehouseController();
					warehouseController.warehouseBoundary.showWarehouseUI();
					EnterToContinue();
					showMainUI();
				}
				// PRODUCTS
				case 4 -> {
					ProductController productController = new ProductController();
					productController.productBoundary.showProductUI();
					EnterToContinue();
					showMainUI();

				}
				// SALESPERSONS
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
			}
		} while (choice < 7);
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
	static Map<String, Warehouse> warehouses = new HashMap<>();
}
