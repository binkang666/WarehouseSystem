import java.io.Serializable;

public class Product implements Serializable {
    private String productName;
    private double costPrice, sellingPrice;
    private int quantitySold;
    private int quantity;
    
    public Product(String productName, double costPrice, double sellingPrice, int quantitySold, int quantity) {
    	this.productName = productName;
    	this.costPrice = costPrice;
    	this.sellingPrice = sellingPrice;
    	this.quantitySold = quantitySold;
    	this.quantity = quantity;
    }
    
    //getter
    public double getSellingPrice() {
        return sellingPrice;
    }
    public double getCostPrice() {
        return costPrice;
    }
    public String getProductName() {
        return productName;
    }
    public int getQuantity() {
    	return quantity;
    }
    public double getTotalProfitPercent() {
    	return (getTotalProfit() / (sellingPrice * quantitySold)) * 100;
    }
    public double getTotalCost() {
    	return costPrice * quantity;
    }
    public double getTotalProfit() {
    	return (sellingPrice * quantitySold) - (costPrice * quantitySold);
    }
    public double getTotalSales() {
    	return quantitySold * sellingPrice;
    }
   
    
    //setter
    public void setQuantity(int q) {
    	quantity = q;
    }
    
    public void display() {
    	String formatting = String.format("%s %lf %lf %d %d %lf %lf %lf %lf", productName, costPrice, 
    			sellingPrice, quantity, quantitySold, getTotalSales(), getTotalCost(), getTotalProfit(), getTotalProfitPercent());
    	System.out.println(formatting);
   
    }
}

