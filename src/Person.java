import java.util.*;

public abstract class Person  {
    private String first_name;
    private String last_name;
    private String phone;
    private String name;
    private String address;

    public Person(){
        this.first_name = "";
        this.last_name = "";
        this.phone = "";
        this.address = "";
    }
    public Person(String firstName, String lastName, String phone, String address){
        this.first_name = firstName;
        this.last_name = lastName;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public String toString()
    {
        return "Name: " + getName() + ", Address: " + getAddress() + ", Phone: " + getPhone();
    }

    public String display(){
        return toString() + "\n";
    }

    public abstract String getType();

    //getter - Boundary
    public String getFirst_name() { return first_name; }
    public String getLast_name() { return last_name; }
    public String getPhone() { return phone; }
    public String getName() { return name = first_name + last_name; }
    public String getAddress() { return address; }

    //setter - Controller
    public void setFirst_name(String first_name) { this.first_name = first_name; }
    public void setLast_name(String last_name) { this.last_name = last_name; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }

}
