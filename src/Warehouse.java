import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Warehouse implements Serializable {
	private String name;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phoneNumber;

	private List<Product> productList=new ArrayList<>();

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
		// Lambda function to sort by quantity
		Collections.sort(products, (a, b) -> a.getQuantity() - b.getQuantity());
		for (int i=0; i<products.size(); i++) {
			System.out.println(products.get(i).toString());;
		}
	}
	
	public void showQuantity() {
		for (int i=0; i<productList.size(); i++) {
			System.out.println(productList.get(i).getProductName()+" "+productList.get(i).getQuantity());
		}
	}

	public void replenishStock(String product, int quantity) {
		for (int i=0; i<productList.size(); i++) {
			if (product.equals(productList.get(i).getProductName())) {
				productList.get(i).setQuantity(quantity);
				return;
			}
		}
	}

	public void displayAllProducts() {
		// Lambda function to sort by profit percent
		productList.sort((a, b) -> (int) (b.getTotalProfitPercent() - a.getTotalProfitPercent()));
		for(int i=0; i<productList.size(); i++) {
			System.out.println(productList.get(i).toString());;
		}
	}

	@Override
	// TODO: just have productList print out the stuff needed in the RFP like name and quantity.
	public String toString() {
		return "Warehouse{" +
				"name='" + name + '\'' +
				", address='" + address + '\'' +
				", city='" + city + '\'' +
				", state='" + state + '\'' +
				", zip='" + zip + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", productList=" + productList +
				'}';
	}

	//	public void addProduct(String productName, double costPrice, double sellingPrice) {
//		productList.add(new Product(productName, costPrice, sellingPrice));
//	}


	public String getName() {
		return name;
	}

	public List<Product> getProductList() {
		return productList;
	}
}
