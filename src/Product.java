import java.io.Serializable;
import java.text.DecimalFormat;

public class Product implements Serializable {
    private String productName;
    private double costPrice;
    private double sellingPrice;
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
    	return quantity;
    }
    //
    public Double getTotalProfitPercent() {

         return (getTotalProfit() / (sellingPrice * (quantitySold))) * 100;

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

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    //setter
    public void setQuantity(int q) {
    	quantity = q;
    }

    @Override
    public String toString() {
    	DecimalFormat ft = new DecimalFormat("#.##");
    	return "\n\nProduct name: "+ productName +
    			"\nCost price: "+costPrice +
    			"\nSelling price: "+sellingPrice+
    			"\nQuantity: "+ quantity+
    			"\nQuantity Sold: "+quantitySold+
    			"\nTotal sales: "+ft.format(getTotalSales())+
    			"\nTotal cost: "+ft.format(getTotalCost())+
    			"\nTotal profit: "+ft.format(getTotalProfit())+
    			"\nTotal Profit Percent: "+ft.format(getTotalProfitPercent())+"\n";
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

