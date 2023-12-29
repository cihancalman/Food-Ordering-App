package Db;


import Models.Customer;
import Models.Food;
import Models.Order;
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

    public static void addFood(Food food, Restaurant restaurant){
        try {
            FileWriter fileWriter = new FileWriter(restaurantFolder+ File.separator + restaurant.getEmail() + File.separator+ "foods.txt",true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(food.toString());
            bufferedWriter.newLine();
            bufferedWriter.close();

        }
        catch (IOException e){
            System.out.println(e);
        }
    }

    public static ArrayList<Food> getFoods(Restaurant restaurant){

        ArrayList<Food> foods = new ArrayList<Food>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(restaurantFolder+ File.separator + restaurant.getEmail() + File.separator+ "foods.txt"))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] values =line.split(";");
                foods.add(new Food(values[0],values[1],values[2],values[3]));
            }


        } catch (IOException e) {
            System.out.println(e);
        }
        return foods;



    }

    public static void addOrder(ArrayList<Order> orders){
        orders.forEach(order -> {
            try {
                FileWriter fileWriter = new FileWriter(restaurantFolder+ File.separator + order.getRestaurantMail() + File.separator+ "orders.txt",true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(order.toString());
                bufferedWriter.newLine();
                bufferedWriter.close();

            }
            catch (IOException e){
                System.out.println(e);
            }

        });
    }

    public static ArrayList<Order> getOrders(Restaurant restaurant){
        ArrayList<Order> orders = new ArrayList<Order>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(restaurantFolder+ File.separator + restaurant.getEmail() + File.separator+ "orders.txt"))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] values =line.split(";");
                orders.add(new Order(values[0],values[1],values[2],values[3],values[4],values[5],values[6],restaurant.getRestaurantName(),restaurant.getEmail(),Boolean.valueOf(values[7])));
            }


        } catch (IOException e) {
            System.out.println(e);
        }
        return orders;
    }

    public static ArrayList<Order> getOrders(Restaurant restaurant,int x){
        ArrayList<Order> orders = new ArrayList<>();
        if (x == 0){
            getOrders(restaurant).forEach(order -> {
                if (Boolean.FALSE.equals(order.isDelivered())){
                    orders.add(order);
                }
            });
        } else if (x==1) {
            getOrders(restaurant).forEach(order -> {
                if (Boolean.TRUE.equals(order.isDelivered())){
                    orders.add(order);
                }
            });
        }
        return orders;
    }
    public static void updateOrders(Order order) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(restaurantFolder+ File.separator + order.getRestaurantMail() + File.separator+ "orders.txt"));
        String line;
        StringBuilder newContent = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains(order.getId())) {

                String newLine = line.replace("false", "true"); // Değiştirme işlemi
                newContent.append(newLine).append("\n");
            } else {
                newContent.append(line).append("\n");
            }
        }
        bufferedReader.close();

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(restaurantFolder+ File.separator + order.getRestaurantMail() + File.separator+ "orders.txt"));
        bufferedWriter.write(newContent.toString());
        bufferedWriter.close();
    }





}


