import java.io.*;
import java.util.HashMap;

public class WarehouseController {
    WarehouseBoundary warehouseBoundary = new WarehouseBoundary(this);
    Warehouse warehouse;

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






}
