package Model;

public class Order {
    private String id;
    private String foodName;
    private String foodPrice;
    private String deliveryTime;

    private String customerName;
    private String customerTown;
    private String customerFullAddress;
    private String restaurantName;
    private String restaurantMail;

    private boolean isDelivered ;

    public Order(String id, String foodName, String foodPrice, String deliveryTime, String customerName, String customerTown, String customerFullAddress, String restaurantName, String restaurantMail, boolean isDelivered) {
        this.id = id;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.deliveryTime = deliveryTime;
        this.customerName = customerName;
        this.customerTown = customerTown;
        this.customerFullAddress = customerFullAddress;
        this.restaurantName = restaurantName;
        this.restaurantMail = restaurantMail;
        this.isDelivered = isDelivered;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerTown() {
        return customerTown;
    }

    public void setCustomerTown(String customerTown) {
        this.customerTown = customerTown;
    }

    public String getCustomerFullAddress() {
        return customerFullAddress;
    }

    public void setCustomerFullAddress(String customerFullAddress) {
        this.customerFullAddress = customerFullAddress;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantMail() {
        return restaurantMail;
    }

    public void setRestaurantMail(String restaurantMail) {
        this.restaurantMail = restaurantMail;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    @Override
    public String toString() {
        return id+";"+foodName+";"+foodPrice+";"+deliveryTime+";"+customerName+";"+customerTown+";"+ customerFullAddress +";"+String.valueOf(isDelivered);
    }
}
