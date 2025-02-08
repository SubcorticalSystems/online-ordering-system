public class Product {
    public Product(String productId,
                   String productCategory,
                   String productDescription,
                   String soldBy,
                   double price,
                   OrderProcessor.Condition condition) {
        this.productId = productId;
        this.productCategory = productCategory;
        this.productDescription = productDescription;
        this.soldBy = soldBy;
        this.price = price;
        this.condition = condition;

        System.out.println(getPrice());
    }

     String productId;
     String productCategory;
     String productDescription;
     String soldBy;
     double price;
     OrderProcessor.Condition condition;



    void setProductId(String productId) {
        this.productId = productId;
    }
    void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
    void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    void setSoldBy(String soldBy) {
        this.soldBy = soldBy;
    }
    void setPrice(double price) {
        this.price = price;
    }
    void setCondition(OrderProcessor.Condition condition) {
        this.condition = condition;
    }
    public String getProductId() {
        return productId;
    }
    public String getProductCategory() {
        return productCategory;
    }
    public String getProductDescription() {
        return productDescription;
    }
    public String getSoldBy() {
        return soldBy;
    }
    public double getPrice() {
        return this.price;
    }
    @Override
    public String toString() {
        return "\nCategory: " + getProductCategory()  + "\n" +  getProductDescription() + "\nSeller: "
                + getSoldBy() + "\n$" + getPrice() + "\nCondition: "  + condition;
    }

}