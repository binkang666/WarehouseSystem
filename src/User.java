import java.io.Serializable;

public class User implements Serializable {

    private String password;
    private static final long serialVersionUID = 1L;

    public User(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Password: " + password;
    }

    public String getPassword() {
        return password;
    }
}
