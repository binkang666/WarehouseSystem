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

	public String getName() {
		return name;
	}

	public List<Product> getProductList() {
		return productList;
	}
}
