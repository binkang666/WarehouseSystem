import java.io.*;
import java.util.*;

public class WarehouseController {
    WarehouseBoundary warehouseBoundary = new WarehouseBoundary(this);

    public void writeWarehouse(String name, String address, String city, String state, String zip, String phoneNumber)throws IOException {
        Main.warehouses.put(name, new Warehouse(name, address, city, state,zip,phoneNumber));

        FileOutputStream f = new FileOutputStream("Warehouse.txt");
        ObjectOutputStream o = new ObjectOutputStream(f);

        o.writeObject(Main.warehouses);
        o.flush();
        f.close();
        o.close();
    }

    public void getWarehouses() throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream("Warehouse.txt");
        ObjectInputStream oi = new ObjectInputStream(fi);

        Main.warehouses = (HashMap<String, Warehouse>) oi.readObject();
        oi.close();
        fi.close();
    }

    // If a warehouses productList is modified, this is called to update the warehouses map
    public void modifyWarehouse(Warehouse warehouse) throws IOException {
        Main.warehouses.put(warehouse.getName(), warehouse);
        FileOutputStream f = new FileOutputStream("Warehouse.txt");
        ObjectOutputStream o = new ObjectOutputStream(f);

        o.writeObject(Main.warehouses);
        o.flush();
        f.close();
        o.close();
    }

    public void showProductsUnder(Warehouse w, int n) throws IOException {
        List<Product> products = new ArrayList<>();
        for (int i=0; i<w.getProductList().size(); i++) {
            if (w.getProductList().get(i).getQuantity()<=n) {
                Product p = w.getProductList().get(i);
                products.add(p);
                if (n <= 5) {
                    Scanner sc = new Scanner(System.in);
                    System.out.println(p.getProductName() + " has " + p.getQuantity() + " stock remaining. Enter an amount to replenish it to:");
                    int replenish = sc.nextInt();
                    replenishStock(w, p.getProductName(), replenish);
                    WarehouseController warehouseController = new WarehouseController();
                    warehouseController.modifyWarehouse(w);
                }
            }
        }
        // Lambda function to sort by quantity
        Collections.sort(products, (a, b) -> a.getQuantity() - b.getQuantity());
        System.out.println("Showing products under " + n + " stock (including now replenished items)");
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }

    public void showQuantity(Warehouse w) {
        for (int i=0; i<w.getProductList().size(); i++) {
            System.out.println(w.getProductList().get(i).getProductName()+" "+w.getProductList().get(i).getQuantity());
        }
    }

    public void replenishStock(Warehouse w, String product, int quantity) {
        for (int i=0; i<w.getProductList().size(); i++) {
            if (product.equals(w.getProductList().get(i).getProductName())) {
                Product p = w.getProductList().get(i);
                p.setQuantity(p.getQuantity() + quantity);
            }
        }
    }

    public void displayAllProducts(Warehouse w) {
        // Lambda function to sort by profit percent
        w.getProductList().sort((a, b) -> (int) (b.getTotalProfitPercent() - a.getTotalProfitPercent()));
        for(int i=0; i<w.getProductList().size(); i++) {
            System.out.println(w.getProductList().get(i).toString());;
        }
    }

    public boolean displayInStockProducts() {
        boolean flag = false;
        for (Warehouse w : Main.warehouses.values()) {
            System.out.println("Warehouse " + w.getName() + "'s stock:");
            for (Product p : w.getProductList()) {
                if (p.getQuantity() > 0) {
                    flag = true;
                    System.out.println(p.getProductName() + ", Quantity: " + p.getQuantity() + ", Selling Price: $"
                            + p.getSellingPrice() + ", Cost Price: $" + p.getCostPrice());
                }
            }
            System.out.println("");
        }
        return flag;
    }

    public Product removeProduct(String name, int quantity) throws IOException {
        boolean flag = false;
        Product product = null;
        for (Warehouse w : Main.warehouses.values()) {
            // Flag is true when you don't need to search anymore warehouses for the quantity of a given item
            if (!flag) {
                for (Product p : w.getProductList()) {
                    if (p.getProductName().equals(name)) {
                        product = p;
                        int temp = p.getQuantity();
                        p.setQuantity(p.getQuantity() - quantity);
                        // If a warehouse's quantity of the product falls below 0, then that warehouse didn't have enough of it but you can still
                        // get more from other warehouses, so take the absolute value of how much the quantity fell below 0 and keep searching through other warehouses.
                        if (p.getQuantity() < 0) {
                            p.setQuantitySold(temp + p.getQuantity()); // Temp holds the original quantity, p.getQuantity holds a neg value, add that negative val to it so this warehouse doesn't sell more than it has
                            quantity = Math.abs(p.getQuantity());
                            // Set the stock in this warehouse to 0
                            p.setQuantity(0);
                            // modify the product list in the warehouse
                            modifyWarehouse(w);
                        }
                        // If you make it here, you found the items and were able to get all the quantity within the current warehouse, so stop searching
                        else {
                            p.setQuantitySold(p.getQuantitySold() + quantity);
                            flag = true;
                            modifyWarehouse(w);
                            break;
                        }
                    }
                }
            }
        }
        product.setQuantity(quantity);
        return product;
    }

    public Boolean productExists(String name) {
        for (Warehouse w : Main.warehouses.values()) {
            for (Product p : w.getProductList()) {
                if (p.getProductName().equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getQuantityForAllWarehouses(String name) {
        int quantity = 0;
        for (Warehouse w : Main.warehouses.values()) {
            for (Product p : w.getProductList()) {
                if (p.getProductName().equals(name)) {
                    quantity += p.getQuantity();
                }
            }
        }
        return quantity;
    }






}
