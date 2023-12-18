package Db;


import Models.Customer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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

}


