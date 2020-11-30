import java.io.IOException;
import java.util.Scanner;
import java.io.*;

public class LoginBoundary {

    LoginController loginController;

    public LoginBoundary(LoginController loginController) {
        this.loginController = loginController;
    }

    void showLoginUI() throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);

        // Check if it is the first time running the app. Create a password if it is.
        if (!new File("User.txt").exists()) {
            System.out.println("Create a password: ");
            String password = sc.nextLine();
            loginController.createPassword(password);
        }
        // It's not the first time the app is running
        else {
            int input;
            Boolean valid = false;
            String password;

            while (!valid) {
                System.out.println("""
                        Select an option
                        1. Enter password
                        2. Change password
                        3. Exit""");
                input = sc.nextInt();

                // Enter password
                if (input == 1) {
                    System.out.println("Enter password: ");
                    password = sc.next();
                    valid = loginController.login(password);

                    if (!valid) {
                        System.out.println("Invalid Password\n");
                    }
                }
                // Change password
                else if (input == 2) {
                    System.out.println("Enter password: ");
                    password = sc.next();
                    valid = loginController.login(password);

                    if (valid) {
                        System.out.println("Enter new password: ");
                        password = sc.next();
                        loginController.createPassword(password);
                        System.out.println("Your password has been updated.\n");
                        // Set valid to false so user has to re-enter password.
                        valid = false;
                    }
                    else {
                        System.out.println("Invalid Password.\n");
                    }
                }
                // Exit
                else {
                    System.exit(1);
                }
            }
        }
    }
}
