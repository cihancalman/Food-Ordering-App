package View;

import Controller.RestaurantController;
import Db.Database;
import Controller.Regex;
import Model.Order;
import Model.Restaurant;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class RestaurantGUI  {
    private JFrame frame;
    private JLabel restaurantNameField;
    private JLabel restaurantResNameField;
    private JPanel homePage;
    private JLabel greetingField;
    private JSplitPane splitPane;
    private JButton restaurantFoodBtn;
    private JPanel foodPage;
    private JTabbedPane tabbedPane1;
    private JButton restaurantAddFoodBtn;
    private JTextField restaurantAddFoodPrice;
    private JTextField restaurantAddFoodName;
    private JComboBox restaurantAddFoodDeliveryTime;
    private JTextField restaurantAddFoodDescription;
    private JLabel restaurantAddFoodNameErr;
    private JLabel restaurantAddFoodDescriptErr;
    private JLabel restaurantAddFoodPriceErr;
    private JPanel restaurantFoodsPanel;
    private JPanel orderPage;
    private JButton ordersBtn;
    private JTabbedPane tabbedPane2;
    private JPanel completedOrders;
    private JPanel uncompletedOrders;
    private JButton logoutBtn;
    private JLabel greetingField1;
    private JLabel greetingField2;

    public RestaurantGUI(Restaurant restaurant){

        frame = new JFrame("Food Ordering App | " + restaurant.getRestaurantName());
        frame.add(splitPane);
        frame.setSize(1070
                ,600);
        frame.setResizable(false);
        restaurantNameField.setText(restaurant.getName() +" "+ restaurant.getSurname());
        restaurantResNameField.setText(restaurant.getRestaurantName());




        greetingField1.setText("Hoşgeldin "+ restaurant.getName());
        greetingField2.setText("Hoşgeldin " + restaurant.getName());






        restaurantFoodsPanel.setLayout(new BoxLayout(restaurantFoodsPanel,BoxLayout.X_AXIS));









        restaurantFoodBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                orderPage.setVisible(false);
                foodPage.setVisible(true);
            }
        });

        restaurantAddFoodBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (restaurantAddFoodName.getText().length() == 0){
                    restaurantAddFoodNameErr.setText("Menü İsim Alanı Dolu Olmalıdır!");
                    restaurantAddFoodNameErr.setVisible(true);
                }
                else if (restaurantAddFoodDescription.getText().length() ==0){
                    restaurantAddFoodDescriptErr.setText("Menü Açıklama Alanı Dolu Olmalıdır!");
                    restaurantAddFoodDescriptErr.setVisible(true);
                } else if (!Regex.validatePrice(restaurantAddFoodPrice.getText())) {
                    restaurantAddFoodPriceErr.setText("Lütfen Geçerli Bir Fiyat Girin!");
                    restaurantAddFoodPriceErr.setVisible(true);

                }
                else {
                    String res = RestaurantController.addFood(restaurantAddFoodName.getText(),restaurantAddFoodDescription.getText(),restaurantAddFoodPrice.getText(), (String) restaurantAddFoodDeliveryTime.getSelectedItem(),restaurant);

                    switch (res){
                        case "err":
                            restaurantAddFoodNameErr.setText("Menü zaten mevcut");
                            restaurantAddFoodNameErr.setVisible(true);
                            break;
                        case "success":
                            System.out.println("success");
                            break;
                        default:
                            break;

                    }

                }
            }
        });
        restaurantAddFoodName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (restaurantAddFoodName.getText().isEmpty()){
                    restaurantAddFoodNameErr.setText("Menü İsim Alanı Dolu Olmalıdır!");

                    restaurantAddFoodNameErr.setVisible(true);
                }
                else {
                    restaurantAddFoodNameErr.setVisible(false);
                }
            }
        });
        restaurantAddFoodDescription.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (restaurantAddFoodDescription.getText().isEmpty()){
                    restaurantAddFoodDescriptErr.setText("Menü Açıklama Alanı Dolu Olmalıdır");

                    restaurantAddFoodDescriptErr.setVisible(true);
                }
                else {
                    restaurantAddFoodDescriptErr.setVisible(false);
                }
            }
        });
        restaurantAddFoodPrice.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (!Regex.validatePrice(restaurantAddFoodPrice.getText())){
                    restaurantAddFoodPriceErr.setText("Lütfen Geçerli Bir Fiyat Girin!");
                    restaurantAddFoodPriceErr.setVisible(true);
                }else {
                    restaurantAddFoodPriceErr.setVisible(false);
                }
            }
        });
        tabbedPane1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
                int selectedIndex = sourceTabbedPane.getSelectedIndex();
                if(selectedIndex == 1) {



                    showFoods(restaurant);




                }
            }
        });
        tabbedPane2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
                int selectedIndex = sourceTabbedPane.getSelectedIndex();
                if(selectedIndex == 1) {



                    showUncompletedOrders(restaurant);

                } else if (selectedIndex ==0) {
                    showCompletedOrders(restaurant);
                }
            }
        });
        ordersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                foodPage.setVisible(false);

                orderPage.setVisible(true);
                showCompletedOrders(restaurant);


            }
        });
        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                LoginGUI loginGUI = new LoginGUI();
                loginGUI.getFrame().setVisible(true);
            }
        });
    }

    public void showFoods(Restaurant restaurant){
        restaurantFoodsPanel.removeAll();

        restaurantFoodsPanel.setBackground(new Color(229, 96, 81));
        restaurantFoodsPanel.setLayout(new BoxLayout(restaurantFoodsPanel, BoxLayout.Y_AXIS));

        restaurantFoodsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        restaurantFoodsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        Database.getFoods(restaurant).forEach(food -> {

            JPanel myPanel = new JPanel();
            myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

            JLabel foodNameLabel = new JLabel( "Ürün Adı: " +food.getName());
            foodNameLabel.setForeground(new Color(229, 26, 0));
            foodNameLabel.setFont(new Font("Inconsolata", Font.PLAIN, 18));
            JLabel foodDescriptionLabel = new JLabel("Ürün Açıklaması: "+food.getDescription());
            foodDescriptionLabel.setForeground(new Color(229, 26, 0));
            foodDescriptionLabel.setFont(new Font("Inconsolata", Font.PLAIN, 18));
            JLabel foodPriceLabel = new JLabel("Ürün Fiyatı: "+food.getPrice());
            foodPriceLabel.setForeground(new Color(229, 26, 0));
            foodPriceLabel.setFont(new Font("Inconsolata", Font.PLAIN, 18));
            JLabel foodDeliveryLabel = new JLabel("Teslimat Süresi :"+ food.getDeliveryTime() + " (dk)");
            foodDeliveryLabel.setForeground(new Color(229, 26, 0));
            foodDeliveryLabel.setFont(new Font("Inconsolata", Font.PLAIN, 18));
            JButton deleteBtn = new JButton("Ürünü Sil");

            deleteBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Database.deleteFood(food,restaurant);
                    showFoods(restaurant);
                    /*showCompletedOrders(restaurant);
                    showUncompletedOrders(restaurant);*/

                }
            });

            foodNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            foodDescriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            foodPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            foodDeliveryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            deleteBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            myPanel.add(foodNameLabel);
            myPanel.add(foodDescriptionLabel);
            myPanel.add(foodPriceLabel);
            myPanel.add(foodDeliveryLabel);
            myPanel.add(deleteBtn);



            restaurantFoodsPanel.add(myPanel);
            restaurantFoodsPanel.add(Box.createVerticalStrut(20));
            myPanel.revalidate();
            myPanel.repaint();
            myPanel.add(Box.createVerticalStrut(5));
            restaurantFoodsPanel.add(Box.createVerticalStrut(20));
            restaurantFoodsPanel.revalidate();
            restaurantFoodsPanel.repaint();







        });
    }
    public void showCompletedOrders(Restaurant restaurant) {
        completedOrders.removeAll();
        completedOrders.setBackground(new Color(229, 96, 81));
        completedOrders.setLayout(new BoxLayout(completedOrders, BoxLayout.Y_AXIS));

        completedOrders.setBorder(new EmptyBorder(10, 10, 10, 10));

        for (Order order : Database.getOrders(restaurant,1)) {
            JPanel orderPanel = new JPanel();
            orderPanel.setBackground(new Color(228, 220, 229));
            orderPanel.setForeground(new Color(228, 220, 229));


            orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
            JLabel orderFoodNameLabel = new JLabel(order.getFoodName());
            orderFoodNameLabel.setForeground(new Color(229, 26, 0));
            orderFoodNameLabel.setFont(new Font("Inconsolata", Font.PLAIN, 30));
            JLabel orderCustomerAddressLabel = new JLabel(order.getCustomerFullAddress() + " - " + order.getCustomerTown());

            orderCustomerAddressLabel.setForeground(new Color(229, 26, 0));
            orderCustomerAddressLabel.setFont(new Font("Inconsolata", Font.PLAIN, 14));
            JLabel orderCustomerNameAndPrice = new JLabel(order.getCustomerName() + " - " + order.getFoodPrice() + " (TL)");
            orderCustomerNameAndPrice.setForeground(new Color(229, 26, 0));
            orderCustomerNameAndPrice.setFont(new Font("Inconsolata", Font.PLAIN, 19));
            JLabel completedLabel = new JLabel("Tamamlanmış");
            completedLabel.setForeground(new Color(229, 26, 0));
            completedLabel.setFont(new Font("Inconsolata", Font.PLAIN, 19));



            orderFoodNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            orderCustomerAddressLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            orderCustomerNameAndPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
            completedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            orderPanel.add(orderFoodNameLabel);
            orderPanel.add(orderCustomerAddressLabel);
            orderPanel.add(orderCustomerNameAndPrice);
            orderPanel.add(completedLabel);
            orderPanel.add(Box.createVerticalStrut(5));
            completedOrders.add(Box.createVerticalStrut(20));
            completedOrders.add(orderPanel);
        }

    }

    public void showUncompletedOrders(Restaurant restaurant){
        uncompletedOrders.removeAll();
        uncompletedOrders.setBackground(new Color(229, 96, 81));
        uncompletedOrders.setLayout(new BoxLayout(uncompletedOrders, BoxLayout.Y_AXIS));

        uncompletedOrders.setBorder(new EmptyBorder(10, 10, 10, 10));
        for (Order order : Database.getOrders(restaurant,0)) {
            JPanel orderPanel2 = new JPanel();
            orderPanel2.setBackground(new Color(228, 220, 229));
            orderPanel2.setForeground(new Color(228, 220, 229));


            orderPanel2.setLayout(new BoxLayout(orderPanel2, BoxLayout.Y_AXIS));
            JLabel orderFoodNameLabel = new JLabel(order.getFoodName());
            orderFoodNameLabel.setForeground(new Color(229, 26, 0));
            orderFoodNameLabel.setFont(new Font("Inconsolata", Font.PLAIN, 30));
            JLabel orderCustomerAddressLabel = new JLabel(order.getCustomerFullAddress() + " - " + order.getCustomerTown());

            orderCustomerAddressLabel.setForeground(new Color(229, 26, 0));
            orderCustomerAddressLabel.setFont(new Font("Inconsolata", Font.PLAIN, 14));
            JLabel orderCustomerNameAndPrice = new JLabel(order.getCustomerName() + " - " + order.getFoodPrice() + " (TL)");
            orderCustomerNameAndPrice.setForeground(new Color(229, 26, 0));
            orderCustomerNameAndPrice.setFont(new Font("Inconsolata", Font.PLAIN, 19));
            JButton completeBtn = new JButton("Siparişi Tamamla");

            completeBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        Database.updateOrders(order);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    showCompletedOrders(restaurant);
                    showUncompletedOrders(restaurant);

                }
            });


            orderFoodNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            orderCustomerAddressLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            orderCustomerNameAndPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
            completeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            orderPanel2.add(orderFoodNameLabel);
            orderPanel2.add(orderCustomerAddressLabel);
            orderPanel2.add(orderCustomerNameAndPrice);
            orderPanel2.add(completeBtn);
            orderPanel2.add(Box.createVerticalStrut(5));
            uncompletedOrders.add(Box.createVerticalStrut(20));
            uncompletedOrders.add(orderPanel2);
            uncompletedOrders.revalidate();
            uncompletedOrders.repaint();
        }
    }

    public JFrame getFrame() {
        return frame;
    }
}

