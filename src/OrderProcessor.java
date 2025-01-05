import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrderProcessor {
        //setup constants and enums
    static final double TAXRATE = .06;

    enum Condition {New, Used, Reconditioned}

    enum ShipmentStatus {InProcess, Shipped, Delivered}

    enum ShipmentSpeed {OneDay, TwoDay, Mail}

    enum PaymentType {CreditCard, BankTransfer}

    public static void main(String[] args) {
        OrderProcessor op = new OrderProcessor();
        op.testOrderDetails();
    }

    public void testOrderDetails() {
        ArrayList<Product> products = new ArrayList<>();
        addProductsToArrayList(products);

        ArrayList<OrderItem> orderItems = new ArrayList<>();
        
        orderItems = createOrderItems(products);

        Customer customer = new Customer("221", "Sully Huckster", "1298 Hares Hill Road", "Kimberton, PA 19442", "United States");

        Order order = createOrder(orderItems, customer);

        createShipment(order);

        createPayment(order);

        printOrderDetails(order);

        printInvoice(order);
    }


    public void createShipment(Order order) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Shipment shipment = new Shipment("225566", "UPS", ShipmentStatus.Delivered, "1Z3Y67380336377341",
                    dateFormat.parse("22/5/2020"),
                    dateFormat.parse("22/5/2020"), ShipmentSpeed.OneDay);
            order.shipment = shipment;
        } catch (ParseException e) {
            System.out.println("Parse exception");
        }
    }

    public void createPayment(Order order) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            order.payment = new Payment(PaymentType.CreditCard, "132-444-234-7744", "Amazon.com Visa",
                    order.getGrandTotal(), dateFormat.parse("24/9/2024"));
        } catch (ParseException e) {
            System.out.println("ParseException.");
        }
    }

    public void printOrderDetails(Order order) {
        System.out.println("*************");
        System.out.println("Order Details");
        System.out.println("*************");
        System.out.println("Ordered on " + order.getFormattedOrderDate() + "Order # " + order.getOrderNumber());
        System.out.println("Shipping Address\n\n" + order.customer.customerName);
        System.out.println(order.customer.streetAddress);
        System.out.println(order.customer.cityStateZip);
        System.out.println(order.customer.country);
        System.out.println("\nPayment Method\n\n" + order.payment.getPaymentType());
        System.out.println(order.payment.getPartialAccountNumber());
        System.out.println("\nOrder Summary\n\n" + "Item(s) Subtotal:\n" + "$" + order.itemsSubtotal);
        System.out.println("Shipping and Handling:\n" + "$" + order.shippingHandling);
        System.out.println("Total before tax:\n" + order.getTotalBeforeTaxes());
        System.out.println("Estimated Tax to be collected:\n" + order.getEstimatedTaxes());
        System.out.println("Grand Total:\n" + "$" + order.grandTotal);
    }

    public void printInvoice(Order order) {
        System.out.println("\n***********************************************************");
        System.out.println("Final Invoice Details for Order #" + order.getOrderNumber());
        System.out.println("***********************************************************\n");
        System.out.println("Order Placed: " + order.getFormattedOrderDate());
        System.out.println("Amazon.com order number: " + order.getOrderNumber());
        System.out.println("Order Total: " + order.getFormattedItemsSubtotal() + "\n");
        System.out.println(order.getShipmentStatusAndDate() + "\n");
        System.out.println("Items Ordered/Price\n");
        System.out.println(order.orderItems.toString());

    }

    public void addProductsToArrayList(ArrayList<Product> products) {
        products.add(new Product("124-01", "Home",
                "Aqua Earth 15 Stage Replacement Premium Filter Cartridge Mega Pack 4",
                "Aqua Earth", 29.84
                , Condition.New));
        products.add(new Product("123-01", "Pet",
                "Founouly Professional Household Waterproof Low Noise Pet Hair",
                "TAO-SHI", 17.99
                , Condition.New));
        products.add(new Product("122-01", "Health",
                "Organic Tart Cherry Powder, 4oz | 100% Natural Fruit Powder | US",
                "Micro Ingredients", 26.95
                , Condition.New));
        products.add(new Product("121-01", "Drinks",
                "Essentia Water LLC, 99.9% Pure, Infused with Electrolytes",
                "Amazon.com Services, Inc", 16.66
                , Condition.New));
        products.add(new Product("120-01", "Food",
                "Manukora Raw Manuka Honey, MGO 850+ from New Zealand, Non-GMO",
                "Manukora", 145.00
                , Condition.New));
    }


    public ArrayList<OrderItem> createOrderItems(ArrayList<Product> products) {
        ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
        //  Pick odd-numbered products for order.  qty will be odd number.
        for (int i = 0; i <= products.size() - 1; i++) {
            if (i % 2 != 0) {
                orderItems.add(new OrderItem(products.get(i), i));
            }
        }
        return orderItems;
    }


    public Order createOrder(ArrayList<OrderItem> orderItems, Customer customer) {
        Order order = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        order = new Order("114-4625135-4373821",
                //   new SimpleDateFormat("dd/MM/yyyy").parse("21/9/2022"),
                new Date(),
                orderItems,
                7.25,
                customer);
        return order;
    }


}




