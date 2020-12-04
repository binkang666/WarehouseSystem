
public abstract class Person {
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

    public abstract String getType();
    
    public String getPhone() { return phone; }
    public String getName() { return name = first_name +" " + last_name; }
    public String getAddress() { return address; }
    
    public void setFirst_name(String first_name) { this.first_name = first_name; }
    public void setLast_name(String last_name) { this.last_name = last_name; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setAddress(String address) { this.address = address; }

}
