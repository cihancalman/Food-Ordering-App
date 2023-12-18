package Test;

import Db.Database;
import Models.Customer;
import Models.Restaurant;

import java.io.IOException;
import java.util.ArrayList;


public class Test {

    public static void main(String[] args) throws IOException {
        Database.newRestaurant(new Restaurant("cihan","calman","cihan@bilmemne.com","1234","Gaziosmanpaşa","cihan'ın müthiş restorantı"));
        Database.newRestaurant(new Restaurant("mustafa","keleş","mustafa@bilmemne.com","456","Bağcılar","Mustafanın'ın müthiş restorantı"));
        Database.newRestaurant(new Restaurant("yaremir","yücel","yaremir@bilmemne.com","4527","Göztepe","Yaremirin'ın müthiş restorantı"));
        Database.newRestaurant(new Restaurant("ali","biten","ali@bilmemne.com","1234","Göztepe","ali'ın müthiş restorantı"));

        Database.getRestaurants().forEach(restaurant -> System.out.println(restaurant.getEmail()));
        Database.getRestaurants("Göztepe").forEach(restaurant -> System.out.println(restaurant.getRestaurantName()));

    }}
