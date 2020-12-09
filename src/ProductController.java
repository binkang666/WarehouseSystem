import java.io.IOException;

public class ProductController {
    ProductBoundary productBoundary = new ProductBoundary(this);
    Product product;
    WarehouseController warehouseController = new WarehouseController();


    public void addProduct(Warehouse w, String productName, double costPrice, double sellingPrice) throws IOException {
        Product product = new Product(productName, costPrice, sellingPrice);
        // Make sure product isn't in list already?!
        w.getProductList().add(product);
        warehouseController.modifyWarehouse(w);
    }
}
