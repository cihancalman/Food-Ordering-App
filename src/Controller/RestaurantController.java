package Controller;

import Db.Database;

import Models.Restaurant;

import java.io.IOException;

public class RestaurantController {

    public static String login(String email,String password){
        try {
            for (Restaurant restaurant : Database.getRestaurants()){
                if (restaurant.getEmail().equals(email)){
                    if (restaurant.getPassword().equals(password)){

                        //TODO
                        return "success";
                    }
                    return "password";
                }

            }
            return "email";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String register(String name,String surname,String email,String password,String town,String restaurantName){
        try {
            for (Restaurant restaurant: Database.getRestaurants()) {
                if (restaurant.getEmail().equals(email)){
                    return "email";
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Database.newRestaurant(new Restaurant(name,surname,email,password,town,restaurantName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        RestaurantController.login(email, password);
        return "success";
    }
}
