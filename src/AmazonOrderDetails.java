import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AmazonOrderDetails {
//universal code
    static final double paTAX = .06;
    NumberFormat formatter = NumberFormat.getCurrencyInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy ");
//Main Method
            public static void main(String[] args) {
                AmazonOrderDetails amazon = new AmazonOrderDetails();
                amazon.testAmazonOrderDetails();
            }

    public void testAmazonOrderDetails() {
        ArrayList<Product> products = new ArrayList<>();
        addProductsToArrayList(products);

        ArrayList<OrderItem> orderItems;
        // add a list of products to the order
        orderItems = createOrderItems(products);
        // Create a customer
        Customer customer =  new Customer("Dexter Schnoodle", "2 Prince Eugene ln", "610-888-1111" , 2213, "Media, PA 19063", "United States");
        // Create an order.  No shipment or payment yet
        Order order = createOrder(orderItems, customer);
        // Create a shipment for the order
        createShipment(order);
        // Create a payment for the order
        createPayment(order);
        // print the order for the customer
        printOrderDetails(order);
        // print the payment invoice for the order
        printInvoice(order);
    }

    public void createShipment(Order order) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Shipment shipment = new Shipment("225566", "UPS", Shipment.ShipmentStatus.InProcess, "1Z3Y67380336377341",
                    dateFormat.parse("13/09/2024"),
                    dateFormat.parse("13/09/2024"), Shipment.ShipmentSpeed.OneDay);
            order.shipment = shipment;
        }
        catch (ParseException e) {
            System.out.println("Parse exception");
        }
    }

    public void createPayment(Order order) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            order.payment =  new Payment(Payment.PaymentType.CreditCard, "132-424-234-8721", "Amazon.com Visa",
                    order.getGrandTotal(), dateFormat.parse("24/9/2024"));
            System.out.println("Inside Create Payment " + order.grandTotal);
        }
        catch (ParseException e)
        {
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
        System.out.println(order.customer.contact);
        System.out.println("\nPayment Method\n\n" + order.payment.getPaymentType());
        System.out.println(order.payment.getPartialAccountNumber());
        System.out.println("\nOrder Summary\n\n" + "Item(s) Subtotal:\n" + "$" + order.itemsSubtotal);
        System.out.println("Shipping and Handling:\n" + "$" + order.shippingHandling);
        System.out.println("Total before tax:\n"  + order.getTotalBeforeTaxes());
        System.out.println("Estimated Tax to be collected:\n"  + order.getEstimatedTaxes());
        System.out.println("Grand Total:\n"  + "$" + order.grandTotal);
    }

    public void printInvoice(Order order) {
        System.out.println("\n***********************************************************");
        System.out.println("Final Invoice Details for Order #" + order.getOrderNumber());
        System.out.println("***********************************************************\n");
        System.out.println("Order Placed: " + order.getFormattedOrderDate());
        System.out.println("Amazon.com order number: " + order.getOrderNumber());
        System.out.println("Order Total: " + order.getFormattedItemsSubtotal() + "\n");
        // Add your code here.
        System.out.println(order.getShipmentStatusAndDate() + "\n");
        System.out.println("Items Ordered/Price\n");
        System.out.println(order.orderItems.toString());
    }

    public void addProductsToArrayList(ArrayList<Product> products) {
        products.add(new Product("124-01", "Home",
                "Aqua Earth 15 Stage Replacement Premium Filter Cartridge Mega Pack 4",
                "Aqua Earth", 29.84
                , Product.Condition.New));
        products.add(new Product("123-01", "Pet",
                "Founouly Professional Household Waterproof Low Noise Pet Hair",
                "TAO-SHI", 17.99
                , Product.Condition.New));
        products.add(new Product("122-01", "Health",
                "Organic Tart Cherry Powder, 4oz | 100% Natural Fruit Powder | US",
                "Micro Ingredients", 26.95
                , Product.Condition.New));
        products.add(new Product("121-01", "Drinks",
                "Essentia Water LLC, 99.9% Pure, Infused with Electrolytes",
                "Amazon.com Services, Inc", 16.66
                , Product.Condition.New));
        products.add(new Product("120-01", "Food",
                "Manukora Raw Manuka Honey, MGO 850+ from New Zealand, Non-GMO",
                "Manukora", 145.00
                , Product.Condition.New));
    }

    public ArrayList<OrderItem> createOrderItems(ArrayList<Product> products) {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        for (int i = 0; i <= products.size()-1; i++) {
            if (i % 2 != 0) {
                orderItems.add(new OrderItem((i), (products.get(i))));
            }
        }
        System.out.println(products);
        return orderItems;
    }

    public Order createOrder(ArrayList<OrderItem> orderItems, Customer customer) {
        Order order = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        order = new Order("114-9710958-5063414",
                //new SimpleDateFormat("dd/MM/yyyy").parse("21/9/2022"),
                new Date(),
                orderItems,
                7.25,
                customer);
        return order;
    }

    //Center of UML the 'Order' Class
       class Order{
            public Order(String orderNumber,
                         Date orderDate,
                         ArrayList<OrderItem> orderItems,
                         double shippingHandling,
                         Customer customer) {
                this.orderNumber = orderNumber;
                this.orderDate = orderDate;
                this.orderItems = orderItems;
                this.shippingHandling = shippingHandling;
                this.customer = customer;
            }

             private String orderNumber;
             private Date orderDate;
             private ArrayList<OrderItem> orderItems;
             private double itemsSubtotal;
             private double shippingHandling;
             private double tax;
             private double grandTotal;
             private Customer customer;
             private Shipment shipment;
             private Payment payment;


                public String getFormattedOrderDate() {
                return dateFormat.format(orderDate);
            }

                public String getOrderNumber() {
                return orderNumber;
            }

                public Customer getCustomer() {
                return customer;
            }

                public Payment getPayment() {
                return payment;
            }

                public Shipment getShipment() {
                return shipment;
            }

                public String getShippingHandlingFormatted() {
                return formatter.format(shippingHandling);
            }

                public double getItemsSubtotal() {
                    double subtotal = 0;

                    for (OrderItem item : orderItems) {
                        subtotal = subtotal + (item.quantity * item.product.price);
                    }
                    itemsSubtotal = subtotal;
                    return subtotal;
                }

                public double getGrandTotal() {
                    grandTotal = getItemsSubtotal() + shippingHandling + getEstimatedTaxes();
                    return grandTotal;
                }

                public String getFormattedItemsSubtotal() {
                return formatter.format(getItemsSubtotal());
            }

                public String getTotalBeforeTaxes() {
                return formatter.format(getItemsSubtotal() + shippingHandling);
            }

                public double getEstimatedTaxes() {
                    tax = (getItemsSubtotal() * paTAX) + shippingHandling;
                    return tax;
                }

                public String getFormattedEstimatedTaxes() {
                return formatter.format(getEstimatedTaxes());
            }

                public String getFormattedGrandTotal() {
                    return formatter.format(getItemsSubtotal() + shippingHandling + getEstimatedTaxes());
                }

                public String getShipmentStatusAndDate() {
                    if (shipment.shipmentStatus == Shipment.ShipmentStatus.Delivered) {
                        return shipment.shipmentStatus.name() + " " + dateFormat.format(shipment.deliveryDate);
                    }
                    return shipment.shipmentStatus.name() + " " + dateFormat.format(shipment.shippedDate);
                }
        }

