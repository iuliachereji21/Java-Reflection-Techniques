package org.ro.tuc.pt.presentation;

import org.ro.tuc.pt.businessLayer.ClientsOperations;
import org.ro.tuc.pt.businessLayer.OrdersOperations;
import org.ro.tuc.pt.businessLayer.ProductsOperations;
import org.ro.tuc.pt.model.Product;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The class will be the controller of the main window of the application, handling user's requests.
 * It has 3 inner classes, one corresponding to each panel of the application (i.e. ButtonsListenerClients - ClientsViewPanel, ButtonsListenerProducts - ProductsViewPanel, ButtonsListenerOrders - OrderViewPanel).
 * @author Chereji Iulia
 */
public class Controller {
    private MainFrame mainFrame;
    private ClientsViewPanel clientsViewPanel;
    private ProductsViewPanel productsViewPanel;
    private OrderViewPanel orderViewPanel;
    private ClientsOperations clientsOperations;
    private ProductsOperations productsOperations;
    private OrdersOperations ordersOperations;

    /**
     * Creates a new instance of Controller.
     * @param mainFrame the main JFrame of the application, from which user input is received.
     * @param clientsOperations the object that will handle user's request concerning clients.
     * @param productsOperations the object that will handle user's request concerning products.
     * @param ordersOperations the object that will handle user's request concerning orders.
     */
    public Controller(MainFrame mainFrame, ClientsOperations clientsOperations, ProductsOperations productsOperations, OrdersOperations ordersOperations)
    {
        this.mainFrame=mainFrame;
        this.clientsOperations=clientsOperations;
        this.productsOperations=productsOperations;
        this.ordersOperations=ordersOperations;

        this.clientsViewPanel=(ClientsViewPanel) mainFrame.panels[0];
        this.productsViewPanel =(ProductsViewPanel) mainFrame.panels[1];
        this.orderViewPanel = (OrderViewPanel) mainFrame.panels[2];
        for(int i=0;i<clientsViewPanel.getNrButtons();i++)
            clientsViewPanel.addButtonListener(new ButtonsListenerClients(), i);
        for(int i=0;i<productsViewPanel.getNrButtons();i++)
            productsViewPanel.addButtonListener(new ButtonsListenerProducts(), i);
        for(int i=0;i<orderViewPanel.getNrButtons();i++)
            orderViewPanel.addButtonListener(new ButtonsListenerOrders(), i);
    }

