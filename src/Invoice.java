import java.util.ArrayList;

public class Invoice {
    Order order;
    String invoiceNumber;

    public Invoice(Order order) {
        this.order = order;
    }


    public Invoice(Order order, String invoiceNumber) {
        this.order = order;
        this.invoiceNumber = invoiceNumber;
    }
}