//Right branch of UML: *Order* -> 'OrderItem' Class -> 'Product' Class -> enum 'Condition' Class
       class OrderItem{
           public OrderItem(int quantity, Product product) {
               this.quantity = quantity;
               this.product = product;
           }
           int quantity;
           Product product;
       }
       class Product{
            public Product(String productId,
                           String productCategory,
                           String productDescription,
                            String soldBy,
                           double price,
                           Condition condition) {
                this.productId = productId;
                this.productCategory = productCategory;
                this.productDescription = productDescription;
                this.soldBy = soldBy;
                this.price = price;
                this.condition = condition;
            }
                private String productId;
                private String productCategory;
                private String productDescription;
                private String soldBy;
                private double price;
                private Condition condition;

           public String toString() {
               return productId + " " + productCategory  + " " +  productDescription + " "  + soldBy + " " + price + " "  + condition;
           }

           enum Condition{New, Used, Refurbished};
       }
//Top Branch of UML: *Order* -> 'Invoice' Class
class Invoice {
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
//Top Left Branch of UML: *Order* -> 'Customer' Class
        class Customer{
            public Customer(String customerName, String streetAddress, String contact, int customerId, String cityStateZip, String country) {
                this.customerName = customerName;
                this.streetAddress = streetAddress;
                this.contact = contact;
                this.customerId = customerId;
                this.cityStateZip = cityStateZip;
                this.country = country;
            }
             private String customerName;
             private String streetAddress;
             private String contact;
             private int customerId;
             private String cityStateZip;
             private String country;

