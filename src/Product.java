public class Product {
    private String productName;
    private double costPrice, sellingPrice;

    private int quantitySold;
    private int quantity;
    private double totalCost = costPrice * quantity;
    private double totalProfit = (sellingPrice * quantitySold) - (costPrice * quantitySold);
    private double totalProfitPercent = (totalProfit / (sellingPrice * quantitySold)) * 100;
    private double totalSales = quantitySold * sellingPrice;


    public double getSellingPrice() {
        return sellingPrice;
    }
}

