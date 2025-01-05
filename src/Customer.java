public class Customer
{
    public Customer(String customerId, String customerName, String streetAddress, String cityStateZip, String country) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.streetAddress = streetAddress;
        this.cityStateZip = cityStateZip;
        this.country = country;
    }

    String customerId;
    String customerName;
    String streetAddress;
    String cityStateZip;
    String country;

    public String getCustomerName() {
        return customerName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCityStateZip() {
        return cityStateZip;
    }

    public String getCountry() {
        return country;
    }
}
