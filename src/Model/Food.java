package Model;

public class Food {

    private String id;
    private String name;
    private String description;

    private String price;
    private String deliveryTime;


    public Food(String id,String name, String description, String price, String deliveryTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.deliveryTime = deliveryTime;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }





    @Override
    public String toString() {
        return this.getId()+";"+this.getName()+";"+this.getDescription()+";"+this.getPrice()+";"+ this.getDeliveryTime();
    }
}
