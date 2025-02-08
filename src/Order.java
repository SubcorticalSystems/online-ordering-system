import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;

class Order {
    public Order(String orderNumber,
                 Date orderDate,
                 ArrayList<OrderItem> orderItems,
                 double shippingHandling,
                 Customer customer){
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.orderItems = orderItems;
        this.shippingHandling = shippingHandling;
        this.customer = customer;
    }

    String orderNumber;
    Date orderDate;
    ArrayList<OrderItem> orderItems;
    double itemsSubtotal;
    double shippingHandling;
    double tax;
    double grandTotal;
    Customer customer;
    Shipment shipment;
    Payment payment;

    NumberFormat formatter = NumberFormat.getCurrencyInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy ");

    public String getFormattedOrderDate() {
        return dateFormat.format(orderDate);
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    /*
    public Customer getCustomer()
    {
        return customer;
    }

    public Payment getPayment() {
        return payment;
    }

    public Shipment getShipment() {
        return shipment;
    }

     */

    public String getShippingHandlingFormatted() {
        return formatter.format(shippingHandling);
    }

    public double getItemsSubtotal() {
        double subtotal = 0;

        for (OrderItem item : orderItems) {
            subtotal += (item.quantity * item.product.price);
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
        tax = (itemsSubtotal * OrderProcessor.taxRate);
        return tax;
    }

    public String getFormattedEstimatedTaxes() {
        return formatter.format(getEstimatedTaxes());
    }

    public String getFormattedGrandTotal() {
        return formatter.format(getItemsSubtotal() + shippingHandling + getEstimatedTaxes());
    }

    public String getShipmentStatusAndDate() {
        if (shipment.shipmentStatus == OrderProcessor.ShipmentStatus.Delivered) {
            return shipment.shipmentStatus.name() + " " + dateFormat.format(shipment.deliveryDate);
        }
        return shipment.shipmentStatus.name() + " " + dateFormat.format(shipment.shippedDate);
    }
}

