import java.io.*;

public class LoginController {

    LoginBoundary loginBoundary = new LoginBoundary(this);
    User user;

    public void createPassword(String password) throws IOException {
        user = new User(password);
        FileOutputStream f = new FileOutputStream(new File("User.txt"));
        ObjectOutputStream o = new ObjectOutputStream(f);

        o.writeObject(user);
        f.close();
        o.close();
    }

    // Checks if the argument password matches the password in the text file
    public Boolean login(String password) throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(new File("User.txt"));
        ObjectInputStream oi = new ObjectInputStream(fi);

        user = (User) oi.readObject();
        return password.equals(user.getPassword());
    }
}
