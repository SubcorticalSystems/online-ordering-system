import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AmazonOrderDetails {
    static final double paTAX = .06;
    NumberFormat formatter = NumberFormat.getCurrencyInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy ");

//Main Method
            public static void main(String[] args) {
                AmazonOrderDetails od = new AmazonOrderDetails();  // Name of main class driver
                ArrayList<Product> products = new ArrayList<>();
                od.addProducts(products); // Create your product data here
                Order order = od.createOrder(products); // Create your order
                od.createShipment(order); // Ship your order
                od.createPayment(order); // Create a payment record
                od.printOrderDetails(order); // Print
                od.printInvoice(order);
            }

    //Execute Main Method calls
    private void addProducts(ArrayList<Product> products) {
        products.add(new Product("11-01", "Shower Filter", "Amazon.com Services LLC", 27.99, Product.Condition.New ));
    }

    private Order createOrder(ArrayList<Product> products) {
        Order order = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        order = new Order("114-4625135-4373821",
                //   new SimpleDateFormat("dd/MM/yyyy").parse("21/9/2022"),
                new Date(), orderItems, 7.00, shippingHandling, paTAX, 8.00, "Matt" );
        return order;
    }

    private void createShipment(Order order) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Shipment shipment = new Shipment("225566", "UPS", ShipmentStatus.Delivered, "1Z3Y67380336377341",
                    dateFormat.parse("22/5/2020"),
                    dateFormat.parse("22/5/2020"), ShipmentSpeed.OneDay);
            order.shipment = shipment;
        }
        catch (ParseException e) {
            System.out.println("Parse exception");
        }
    }

    public void createPayment(Order order) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            order.payment =  new Payment(PaymentType.CreditCard, "132-444-234-7744", "Amazon.com Visa",
                    order.getGrandTotal(), dateFormat.parse("24/9/2024"));
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
        //  Add your code here
    }

    public void printInvoice(Order order) {
        System.out.println("\n***********************************************************");
        System.out.println("Final Invoice Details for Order #" + order.getOrderNumber());
        System.out.println("***********************************************************\n");
        System.out.println("Order Placed: " + order.getFormattedOrderDate());
        System.out.println("Amazon.com order number: " + order.getOrderNumber());
        System.out.println("Order Total: " + order.getFormatttedItemsSubtotal() + "\n");
        // Add your code here.

    }

//Center of UML the 'Order' Class
       class Order{
            private Order(String orderNumber, Date orderDate,
                         ArrayList<OrderItem> orderItems,
                         double itemsSubtotal, double shippingHandling,
                         double tax, double grandTotal, Customer customer,
                         Shipment shipment, Payment payment) {
                this.orderNumber = orderNumber;
                this.orderDate = orderDate;
                this.orderItems = orderItems;
                this.itemsSubtotal = itemsSubtotal;
                this.shippingHandling = shippingHandling;
                this.tax = tax;
                this.grandTotal = grandTotal;
                this.customer = customer;
                this.shipment = shipment;
                this.payment = payment;
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
           private int quantity;
           private Product product;
       }
       class Product{
            public Product(String productId, String productDescription,
                       String soldBy, double price, Condition condition) {
                this.productId = productId;
                this.productDescription = productDescription;
                this.soldBy = soldBy;
                this.price = price;
                this.condition = condition;
            }
            private String productId;
            private String productDescription;
            private String soldBy;
            private double price;
            private Condition condition;

            enum Condition{New, Used, Refurbished};
            public String toString(){
                return productDescription + " "  + soldBy + " " + price + " "  + condition;
            }
            }


//Top Branch of UML: *Order* -> 'Invoice' Class
        class Invoice{
            public Invoice(String invoiceNumber, Order order) {
                InvoiceNumber = invoiceNumber;
                this.order = order;
            }
            private Order order;
            private String InvoiceNumber;
        }
//Top Left Branch of UML: *Order* -> 'Customer' Class
        class Customer{
            public Customer(String customerName, String streetAddress,
                    String contact, int customerId, String cityStateZip, String country) {
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


    public Date getPaymentDate() {
        return paymentDate;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public String getBankOrIssuer() {
        return bankOrIssuer;
    }

    public String getAccountNumber() {
        return accountNumber;
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
