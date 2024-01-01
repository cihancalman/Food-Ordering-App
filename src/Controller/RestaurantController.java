package Controller;

import Db.Database;

import Model.Food;
import Model.Restaurant;
import View.RestaurantGUI;

import java.io.IOException;
import java.util.UUID;

public class RestaurantController {

    public static String login(String email,String password){
        try {
            for (Restaurant restaurant : Database.getRestaurants()){
                if (restaurant.getEmail().equals(email)){
                    if (restaurant.getPassword().equals(password)){

                        RestaurantGUI restaurantGUI = new RestaurantGUI(restaurant);
                        restaurantGUI.getFrame().setVisible(true);
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

    public static  String addFood(String name,String description,String price,String deliveryTime,Restaurant restaurant) {
        for (Food food : Database.getFoods(restaurant)) {
            if (food.getName().equals(name)) {
                return "err";
            }
        }

        Database.addFood(new Food(UUID.randomUUID().toString(),name,description,price,deliveryTime),restaurant);
        return "success";
    }}
