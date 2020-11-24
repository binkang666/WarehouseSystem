import java.util.*;

public class Person {
    private String first_name;
    private String last_name;
    private long information;
    String name;

    public Person(String firstName, String lastName, long info){
        this.first_name = firstName;
        this.last_name = lastName;
        this.information = info;
        this.name = first_name + last_name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(information, name);
    }

    public String toString(){
        return "Name: " + name + ", Information: " + information;
    }

    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        Person p = (Person) o;
        return (this.name.equals(p.name)) && (this.information == p.information);
    }

}
