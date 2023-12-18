package Models;

public class Restaurant extends User{
    private String restaurantName;

    public Restaurant(String name, String surname, String email, String password, String town, String restaurantName) {
        super(name, surname, email, password, town);
        this.restaurantName = restaurantName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    @Override
    public String toString() {
        return this.getName()+";"+this.getSurname()+";"+this.getEmail()+";"+this.getPassword()+";"+this.getTown()+";" + this.getRestaurantName() ;
    }
}
