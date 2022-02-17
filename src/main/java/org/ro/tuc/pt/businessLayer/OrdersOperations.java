package org.ro.tuc.pt.businessLayer;

import org.ro.tuc.pt.dataAccessLayer.ConnectionFactory;
import org.ro.tuc.pt.dataAccessLayer.DataAccessClass;
import org.ro.tuc.pt.model.Client;
import org.ro.tuc.pt.model.Order;
import org.ro.tuc.pt.model.Product;
import org.ro.tuc.pt.presentation.MainFrame;
import org.ro.tuc.pt.presentation.OrderViewPanel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class will be used for managing order operations.
 * It reads from and writes to the order table in the database.
 * @author Chereji Iulia
 */
public class OrdersOperations {
    private DataAccessClass<Order> orderDAO;
    private MainFrame mainFrame;
    private int newID;
    private float totalPrice;
    private String dateAndTime;
    private ArrayList<Order> orders;
    private Client client;

    /**
     * Creates a new instance of OrdersOperations.
     * @param connectionFactory is the object holding the connection to the database.
     * @param mainFrame is the main window of the application, which will have to be updated in this class when client changes are made.
     */
    public OrdersOperations(ConnectionFactory connectionFactory, MainFrame mainFrame)
    {
        this.mainFrame=mainFrame;
        orderDAO= new DataAccessClass<Order>(connectionFactory, new Order());
        ArrayList<Order> ordersNotUsed = orderDAO.findAll();
        newID=ordersNotUsed.get(ordersNotUsed.size()-1).getId()+1;
        totalPrice=0;
        dateAndTime="";
        orders=new ArrayList<>();
    }

    /**
     * It deletes all the orders that were not yet placed.
     * It is used every time the orders window is left so all unfinished orders will be lost.
     */
    public void reset()
    {
        totalPrice=0;
        dateAndTime="";
        orders=new ArrayList<>();
    }

    /**
     * @return the id that will be assigned to the next order introduced into the database.
     */
    public int getNewID()
    {
        return newID;
    }

    /**
     * @return the total price of all the items that are currently in the 'cart'.
     */
    public float getTotalPrice() {
        return totalPrice;
    }

    /**
     * Attempts to add a new product to the order.
     * @param product the object of Product type that will be added.
     * @param quantity the quantity of the desired product.
     * @return 0 if the product was successfully added to the order, -1 if the quantity is larger than the available stock.
     */
    public int addProduct(Product product, int quantity)
    {
        int i=0;
        while(i<orders.size() && orders.get(i).getIdProduct()!=product.getId())
            i++;

        int q;
        if(i==orders.size()) //this item is not here
        {
            if(product.getStock()<quantity) return -1;
            Order order= new Order();
            order.setIdProduct(product.getId());
            order.setQuantity(quantity);
            order.setPrice(product.getPrice()*quantity);
            orders.add(order);
            totalPrice+=product.getPrice()*quantity;
            return 0; //ok
        }
        if((q=orders.get(i).getQuantity()+quantity)>product.getStock()) return -1;
        //we can add it
        totalPrice=totalPrice- orders.get(i).getPrice();
        orders.get(i).setQuantity(q);
        orders.get(i).setPrice(product.getPrice()*q);
        totalPrice=totalPrice+ orders.get(i).getPrice();
        return 0; //ok
    }

    /**
     * Used to display the current status of the unfinished order.
     * @param productsOperations object of ProductsOperations type, needed to get additional information abut the products.
     * @return an object of String type to be displayed in the GUI, containing the total price, the names, prices and quantities of the products in the order.
     */
    public String toStringGUI(ProductsOperations productsOperations)
    {
        String result="Total price: "+totalPrice;
        for(int i=0;i< orders.size();i++)
        {
            Order order=orders.get(i);
            int idProduct=order.getIdProduct();
            Product product = productsOperations.getProductByID(idProduct);
            result= result + "\n" + product.getName() + ", " + order.getQuantity()+", price: "+order.getPrice();
        }

        return result;
    }

    private void createBill(ProductsOperations productsOperations)
    {
        File billFile = new File("Bill_"+newID+".txt");
        try {
            FileWriter fw = new FileWriter(billFile);
            PrintWriter pw = new PrintWriter(fw);
            String str= "Order with id: "+newID +"\n\nClient: "+client.toStringBill()+"\n\nDate and time: "+dateAndTime+"\n\nTotal price: "+totalPrice+"\n\nProducts:\n\n";
            for(int i=0;i< orders.size();i++) {
                Product product = productsOperations.getProductByID(orders.get(i).getIdProduct());
                str=str+product.toString() +", quantity: "+orders.get(i).getQuantity()+", total price: "+orders.get(i).getPrice()+"\n";
            }

            pw.append(str);
            pw.close();
        } catch (IOException e) {System.out.println("ERROR writing to bill\n"); e.printStackTrace();}
    }

    /**
     * Used when the user attempts to place an order.
     * It sets the idClient and the dateAndTime of the orders, reduces the stock of the products, updates the database, creates the newID for the next order and creates a bill.
     * @param productsOperations object of ProductsOperations type, needed to get additional information about the products.
     */
    public void placeOrder(ProductsOperations productsOperations)
    {
        long millis= System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        java.sql.Time time = new java.sql.Time(millis);
        dateAndTime= date + " " + time;
        client = (Client)(((OrderViewPanel)mainFrame.panels[2]).clientsComboBox.getSelectedItem());
        int idClient = client.getId();
        for(int i=0;i< orders.size();i++)
        {
            Order order= orders.get(i);
            order.setDateAndTime(dateAndTime);
            order.setId(newID);
            order.setIdClient(idClient);
            orderDAO.insert(order);
            Product product = productsOperations.getProductByID(order.getIdProduct());
            product.setStock(product.getStock()-order.getQuantity());
            productsOperations.updateProduct(product);
            createBill(productsOperations);
        }
        reset();
        newID++;
    }

}
