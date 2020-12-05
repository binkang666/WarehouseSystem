public class InvoiceController {

    InvoiceBoundary invoiceBoundary = new InvoiceBoundary(this);

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
