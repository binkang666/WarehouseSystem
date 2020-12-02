public class Product {
    private String productName;
    private double costPrice, sellingPrice;
    
    private String storedIn;

    private int quantitySold;
    private int quantity;
    private double totalCost = costPrice * quantity;
    private double totalProfit = (sellingPrice * quantitySold) - (costPrice * quantitySold);
    private double totalProfitPercent = (totalProfit / (sellingPrice * quantitySold)) * 100;
    private double totalSales = quantitySold * sellingPrice;
    
    //showAllProducts
    
    //showProductsLessThan5
    

    //getter
    public double getSellingPrice() {
        return sellingPrice;
    }
    public double getcostPrice() {
        return costPrice;
    }
    public String getProductName() {
        return productName;
    }
    
    
    
    
    
}