    public String getCustomerName() {
        return customerName;
    }

    public String getStreetAddress() {return streetAddress;}

    public String getContact() {return contact;}

    public String getCityStateZip() {return cityStateZip;}

    public String getCountry() {return country;}

        }
//Left Branch of UML: *Order* -> 'Shipment' Class -> enum 'ShipmentStatus' Class && enum 'ShipmentSpeed' Class
        class Shipment {
            public Shipment(String shipmentId, String carrier, ShipmentStatus shipmentStatus, String trackingId,
                        Date shippedDate, Date deliveryDate, ShipmentSpeed speed) {
                this.shipmentId = shipmentId;
                this.carrier = carrier;
                this.shipmentStatus = shipmentStatus;
                this.trackingId = trackingId;
                this.shippedDate = shippedDate;
                this.deliveryDate = deliveryDate;
                this.shipmentSpeed = speed;
        }

            private String shipmentId;
            private String carrier;
            private ShipmentStatus shipmentStatus;
             private String trackingId;
            private Date shippedDate;
            private Date deliveryDate;
            private ShipmentSpeed shipmentSpeed;

        public String getFormattedShippedDate() {
            return dateFormat.format(shippedDate);
        }
        public String getFormattedDeliveryDate() {
            return dateFormat.format(deliveryDate);
        }

        enum ShipmentStatus {InProcess, Shipped, Delivered}
        enum ShipmentSpeed {OneDay, TwoDay, Mail}
    }

//Bottom branch of UML: *Order* -> 'Payment' Class -> enum 'PaymentType' Class
    class Payment{
        public Payment(PaymentType paymentType, String accountNumber, String bankOrIssuer,
                       double paymentAmount, Date paymentDate) {
            this.paymentType = paymentType;
            this.accountNumber = accountNumber;
            this.bankOrIssuer = bankOrIssuer;
            this.paymentAmount = paymentAmount;
            this.paymentDate = paymentDate;
    }

        private PaymentType paymentType;
        private String accountNumber;
        private String bankOrIssuer;
        private double paymentAmount;
        private Date paymentDate;

        public String getBankOrIssuer() {
            return bankOrIssuer;
        }

        public PaymentType getPaymentType() {
            return paymentType;
        }

        public String getPartialAccountNumber(){
            return "**** " + accountNumber.substring(accountNumber.length() - 4);
        }

        public String getFormattedPaymentDate(){
            return dateFormat.format(paymentAmount);
        }

        public String getFormattedPaymentAmount(){
            return formatter.format(paymentAmount);
        }

        enum PaymentType {CreditCard, BankTransfer, Mail, AmazonRewardsVisa}
    }

}
