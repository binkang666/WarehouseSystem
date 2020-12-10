import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class ProductBoundary {
    ProductController productController;
    WarehouseController warehouseController;

    public ProductBoundary(ProductController productController) {
        this.productController = productController;
    }

    public void showProductUI() throws IOException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        sop("Products");
        sop("""
                1. Add Product
                2. Show all products
                3. Show products that have n or fewer in the warehouse
                4. Replenish Stock
                5. Remove Product
                6. Return to main menu"""
        );

        int choice2 = input.nextInt();
        switch (choice2) {
            case 1 -> {
                // load up warehouses
                if (new File("Warehouse.txt").exists()) {
                    warehouseController = new WarehouseController();
                    warehouseController.getWarehouses();
                    sop("Adding Product\n");
                    Scanner scanner = new Scanner(System.in);
                    sop("Enter name of the warehouse: ");
                    String warehouse = scanner.nextLine();
                    if (Main.warehouses.containsKey(warehouse)) {
                        sop("Enter name of the product: ");
                        String product = scanner.nextLine();
                        sop("Enter costPrice: ");
                        String cprice = scanner.nextLine();
                        sop("Enter sellingPrice: ");
                        String sprice = scanner.nextLine();
                        Warehouse foundWarehouse = Main.warehouses.get(warehouse);
                        productController.addProduct(foundWarehouse, product, Double.parseDouble(cprice), Double.parseDouble(sprice));
                        warehouseController.modifyWarehouse(foundWarehouse);
                        sop("New product added..");
                    } else {
                        sop("Unavailable warehouse: ");
                    }
                } else {
                    sop("There are no warehouses!");
                }
            }


            case 2 -> {
                // load up warehouses
                if (new File("Warehouse.txt").exists()) {
                    warehouseController = new WarehouseController();
                    warehouseController.getWarehouses();
                    sop("Show all products\n");
                    sop("Enter name of the warehouse: ");
                    Scanner scanner = new Scanner(System.in);
                    String warehouse = scanner.nextLine();
                    if (Main.warehouses.containsKey(warehouse)) {
                        Warehouse foundWarehouse = Main.warehouses.get(warehouse);
                        warehouseController.displayAllProducts(foundWarehouse);
                    } else {
                        sop("Unavailable warehouse: ");
                    }
                } else {
                    sop("No warehouses available!");
                }
            }
            case 3 -> {
                // load up warehouses
                if (new File("Warehouse.txt").exists()) {
                    warehouseController = new WarehouseController();
                    warehouseController.getWarehouses();
                    sop("Show products that have n or fewer in the warehouse\n");
                    Scanner scanner = new Scanner(System.in);
                    sop("Enter name of the warehouse: ");
                    String warehouse = scanner.nextLine();
                    sop("Enter quantity: ");
                    String quantity = scanner.nextLine();
                    if (Main.warehouses.containsKey(warehouse)) {
                        Warehouse foundWarehouse = Main.warehouses.get(warehouse);
                        warehouseController.showProductsUnder(foundWarehouse, Integer.parseInt(quantity));
                    } else {
                        sop("Unavailable warehouse");
                    }
                } else {
                    sop("No warehouses available!");
                }
            }
            case 4 -> {
                // load up warehouses
                if (new File("Warehouse.txt").exists()) {
                    warehouseController = new WarehouseController();
                    warehouseController.getWarehouses();
                    sop("Replenish Stock\n");
                    Scanner scanner = new Scanner(System.in);
                    sop("Enter name of the warehouse: ");
                    String warehouse = scanner.nextLine();
                    if (Main.warehouses.containsKey(warehouse)) {
                        sop("Enter product name: ");
                        String product = scanner.nextLine();
                        sop("Enter quantity: ");
                        String quantity = scanner.nextLine();

                        Warehouse foundWarehouse = Main.warehouses.get(warehouse);
                        warehouseController.replenishStock(foundWarehouse, product, Integer.parseInt(quantity));
                        warehouseController.modifyWarehouse(foundWarehouse);

                        sop("stock replenished..");

                    } else {
                        sop("Unavailable warehouse");
                    }
                } else {
                    sop("No warehouses available!");
                }
            }
//            case 5 ->{
//            	//cannot enter quantity less than actual quantity that product has
//            	Scanner scanner = new Scanner(System.in);
//            	sop("What product you want to remove?: ");
//            	String product = scanner.nextLine();
//            	if(Warehouse.getProductList().contains(product)) {
//            		sop("How many quantity you want to remove?: ");
//            		int quantity = scanner.nextInt();
//            		for (Product p : Warehouse.getProductList()) {
//            			if(quantity > p.getQuantity()) {
//            				sop("Unavailable quantity");
//            			}
//            			else {
//            				warehouseController.removeProduct(product, quantity);
//                    		sop("removed..");
//            			}
//            		}
//            	}
//            	else {
//            		sop("Product not found");
//            	}
//            }
            default -> {
                sop("Going back");
            }
        }
    }
        private static void sop (String s){
            System.out.println(s);
        }

}
