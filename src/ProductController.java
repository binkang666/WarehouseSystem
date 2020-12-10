import java.io.IOException;
import java.math.BigDecimal;

public class ProductController {
    ProductBoundary productBoundary = new ProductBoundary(this);
    WarehouseController warehouseController = new WarehouseController();


    public void addProduct(Warehouse w, String productName, double costPrice, double sellingPrice) throws IOException {
        Product product = new Product(productName, costPrice, sellingPrice);
        w.getProductList().add(product);
        warehouseController.modifyWarehouse(w);
    }
}