    /**
     * Class implements ActionListener interface and handles user's request concerning clients, received from mainFrame's ClientsViewPanel panel
     * It calls methods of the clientsOperations object and displays the appropriate messages into the GUI.
     */
    class ButtonsListenerClients implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object event=e.getSource(); int row; clientsViewPanel.setWrongLabelVisible(false, 0,true); int result;
            if(event==clientsViewPanel.buttons.get(0)) //clients
            {   clientsViewPanel.updateFieldsToEdit(0,false);  mainFrame.setPanel(0);}
            else if(event==clientsViewPanel.buttons.get(1)) //products
            { productsViewPanel.updateFieldsToEdit(0,false); mainFrame.setPanel(1);}
            else if(event==clientsViewPanel.buttons.get(2)) //orders
            {   ordersOperations.reset(); orderViewPanel.cartDisplay.setText(ordersOperations.toStringGUI(productsOperations)); mainFrame.setPanel(2);}
            else if(event==clientsViewPanel.buttons.get(3)) //edit client
                if((row=clientsViewPanel.clientsTable.getSelectedRow())>=0)
                    clientsViewPanel.updateFieldsToEdit(row, true);
                else clientsViewPanel.wrongLabels.get(4).setVisible(true);
            else if(event==clientsViewPanel.buttons.get(4)) //delete client
                if((row=clientsViewPanel.clientsTable.getSelectedRow())>=0)
                { clientsOperations.deleteClient(Integer.valueOf(String.valueOf(clientsViewPanel.clientsTable.getValueAt(row,0))));}
                else clientsViewPanel.wrongLabels.get(4).setVisible(true);
            else if(event==clientsViewPanel.buttons.get(5)) //add client
                    clientsViewPanel.fields.get(5).setText(Integer.toString(clientsOperations.getNewID()));
            else if(event==clientsViewPanel.buttons.get(6)) //save
                    if((result= clientsOperations.validateClient(clientsViewPanel.fields.get(5).getText(), clientsViewPanel.fields.get(0).getText(), clientsViewPanel.fields.get(1).getText(), clientsViewPanel.fields.get(2).getText(), clientsViewPanel.fields.get(3).getText(), clientsViewPanel.fields.get(4).getText()))!=-1)
                        clientsViewPanel.setWrongLabelVisible(true, result, false);
                    else { clientsViewPanel.setWrongLabelVisible(false, 0, true); clientsViewPanel.updateFieldsToEdit(-1,false);}
        }
    }

    /**
     * Class implements ActionListener interface and handles user's request concerning products, received from mainFrame's ProductsViewPanel panel
     * It calls methods of the productsOperations object and displays the appropriate messages into the GUI.
     */
    class ButtonsListenerProducts implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object event=e.getSource(); int row; productsViewPanel.setWrongLabelVisible(false, 0, true); int result;
            if(event == productsViewPanel.buttons.get(0)) //clients
            {   clientsViewPanel.updateFieldsToEdit(0,false);  mainFrame.setPanel(0);}
            else if(event == productsViewPanel.buttons.get(1)) //products
            { productsViewPanel.updateFieldsToEdit(0,false); mainFrame.setPanel(1);}
            else if(event == productsViewPanel.buttons.get(2)) //orders
            {   ordersOperations.reset(); orderViewPanel.cartDisplay.setText(ordersOperations.toStringGUI(productsOperations)); mainFrame.setPanel(2);}
            else if(event==productsViewPanel.buttons.get(3)) //edit product
                if((row=productsViewPanel.productsTable.getSelectedRow())>=0)
                    productsViewPanel.updateFieldsToEdit(row, true);
                else productsViewPanel.wrongLabels.get(3).setVisible(true);
            else if(event==productsViewPanel.buttons.get(4)) //delete product
                if((row=productsViewPanel.productsTable.getSelectedRow())>=0)
                { productsOperations.deleteProduct(Integer.valueOf(String.valueOf(productsViewPanel.productsTable.getValueAt(row,0))));
                    }
                else productsViewPanel.wrongLabels.get(3).setVisible(true);
            else if(event==productsViewPanel.buttons.get(5)) //add product
                productsViewPanel.fields.get(3).setText(Integer.toString(productsOperations.getNewID()));
            else if(event==productsViewPanel.buttons.get(6)) //save
                if((result= productsOperations.validateProduct(productsViewPanel.fields.get(3).getText(), productsViewPanel.fields.get(0).getText(), productsViewPanel.fields.get(1).getText(), productsViewPanel.fields.get(2).getText()))!=-1)
                    productsViewPanel.setWrongLabelVisible(true, result, false);
                else { productsViewPanel.setWrongLabelVisible(false, 0, true); productsViewPanel.updateFieldsToEdit(-1,false);}
        }
    }

    /**
     * Class implements ActionListener interface and handles user's request concerning orders, received from mainFrame's OrderViewPanel panel
     * It calls methods of the ordersOperations object and displays the appropriate messages into the GUI.
     */
    class ButtonsListenerOrders implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object event=e.getSource();
            if(event == orderViewPanel.buttons.get(0)) //clients
            {   clientsViewPanel.updateFieldsToEdit(0,false);  mainFrame.setPanel(0);}
            else if(event == orderViewPanel.buttons.get(1)) //products
            { productsViewPanel.updateFieldsToEdit(0,false); mainFrame.setPanel(1);}
            else if(event == orderViewPanel.buttons.get(2)) //orders
            {   ordersOperations.reset(); orderViewPanel.cartDisplay.setText(ordersOperations.toStringGUI(productsOperations)); mainFrame.setPanel(2);}
            else if(event == orderViewPanel.buttons.get(3)) //add item
            {
                int q;
                try {
                    q = Integer.parseInt(orderViewPanel.quantityField.getText());
                    if (q<=0) orderViewPanel.setWrongLabelVisible(true,0, false);
                    else {
                        if(ordersOperations.addProduct((Product)(orderViewPanel.productsComboBox.getSelectedItem()), q)!=0)
                            orderViewPanel.setWrongLabelVisible(true,1, false);
                        else
                        {orderViewPanel.quantityField.setText(""); orderViewPanel.setWrongLabelVisible(false,0,true); orderViewPanel.cartDisplay.setText(ordersOperations.toStringGUI(productsOperations));}}
                }
                catch (NumberFormatException ex) { orderViewPanel.setWrongLabelVisible(true,0, false); }
            }
            else if(event == orderViewPanel.buttons.get(4)) //place order
            {
                if(ordersOperations.getTotalPrice()!=0) {
                    ordersOperations.placeOrder(productsOperations);
                    orderViewPanel.cartDisplay.setText(ordersOperations.toStringGUI(productsOperations));
                }
            }
        }
    }
}
