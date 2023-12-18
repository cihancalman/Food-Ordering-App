package Db;


import Models.Customer;

import java.io.*;
import java.util.ArrayList;

public class Database {

    public static final String customerPath = "Database/customers.txt";


    public static void newCustomer(Customer customer) throws IOException {

       try {
           FileWriter fileWriter = new FileWriter(customerPath,true);
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
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(customerPath))) {
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


}


