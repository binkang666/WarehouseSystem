import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Warehouse {
	private String name;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phoneNumber;

	private List<Product> productList;

	public Warehouse(String name, String address, String city, String state, String zip, String phoneNumber) {
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNumber = phoneNumber;
	}
	

	public void showProductsUnder(int n) {
		List<Product> products = new ArrayList<>();
		for (int i=0; i<productList.size(); i++) {
			if (productList.get(i).getQuantity()<=n) {
				products.add(productList.get(i));
			}
		}
		Collections.sort(products, new Comparator<Product>() {
			public int compare(Product a, Product b) {
				return a.getQuantity() - b.getQuantity();
			}
		});
		for (int i=0; i<products.size(); i++) {
			products.get(i).display();
		}
	}
	
	public void showQuantity() {
		for (int i=0; i<productList.size(); i++) {
			System.out.println(productList.get(i).getProductName()+" "+productList.get(i).getQuantity());
		}
	}

	public void replenishStock(String product, int quantity) {
		for (int i=0; i<productList.size(); i++) {
			if (product==productList.get(i).getProductName()) {
				productList.get(i).setQuantity(quantity);
				return;
			}
		}
	}

	public void displayAllProducts() {
		Collections.sort(productList, new Comparator<Product>(){
			public int compare(Product a, Product b) {
				return (int)(b.getTotalProfitPercent() - a.getTotalProfitPercent());
			}
		});
		for(int i=0; i<productList.size(); i++) {
			productList.get(i).display();
		}
	}
	
	public void addProduct(String productName, double costPrice, double sellingPrice, int quantitySold, int quantity) {
		productList.add(new Product(productName, costPrice, sellingPrice, quantitySold, quantity));
	}

}
