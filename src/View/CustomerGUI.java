package View;

import Db.Database;
import Model.Customer;
import Model.Food;
import Model.Order;
import Model.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.UUID;
import javax.swing.border.EmptyBorder;

public class CustomerGUI  {

    private JFrame frame;
    private JSplitPane panel1;
    private JLabel userNameField;
    private JLabel userTownField;
    private JLabel greetingField;
    private JLabel greetingTownField;
    private JPanel restaurantsFrame;
    private JPanel homePage;
    private JPanel restaurantPage;
    private JButton homePageBtn;
    private JLabel restaurantPageGreeting;
    private JLabel restaurantPageGreeting2;
    private JPanel restaurantDetailFrame;
    private JButton buyBtn;
    private JPanel shoppingCartPanel;
    private JScrollPane shoppingCartScrollPane;
    private JLabel cost;
    private JButton çıkışYapButton;
    private ArrayList<Order> shoppingCart = new ArrayList<Order>();

    int sumPrice = 0;

    public CustomerGUI(Customer customer, ArrayList<Restaurant> restaurants){
        frame = new JFrame("Food Ordering App | " + customer.getName()+" "+customer.getSurname());
        frame.add(panel1);
        frame.setSize(1350
                ,600);
        frame.setResizable(false);

        userNameField.setText(customer.getName()+" "+customer.getSurname());
        userTownField.setText(customer.getTown());

        greetingField.setText("Hoşgeldin" + " " +customer.getName());
        restaurantPageGreeting.setText("Hoşgeldin" + " " +customer.getName());
        greetingTownField.setText("İşte "+ customer.getTown() + " İlçesindeki Restoranlar");

        showHomePage(customer,restaurants);
        showCart();





        homePageBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHomePage(customer,restaurants);
            }
        });
        buyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database.addOrder(shoppingCart);
                shoppingCart.clear();
                showCart();
                JOptionPane.showMessageDialog(null, "Siparişleriniz Alınmıştır.");

            }
        });
        çıkışYapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                LoginGUI login = new LoginGUI();
                login.getFrame().setVisible(true);
            }
        });
    }

    public void showRestaurantDetail(Restaurant restaurant, Customer customer){
        homePage.setVisible(false);
        restaurantPage.setVisible(true);
        restaurantPageGreeting2.setText(restaurant.getRestaurantName() +   " Restoranındaki Ürünler");
        restaurantDetailFrame.removeAll();
        restaurantDetailFrame.setBackground(new Color(229,96,81));
        restaurantDetailFrame.setLayout(new BoxLayout(restaurantDetailFrame,BoxLayout.Y_AXIS));
        /*restaurantDetailFrame.setLayout(new GridLayout((Database.getFoods(restaurant).size()/2)+1,2,0,10) ); */
        restaurantDetailFrame.setBorder(new EmptyBorder(10, 10, 10, 10));

        for (Food food : Database.getFoods(restaurant)) {
            JPanel foodPanel = new JPanel();
            foodPanel.setBackground(new Color(228, 220, 229));
            foodPanel.setForeground(new Color(228, 220, 229));


            foodPanel.setLayout(new BoxLayout(foodPanel, BoxLayout.Y_AXIS));
            JLabel foodNameLabel = new JLabel(food.getName());
            foodNameLabel.setForeground(new Color(229, 26, 0));
            foodNameLabel.setFont(new Font("Inconsolata", Font.PLAIN, 30));
            JLabel foodDescriptionLabel = new JLabel(food.getDescription());

            foodDescriptionLabel.setForeground(new Color(229, 26, 0));
            foodDescriptionLabel.setFont(new Font("Inconsolata", Font.PLAIN, 14));
            JLabel foodPriceAndDelivery = new JLabel(food.getPrice() + " TL" + " - " + food.getDeliveryTime() + " (dk)");
            foodPriceAndDelivery.setForeground(new Color(229, 26, 0));
            foodPriceAndDelivery.setFont(new Font("Inconsolata", Font.PLAIN, 19));
            JButton addBtn = new JButton("Sepete Ekle");

            addBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    shoppingCart.add(new Order(UUID.randomUUID().toString(),food.getName(),food.getPrice(),food.getDeliveryTime(),customer.getName()+" "+customer.getSurname(), customer.getTown(), customer.getFullAdress(),restaurant.getRestaurantName(),restaurant.getEmail(),false ));
                    showCart();
                }
            });

            foodNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            foodDescriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            foodPriceAndDelivery.setAlignmentX(Component.CENTER_ALIGNMENT);
            addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            foodPanel.add(foodNameLabel);
            foodPanel.add(foodDescriptionLabel);
            foodPanel.add(foodPriceAndDelivery);
            foodPanel.add(addBtn);
            foodPanel.add(Box.createVerticalStrut(5));
            restaurantDetailFrame.add(Box.createVerticalStrut(20));
            restaurantDetailFrame.add(foodPanel);

        }
    }


    public void showHomePage(Customer customer,ArrayList<Restaurant> restaurants){

        restaurantPage.setVisible(false);
        homePage.setVisible(true);
        restaurantsFrame.removeAll();
        restaurantsFrame.setLayout(new GridLayout((restaurants.size()/2)+1,2,0,10) );
        restaurantsFrame.setBorder(new EmptyBorder(10, 10, 10, 10));


        restaurants.forEach(restaurant -> {
            JPanel restaurantPanel = new JPanel();
            restaurantPanel.setLayout(new BoxLayout(restaurantPanel,BoxLayout.Y_AXIS));
            JLabel restaurantImage = new JLabel(new ImageIcon("src/Images/restoran.png"));
            JLabel restaurantNameLabel = new JLabel();
            restaurantPanel.setBackground(new Color(229,96,81));
            restaurantNameLabel.setText(restaurant.getRestaurantName());
            restaurantNameLabel.setFont(new Font("Inconsolata", Font.PLAIN, 30));
            restaurantNameLabel.setForeground(new Color(228,220,229));
            JButton restaurantButton = new JButton("Menüleri İncele!");
            restaurantButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("merhaba");
                    showRestaurantDetail(restaurant,customer);
                    System.out.println(restaurant.getEmail());
                }
            });
            restaurantButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            restaurantNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            restaurantImage.setAlignmentX(Component.CENTER_ALIGNMENT);
            restaurantPanel.add(restaurantImage);
            restaurantPanel.add(restaurantNameLabel);
            restaurantPanel.add(restaurantButton);

            restaurantsFrame.add(restaurantPanel);

        });

    }
    public void showCart(){
        sumPrice=0;
        cost.setText("Sepetiniz Boş");
        shoppingCartPanel.removeAll();

        shoppingCartPanel.setBackground(new Color(229,96,81));
        shoppingCartPanel.setLayout(new BoxLayout(shoppingCartPanel,BoxLayout.Y_AXIS));
        shoppingCartPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        shoppingCart.forEach(order -> {

            sumPrice+= Integer.valueOf(order.getFoodPrice());
            cost.setText("Toplam: "+sumPrice+"Tl");
            JPanel orderPanel = new JPanel();
            orderPanel.setBackground(new Color(228, 220, 229));
            orderPanel.setForeground(new Color(228,220,229));

            orderPanel.setLayout(new BoxLayout(orderPanel,BoxLayout.Y_AXIS));

            JLabel orderNameLabel = new JLabel(order.getFoodName());
            orderNameLabel.setForeground(new Color(229, 26, 0));
            orderNameLabel.setFont(new Font("Inconsolata", Font.PLAIN, 15));


            JLabel orderRestaurantLabel = new JLabel( order.getRestaurantName() );

            orderRestaurantLabel.setForeground(new Color(229, 26, 0));
            orderRestaurantLabel.setFont(new Font("Inconsolata", Font.PLAIN, 8));


            JLabel orderPriceLabel = new JLabel(order.getFoodPrice());
            orderPriceLabel.setForeground(new Color(229, 26, 0));
            orderPriceLabel.setFont(new Font("Inconsolata", Font.PLAIN, 10));
            JButton deleteBtn = new JButton("Sepetten Çıkar");


            deleteBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    shoppingCart.remove(order);
                    showCart();
                }
            });



            orderNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            orderRestaurantLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            orderPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            deleteBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            orderPanel.add(orderNameLabel);
            orderPanel.add(orderRestaurantLabel);
            orderPanel.add(orderPriceLabel);
            orderPanel.add(deleteBtn);
            orderPanel.add(Box.createVerticalStrut(5));
            shoppingCartPanel.add(Box.createVerticalStrut(20));
            shoppingCartPanel.add(orderPanel);
            shoppingCartPanel.revalidate();
            shoppingCartPanel.repaint();
            shoppingCartScrollPane.revalidate();
            shoppingCartScrollPane.repaint();




        });



        }
    public JFrame getFrame() {
        return frame;
    }

    }




