public class OrderItem {
    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    Product product;
    int quantity;

    @Override
    public String toString() {
        return product  + "\nQuantity: " +  quantity +"\n";
    }
}