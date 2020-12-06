public class InvoiceController {

    InvoiceBoundary invoiceBoundary = new InvoiceBoundary(this);
    CustomerController customerController = new CustomerController();

    public void openInvoice(Customer c, String address, char delivery, double deliveryCharge) {
        Invoice invoice = new Invoice(c, address, delivery, deliveryCharge);
        Main.getInvoices().put(invoice.getInvoiceNumber(), invoice);
    }

    public void closeInvoice() {
    }

    public void showOpenInvoices() {

    }

    public void showClosedInvoices() {

    }

    public void markShipped() {

    }
}
