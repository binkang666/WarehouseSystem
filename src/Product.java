import java.io.Serializable;
import java.text.DecimalFormat;

public class Product implements Serializable {
    private String productName;
    private double costPrice, sellingPrice;
    private int quantitySold;
    private int quantity;
    // Remove quantity and quantitySold from constructor because a product should have 0 quantity and should have been
    // sold 0 times when created
    public Product(String productName, double costPrice, double sellingPrice) {
    	this.productName = productName;
    	this.costPrice = costPrice;
    	this.sellingPrice = sellingPrice;
    	this.quantitySold = quantitySold;
    	this.quantity = quantity;
    	quantity = 0;
    	quantitySold = 0;
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
    	return quantity-quantitySold;
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

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", costPrice=" + costPrice +
                ", sellingPrice=" + sellingPrice +
                ", quantitySold=" + quantitySold +
                ", quantity=" + quantity +
                '}';
    }

    //TODO: have toString replace this method
//    public void display() {
//    	DecimalFormat ft = new DecimalFormat("#.##");
//    	System.out.printf("\n\nProduct name: "+ productName +
//    			"\nCost price: "+costPrice +
//    			"\nSelling price: "+sellingPrice+
//    			"\nQuantity: "+ quantity+
//    			"\nQuantity Sold: "+quantitySold+
//    			"\nTotal sales: "+getTotalSales()+
//    			"\nTotal cost: "+getTotalCost()+
//    			"\nTotal profit: "+ft.format(getTotalProfit())+
//    			"\nTotal Profit Percent: "+ft.format(getTotalProfitPercent())+"\n");
//
//    }
}

