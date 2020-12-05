import java.io.*;

public class CustomerController {
    CustomerBoundary customerBoundary = new CustomerBoundary(this);

    // Look at the ID value in the last line Customer.txt and add 1 to it.
    public int findNextCustomerID() throws IOException {
        // If the file doesn't exist, that implies it's the first customer
        if (!new File("Customer.txt").exists()) {
            return 1;
        }

        BufferedReader input = new BufferedReader(new FileReader("Customer.txt"));
        String last = null, thisLine;

        while ((thisLine = input.readLine()) != null) {
            last = thisLine;
        }

        assert last != null;
        return Integer.parseInt(last.substring(0, last.indexOf(" "))) + 1;
    }

}
