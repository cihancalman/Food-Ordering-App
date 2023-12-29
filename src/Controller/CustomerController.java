package Controller;

import Db.Database;
import Models.Customer;
import Views.CustomerGUI;

import java.io.IOException;


public class CustomerController {

    public static String login(String email,String password){
        try {
            for (Customer customer : Database.getCustomers()){
             if (customer.getEmail().equals(email)){
                 if (customer.getPassword().equals(password)){

                     CustomerGUI customerGUI = new CustomerGUI(customer,Database.getRestaurants(customer.getTown()));
                     customerGUI.setVisible(true);

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
    public static  String register(String name,String surname,String email,String password,String town,String address){
        try {
            for (Customer customer :Database.getCustomers()){
                if (customer.getEmail().equals(email)){
                    return "email";
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Database.newCustomer(new Customer(name,surname,email,password,town,address));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CustomerController.login(email, password);
        return "success";
    }
}
