import java.util.Scanner;

public class LoginBoundary {
    
    LoginController loginController;

    void showLoginUI() {
        Scanner sc = new Scanner(System.in);
        int input = 0;
        String password;
        Boolean valid;

        while (input != 3) {
            System.out.println("Select an option\n" +
                    "1. Enter password\n" +
                    "2. Change password\n" +
                    "3. Exit");
            input = sc.nextInt();
            if (input == 1) {
                System.out.println("Enter password: ");
                password = sc.nextLine();
                valid = loginController.login(password);
                // check if password is valid, if it is print main ui.
                showMainUI();
            }
            else if (input == 2) {
                System.out.println("Enter password: ");
                password = sc.nextLine();
                valid = loginController.login(password);
                // if password valid
                System.out.println("Enter new password: ");
                password = sc.nextLine();
                loginController.setPassword(password);
                System.out.println("Your password has been updated.");
            }
            else {
                input = 3;
            }
        }
    }

    void showMainUI() {

    }
}
