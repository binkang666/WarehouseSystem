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

        else {
            int input = -1;
            Boolean valid;
            String password;

            while (input != 3) {
                System.out.println("Select an option\n" +
                    "1. Enter password\n" +
                    "2. Change password\n" +
                    "3. Exit");
                    input = sc.nextInt();
                // Enter password
                if (input == 1) {
                    System.out.println("Enter password: ");
                    password = sc.next();
                    valid = loginController.login(password);

                    if (valid) {
                        showMainUI();
                        // Ends the program after all actions in MainUI are completed...
                        break;
                    }
                    else {
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
                    }
                    else {
                        System.out.println("Invalid Password.\n");
                    }
                }
                // Exit
                else {
                    input = 3;
                }
            }
        }
    }

    void showMainUI() {
        System.out.println("Main UI");
    }
}
