import java.text.SimpleDateFormat;
import java.util.Date;

public class Shipment {
    public Shipment(String shipmentId, String carrier, OrderProcessor.ShipmentStatus shipmentStatus, String trackingId,
                    Date shippedDate, Date deliveryDate, OrderProcessor.ShipmentSpeed speed) {
        this.shipmentId = shipmentId;
        this.carrier = carrier;
        this.shipmentStatus = shipmentStatus;
        this.trackingId = trackingId;
        this.shippedDate = shippedDate;
        this.deliveryDate = deliveryDate;
        this.shipmentSpeed = speed;
    }

    String shipmentId;
    String carrier;
    OrderProcessor.ShipmentStatus shipmentStatus;
    String trackingId;
    Date shippedDate;
    Date deliveryDate;
    OrderProcessor.ShipmentSpeed shipmentSpeed;

    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy ");

    public String getFormattedShippedDate() {
        return dateFormat.format(shippedDate);
    }
    public String getFormattedDeliveryDate() {
        return dateFormat.format(deliveryDate);
    }
}
