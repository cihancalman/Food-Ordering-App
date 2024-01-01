package Db;


import Model.Customer;
import Model.Food;
import Model.Order;
import Model.Restaurant;

import java.io.*;
import java.util.ArrayList;

public abstract class Database {

    private static final String customersPath = "Database/customers.txt";
    private static final String restaurantsPath = "Database/restaurants.txt";
    private static final String restaurantFolder = "Database/Restaurants";


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
                foods.add(new Food(values[0],values[1],values[2],values[3],values[4]));
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
        File inputFile =new File(restaurantFolder+ File.separator + order.getRestaurantMail() + File.separator+ "orders.txt");
        File outputFile = new File(restaurantFolder+ File.separator + order.getRestaurantMail() + File.separator+ "orders_temp.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile));
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains(order.getId())) {

                line = line.replace("false", "true");
            }
            bufferedWriter.write(line + System.lineSeparator());
        }
        bufferedReader.close();
        bufferedWriter.close();

        if (inputFile.delete()) {
            if (!outputFile.renameTo(inputFile)) {
                System.out.println("err");
            }
        } else {
            System.out.println("err");
        }


    }

    public static void deleteFood(Food food,Restaurant restaurant){
        try {
            File inputFile = new File(restaurantFolder+ File.separator + restaurant.getEmail() + File.separator+ "foods.txt");
            File tempFile = new File(restaurantFolder+ File.separator + restaurant.getEmail() + File.separator+ "foods_temp.txt");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {
                if (!currentLine.contains(food.getId())) {
                    bufferedWriter.write(currentLine + System.getProperty("line.separator"));
                }
            }

            bufferedWriter.close();
            bufferedReader.close();

            if (inputFile.delete()) {
                if (!tempFile.renameTo(inputFile)) {
                    System.out.println("err");
                }
            } else {
                System.out.println("err");
            }
    }catch (IOException ex) {
            System.out.println("err");
        }





}}


