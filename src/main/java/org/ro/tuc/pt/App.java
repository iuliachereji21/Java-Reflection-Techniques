package org.ro.tuc.pt;

import org.ro.tuc.pt.businessLayer.ClientsOperations;
import org.ro.tuc.pt.businessLayer.OrdersOperations;
import org.ro.tuc.pt.businessLayer.ProductsOperations;
import org.ro.tuc.pt.dataAccessLayer.ConnectionFactory;
import org.ro.tuc.pt.presentation.*;

/**
 * This class contains the main method of the application.
 * @author Chereji Iulia
 */
public class App 
{
    /**
     * the method creates the objects of classes ConnectionFactory, MainFrame, ClientsOperations, ProductsOperations, OrdersOperations and Controller, and updates the GUI with the initial status of the application (exiting clients and products).
     * @param args the arguments of the application
     */
    public static void main( String[] args )
    {
        ConnectionFactory myConnection=new ConnectionFactory();
        MainFrame mainFrame= new MainFrame("Order management");
        ClientsOperations clientsOperations = new ClientsOperations(myConnection, mainFrame);
        ProductsOperations productsOperations = new ProductsOperations(myConnection, mainFrame);
        OrdersOperations ordersOperations = new OrdersOperations(myConnection, mainFrame);
        Controller myController = new Controller(mainFrame, clientsOperations, productsOperations, ordersOperations);
        ((ClientsViewPanel)mainFrame.panels[0]).updateTable(clientsOperations.getClients());
        ((ProductsViewPanel)mainFrame.panels[1]).updateTable(productsOperations.getProducts());
        ((OrderViewPanel)mainFrame.panels[2]).updateClientsBox(clientsOperations.getClients());
        ((OrderViewPanel)mainFrame.panels[2]).updateProductsBox(productsOperations.getProducts());

    }
}
