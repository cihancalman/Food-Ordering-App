package Db;


import Models.Customer;
import Models.Restaurant;

import java.io.*;
import java.util.ArrayList;

public class Database {

    private static final String customersPath = "Database/customers.txt";
    private static final String restaurantsPath = "Database/restaurants.txt";
    private static final String restaurantFolder = "Database/Restaurants";

    public static final String[] towns = {"Adalar", "Arnavutköy", "Ataşehir", "Avcılar", "Bağcılar", "Bahçelievler", "Bakırköy", "Başakşehir", "Bayrampaşa", "Beşiktaş", "Beykoz", "Beylikdüzü", "Beyoğlu", "Büyükçekmece", "Çatalca", "Çekmeköy", "Esenler", "Esenyurt", "Eyüpsultan", "Fatih", "Gaziosmanpaşa", "Güngören", "Kadıköy", "Kağıthane", "Kartal", "Küçükçekmece", "Maltepe", "Pendik", "Sancaktepe", "Sarıyer", "Silivri", "Sultanbeyli", "Sultangazi", "Şile", "Şişli", "Tuzla", "Ümraniye", "Üsküdar","Zeytinburnu"};

    public static void newCustomer(Customer customer) throws IOException {

       try {
           FileWriter fileWriter = new FileWriter(customersPath,true);
           BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
           bufferedWriter.write(customer.toString());
           bufferedWriter.newLine();
           bufferedWriter.close();

       }
        catch (IOException e){
            System.out.println(e);
        }






    }

    public static ArrayList<Customer> getCustomers() throws IOException {

            ArrayList<Customer> customers = new ArrayList<Customer>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(customersPath))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] values =line.split(";");
                customers.add(new Customer(values[0],values[1],values[2],values[3],values[4],values[5]));
            }


        } catch (IOException e) {
            System.out.println(e);
        }
        return customers;


    }


    //todo

    public static void newRestaurant(Restaurant restaurant) throws IOException{
        try {
            FileWriter fileWriter = new FileWriter(restaurantsPath,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(restaurant.toString());
            bufferedWriter.newLine();
            bufferedWriter.close();

            // create new folder
            File folder = new File(restaurantFolder+File.separator+restaurant.getEmail());
            File orders = new File(restaurantFolder+File.separator+restaurant.getEmail()+File.separator + "orders.txt");
            File foods = new File(restaurantFolder+File.separator+restaurant.getEmail()+File.separator + "foods.txt");

            folder.mkdir();
            orders.createNewFile();
            foods.createNewFile();




        }
        catch (IOException e){
            System.out.println(e);
        }

    }

    public static ArrayList<Restaurant> getRestaurants() throws IOException{
        ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(restaurantsPath))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] values =line.split(";");
                restaurants.add(new Restaurant(values[0],values[1],values[2],values[3],values[4],values[5]));
            }


        } catch (IOException e) {
            System.out.println(e);
        }
        return restaurants;
    }

    public static ArrayList<Restaurant> getRestaurants(String town) throws IOException{
        ArrayList<Restaurant> filteredRestaurants = new ArrayList<Restaurant>();
        getRestaurants().forEach(restaurant -> {
            if(town.equals(restaurant.getTown())){
                filteredRestaurants.add(restaurant);
            }
        });
        return  filteredRestaurants;
    }





}


