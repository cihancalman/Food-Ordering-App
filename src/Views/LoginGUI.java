package Views;

import Controller.CustomerController;
import Controller.RestaurantController;
import Db.Database;
import Helpers.Regex;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginGUI extends JFrame {
    private JPanel panel1;
    private JPanel admin;
    private JPanel restaurant;
    private JPanel customer;
    private JPanel login;
    private JPanel bar;
    private JButton restaurantButton;
    private JButton adminButton;
    private JButton userButton;
    private JPanel customerLogin;
    private JPanel customerRegister;
    private JPasswordField customerLoginPassword;
    private JTextField customerLoginEmail;
    private JButton customerLoginBtn;
    private JButton customerLoginRdrctBtn;
    private JTextField customerRegisterName;
    private JTextField customerRegisterSurname;
    private JTextField customerRegisterEmail;
    private JPasswordField customerRegisterPassword;
    private JComboBox customerRegisterTown;
    private JTextArea customerRegisterFullAdress;
    private JButton customerRegisterBtn;
    private JButton customerRegisterRdrctBtn;
    private JLabel customerLoginMailErr;
    private JLabel customerLoginPassErr;
    private JPanel restaurantLogin;
    private JPasswordField restaurantLoginPassword;
    private JTextField restaurantLoginEmail;
    private JButton restaurantLoginBtn;
    private JButton restaurantLoginRdrctBtn;
    private JLabel restaurantLoginMailErr;
    private JLabel restaurantLoginPassErr;
    private JLabel customerRegisterMailErr;
    private JLabel customerRegisterPassErr;
    private JLabel customerRegisterTownErr;
    private JLabel customerRegisterNameErr;
    private JLabel customerRegisterSurnameErr;
    private JLabel customerRegisterAddresErr;
    private JPanel adminLogin;
    private JPasswordField adminLoginPassword;
    private JTextField adminLoginMail;
    private JLabel adminLoginMailErr;
    private JLabel adminLoginPassErr;
    private JPanel restaurantRegister;
    private JButton restaurantRegisterRdrctBtn;
    private JTextField restaurantRegisterRestaurantName;
    private JTextField restaurantRegisterName;
    private JTextField restaurantRegisterSurname;
    private JTextField restaurantRegisterEmail;
    private JPasswordField restaurantRegisterPassword;
    private JComboBox restaurantRegisterTown;
    private JButton restaurantRegisterBtn;
    private JLabel restaurantRegisterNameErr;
    private JLabel restaurantRegisterSurnameErr;
    private JLabel restaurantRegisterRestauarantNameErr;
    private JLabel restaurantRegisterTownErr;
    private JLabel restaurantRegisterPassErr;
    private JLabel restaurantRegisterMailErr;

    public LoginGUI(){
        add(panel1);
        setSize(900,600);
        setResizable(false);



        restaurantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restaurant.setVisible(true);
                admin.setVisible(false);
                customer.setVisible(false);
            }
        });
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customer.setVisible(true);
                restaurant.setVisible(false);
                admin.setVisible(false);
            }
        });
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                admin.setVisible(true);
                customer.setVisible(false);
                restaurant.setVisible(false);
            }
        });
        // CUSTOMER
        customerLoginRdrctBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerLogin.setVisible(false);
                customerRegister.setVisible(true);
            }
        });
        customerRegisterRdrctBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerRegister.setVisible(false);
                customerLogin.setVisible(true);
            }
        });
        customerLoginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Regex.validateEmail(customerLoginEmail.getText())){
                    customerLoginMailErr.setText("Lütfen Geçerli Bir Email Adresi Girin!");
                    customerLoginMailErr.setVisible(true);
                }else if (customerLoginPassword.getText().length() == 0){
                    customerLoginPassErr.setText("Parola Alanı Dolu Olmalıdır");
                    customerLoginPassErr.setVisible(true);
                }else {
                   String res =CustomerController.login(customerLoginEmail.getText(),(String) customerLoginPassword.getText());

                   switch (res){
                       case "email":
                           customerLoginMailErr.setText("Kullanıcı Bulunamadı");
                           customerLoginMailErr.setVisible(true);
                           break;
                       case "password":
                           customerLoginPassErr.setText("Hatalı Parola");
                           customerLoginPassErr.setVisible(true);
                           break;
                       default:
                           System.out.println("login");
                           break;

                   }
                }
            }
        });


        customerLoginEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (!Regex.validateEmail(customerLoginEmail.getText())){
                    customerLoginMailErr.setText("Lütfen Geçerli Bir Email Adresi Girin!");
                    customerLoginMailErr.setVisible(true);
                }else {
                    customerLoginMailErr.setVisible(false);
                }
            }
        });
        customerLoginPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (customerLoginPassword.getText().length() == 0){
                    customerLoginPassErr.setText("Parola Alanı Dolu Olmalıdır");
                    customerLoginPassErr.setVisible(true);
                }
                else {
                    customerLoginPassErr.setVisible(false);
                }
            }
        });

        // RESTAURANT

        restaurantLoginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Regex.validateEmail(restaurantLoginEmail.getText())){
                    restaurantLoginMailErr.setText("Lütfen Geçerli Bir Email Adresi Girin!");
                    restaurantLoginMailErr.setVisible(true);
                }else if (restaurantLoginPassword.getText().length() == 0){
                    restaurantLoginPassErr.setText("Parola Alanı Dolu Olmalıdır");
                    restaurantLoginPassErr.setVisible(true);
                }else {
                    String res = RestaurantController.login(restaurantLoginEmail.getText(),(String) restaurantLoginPassword.getText());

                    switch (res){
                        case "email":
                            restaurantLoginMailErr.setText("Kullanıcı Bulunamadı");
                            restaurantLoginMailErr.setVisible(true);
                            break;
                        case "password":
                            restaurantLoginPassErr.setText("Hatalı Parola");
                            restaurantLoginPassErr.setVisible(true);
                            break;
                        default:
                            System.out.println("login");
                            break;

                    }
                }
            }
        });
        restaurantLoginPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (restaurantLoginPassword.getText().isEmpty()){
                    restaurantLoginPassErr.setText("Parola Alanı Dolu Olmalıdır");
                    restaurantLoginPassErr.setVisible(true);
                }
                else {
                    restaurantLoginPassErr.setVisible(false);
                }

            }
        });
        restaurantLoginEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (!Regex.validateEmail(restaurantLoginEmail.getText())){
                    restaurantLoginMailErr.setText("Lütfen Geçerli Bir Email Adresi Girin!");
                    restaurantLoginMailErr.setVisible(true);
                }else {
                    restaurantLoginMailErr.setVisible(false);
                }
            }
        });
        //CUSTOMER REGISTER
        customerRegisterEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (!Regex.validateEmail(customerRegisterEmail.getText())){
                    customerRegisterMailErr.setText("Lütfen Geçerli Bir Email Adresi Girin!");
                    customerRegisterMailErr.setVisible(true);
                }else {
                    customerRegisterMailErr.setVisible(false);
                }
            }
        });
        customerRegisterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Regex.validateEmail(customerRegisterEmail.getText())){
                    customerRegisterMailErr.setText("Lütfen Geçerli Bir Email Adresi Girin!");
                    customerRegisterMailErr.setVisible(true);
            }
                 else if (customerRegisterPassword.getText().length() == 0){
                    customerRegisterPassErr.setText("Parola Alanı Dolu Olmalıdır");
                    customerRegisterPassErr.setVisible(true);
                }  else if (customerRegisterName.getText().length() == 0) {
                    customerRegisterNameErr.setText("İsim Alanı Dolu Olmalıdır");
                    customerRegisterNameErr.setVisible(true);

                }
                 else if (customerRegisterSurname.getText().length() == 0) {
                    customerRegisterSurnameErr.setText("Soyisim Alanı Dolu Olmalıdır");
                    customerRegisterSurnameErr.setVisible(true);

                }
                 else if (customerRegisterFullAdress.getText().length() == 0) {
                    customerRegisterAddresErr.setText("Adres Alanı Dolu Olmalıdır");
                    customerRegisterAddresErr.setVisible(true);

                }
                 else if (customerRegisterTown.getSelectedItem() == null) {
                    customerRegisterTownErr.setText("İlçe Alanı Dolu Olmalıdır");
                    customerRegisterTownErr.setVisible(true);

                }
                 else {
                    String res = CustomerController.register(customerRegisterName.getText(),customerRegisterSurname.getText(),customerRegisterEmail.getText(),(String) customerRegisterPassword.getText(),(String) customerRegisterTown.getSelectedItem(),customerRegisterFullAdress.getText());

                    switch (res){
                        case "email":
                            customerRegisterMailErr.setText("Kullanıcı zaten mevcut");
                            break;
                        case "success":
                            System.out.println("register");
                            break;
                        default:
                            break;

                    }
                }
            }
        });
        customerRegisterName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (customerRegisterName.getText().isEmpty()){
                    customerRegisterNameErr.setText("İsim Alanı Dolu Olmalıdır");
                    customerRegisterNameErr.setVisible(true);
                }
                else {
                    customerRegisterNameErr.setVisible(false);
                }
            }
        });
        customerRegisterSurname.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (customerRegisterSurname.getText().isEmpty()){
                    customerRegisterSurnameErr.setText("Soyisim Alanı Dolu Olmalıdır");
                    customerRegisterSurnameErr.setVisible(true);
                }
                else {
                    customerRegisterSurnameErr.setVisible(false);
                }
            }
        });
        customerRegisterPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (customerRegisterPassword.getText().isEmpty()){
                    customerRegisterPassErr.setText("Parola Alanı Dolu Olmalıdır");
                    customerRegisterPassErr.setVisible(true);
                }
                else {
                    customerRegisterPassErr.setVisible(false);
                }
            }
        });
        customerRegisterFullAdress.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (customerRegisterFullAdress.getText().isEmpty()){
                    customerRegisterAddresErr.setText("Adres Alanı Dolu Olmalıdır");
                    customerRegisterAddresErr.setVisible(true);
                }
                else {
                    customerRegisterAddresErr.setVisible(false);
                }
            }
        });
        restaurantLoginRdrctBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restaurantLogin.setVisible(false);
                restaurantRegister.setVisible(true);
            }
        });
        restaurantRegisterRdrctBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restaurantRegister.setVisible(false);
                restaurantLogin.setVisible(true);
            }
        });


        restaurantRegisterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Regex.validateEmail(restaurantRegisterEmail.getText())){
                    restaurantRegisterMailErr.setText("Lütfen Geçerli Bir Email Adresi Girin!");
                    restaurantRegisterMailErr.setVisible(true);
                }
                else if (restaurantRegisterPassword.getText().length() == 0){
                    restaurantRegisterPassErr.setText("Parola Alanı Dolu Olmalıdır");
                    restaurantRegisterPassErr.setVisible(true);
                }  else if (restaurantRegisterName.getText().length() == 0) {
                    restaurantRegisterNameErr.setText("İsim Alanı Dolu Olmalıdır");
                    restaurantRegisterNameErr.setVisible(true);

                }
                else if (restaurantRegisterSurname.getText().length() == 0) {
                    restaurantRegisterSurnameErr.setText("Soyisim Alanı Dolu Olmalıdır");
                    restaurantRegisterSurnameErr.setVisible(true);

                }
                else if (restaurantRegisterRestaurantName.getText().length() == 0) {
                    restaurantRegisterRestauarantNameErr.setText("Restorant İsim Alanı Dolu Olmalıdır");
                    restaurantRegisterRestauarantNameErr.setVisible(true);

                }
                else if (restaurantRegisterTown.getSelectedItem() == null) {
                    customerRegisterTownErr.setText("İlçe Alanı Dolu Olmalıdır");
                    customerRegisterTownErr.setVisible(true);

                }
                else {
                    String res = RestaurantController.register(restaurantRegisterName.getText(),restaurantRegisterSurname.getText(),restaurantRegisterEmail.getText(),(String) restaurantRegisterPassword.getText(),(String) restaurantRegisterTown.getSelectedItem(),restaurantRegisterRestaurantName.getText());
                    switch (res){
                        case "email":
                            restaurantRegisterMailErr.setText("Kullanıcı zaten mevcut");
                            break;
                        case "success":
                            System.out.println("register");
                            break;
                        default:
                            break;

                    }
                }
            }
        });
        restaurantRegisterName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (restaurantRegisterName.getText().isEmpty()){
                    restaurantRegisterNameErr.setText("İsim Alanı Dolu Olmalıdır");
                    restaurantRegisterNameErr.setVisible(true);
                }
                else {
                    restaurantRegisterNameErr.setVisible(false);
                }
            }
        });
        restaurantRegisterSurname.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (restaurantRegisterSurname.getText().isEmpty()){
                    restaurantRegisterSurnameErr.setText("Soyisim Alanı Dolu Olmalıdır");
                    restaurantRegisterSurnameErr.setVisible(true);
                }
                else {
                    restaurantRegisterSurnameErr.setVisible(false);
                }
            }
        });
        restaurantRegisterPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (restaurantRegisterPassword.getText().isEmpty()){
                    restaurantRegisterPassErr.setText("Parola Alanı Dolu Olmalıdır");
                    restaurantRegisterPassErr.setVisible(true);
                }
                else {
                    restaurantRegisterPassErr.setVisible(false);
                }
            }
        });
        restaurantRegisterRestaurantName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (restaurantRegisterRestaurantName.getText().isEmpty()){
                    restaurantRegisterRestauarantNameErr.setText("Restorant İsim Alanı Dolu Olmalıdır");
                    restaurantRegisterRestauarantNameErr.setVisible(true);
                }
                else {
                    restaurantRegisterRestauarantNameErr.setVisible(false);
                }
            }
        });
        restaurantRegisterEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (!Regex.validateEmail(restaurantRegisterEmail.getText())){
                    restaurantRegisterMailErr.setText("Lütfen Geçerli Bir Email Adresi Girin!");
                    restaurantRegisterMailErr.setVisible(true);
                }else {
                    restaurantRegisterMailErr.setVisible(false);
                }
            }
        });

        /*

         */
    }

}
