package org.ro.tuc.pt.presentation;

import org.ro.tuc.pt.model.Client;
import org.ro.tuc.pt.model.Product;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The class extends JPanel. It will contain the necessary swing components for performing order operations (select a client, select products, insert quantity, place order).
 * @author Chereji Iulia
 */
public class OrderViewPanel extends JPanel {
    private int nrButtons;
    /** the list of buttons in the view. */
    public ArrayList<JButton> buttons;
    /** the combo box from which the client will be selected. */
    public JComboBox clientsComboBox;
    /** the combo box from which the product will be selected. */
    public JComboBox productsComboBox;
    private DefaultComboBoxModel productsComboModel;
    private DefaultComboBoxModel clientsComboModel;
    /** the field where the user will insert the quantity of the desired product. */
    public JTextField quantityField;
    private int nrWrongLabels;
    /** the list of labels which will be used for showing error messages */
    public ArrayList<JLabel> wrongLabels;
    /** the area where the current status of the unfinished order will be displayed.*/
    public JTextArea cartDisplay;

    /**
     * Creates a new instance of OrderViewPanel.
     * @param height height of the panel
     * @param width width of the panel
     */
    public OrderViewPanel(int height, int width)
    {
        super();
        this.setBounds(0,0, height, width);
        this.setLayout(null);
        this.setBackground(Color.DARK_GRAY);
        nrButtons=5;
        buttons=new ArrayList<>();

        for(int i=0;i<nrButtons;i++)
        {
            JButton button=new JButton();
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
            button.setForeground(Color.WHITE);
            button.setFont(new Font("TimesRoman",20,20));
            buttons.add(button);
            this.add(button);
        }

        buttons.get(0).setText("CLIENTS");
        buttons.get(0).setBounds(100,60, 150, 50);
        buttons.get(1).setText("PRODUCTS");
        buttons.get(1).setBounds(300, 60, 150, 50);
        buttons.get(2).setText("ORDERS");
        buttons.get(2).setBounds(500, 60, 150, 50);
        buttons.get(3).setText("ADD ITEM");
        buttons.get(3).setBounds(438, 480, 150, 50);
        buttons.get(4).setText("PLACE ORDER");
        buttons.get(4).setBounds(1200, 750, 200, 50);

        int nrDataLabels = 3;
        JLabel[] dataLabels = new JLabel[nrDataLabels];
        for(int i = 0; i< nrDataLabels; i++)
        {
            dataLabels[i]=new JLabel();
            dataLabels[i].setFont(new Font("TimesRoman",20,20));
            dataLabels[i].setForeground(Color.WHITE);
            this.add(dataLabels[i]);
            dataLabels[i].setVisible(true);
        }

        dataLabels[0].setText("Client:");
        dataLabels[0].setBounds(100,250,100,30);
        dataLabels[1].setText("Product:");
        dataLabels[1].setBounds(100,330,100,30);
        dataLabels[2].setText("Quantity:");
        dataLabels[2].setBounds(100,410,100,30);

        clientsComboBox= new JComboBox();
        clientsComboBox.setBackground(Color.DARK_GRAY);
        clientsComboBox.setForeground(Color.WHITE);
        clientsComboBox.setFont(new Font("TimesRoman",20,20));
        clientsComboBox.setBounds(200,245,400,40);
        productsComboBox= new JComboBox();
        productsComboBox.setBackground(Color.DARK_GRAY);
        productsComboBox.setForeground(Color.WHITE);
        productsComboBox.setFont(new Font("TimesRoman",20,20));
        productsComboBox.setBounds(200,325,400,40);

        productsComboModel = new DefaultComboBoxModel();
        clientsComboModel= new DefaultComboBoxModel();
        productsComboBox.setModel(productsComboModel);
        clientsComboBox.setModel(clientsComboModel);

        this.add(clientsComboBox);
        this.add(productsComboBox);

        quantityField=new JTextField();
        quantityField.setFont(new Font("TimesRoman",20,20));
        quantityField.setForeground(Color.WHITE);
        quantityField.setOpaque(false);
        this.add(quantityField);
        quantityField.setBounds(200,405,385,40);

        nrWrongLabels=2;
        wrongLabels=new ArrayList<>();

        for(int i=0;i<nrWrongLabels;i++)
        {
            JLabel wrongLabel;
            wrongLabel=new JLabel();
            wrongLabel.setFont(new Font("TimesRoman",20,20));
            wrongLabel.setForeground(Color.RED);
            this.add(wrongLabel);
            wrongLabels.add(wrongLabel);
            wrongLabel.setVisible(false);
        }

        wrongLabels.get(0).setText("*please introduce a valid quantity");
        wrongLabels.get(0).setBounds(200,450,440,30);
        wrongLabels.get(1).setText("*not enough products in stock");
        wrongLabels.get(1).setBounds(200,450,440,30);

        Border border = BorderFactory.createEmptyBorder();

        JPanel scrollPanel = new JPanel();
        scrollPanel.setBackground(Color.DARK_GRAY);
        scrollPanel.setBounds(700,200,800, 500);
        this.add(scrollPanel);

        cartDisplay= new JTextArea(10,30);
        cartDisplay.setBorder(BorderFactory.createMatteBorder(0,1,1,0,Color.WHITE));
        cartDisplay.setFont(new Font("TimesRoman",20,25));
        cartDisplay.setText("");
        cartDisplay.setLineWrap(true);
        cartDisplay.setWrapStyleWord(true);
        cartDisplay.setForeground(Color.WHITE);
        cartDisplay.setBackground(Color.DARK_GRAY);

        JScrollPane scrollPane = new JScrollPane(cartDisplay, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(800,700));
        scrollPane.setBorder(border);
        scrollPanel.add(scrollPane);

    }

    /**
     * updates the combo box of clients.
     * @param clients the list of clients to be added to the combo box.
     */
    public void updateClientsBox(ArrayList<Client> clients)
    {
        clientsComboModel.removeAllElements();
        for(int i=0;i<clients.size();i++)
            clientsComboModel.addElement(clients.get(i));
        clientsComboBox.setModel(clientsComboModel);
    }

    /**
     * updates the combo box of products.
     * @param products the list of products to be added to the combo box.
     */
    public void updateProductsBox(ArrayList<Product> products)
    {
        productsComboModel.removeAllElements();
        for(int i=0;i<products.size();i++)
            productsComboModel.addElement(products.get(i));
        productsComboBox.setModel(productsComboModel);
    }

    /**
     * attempts to add an action listener to one of the button of the panel.
     * @param listener reference to the action listener.
     * @param nrOfTheButton the index of the button in the buttons array.
     */
    public void addButtonListener(ActionListener listener, int nrOfTheButton)
    {
        if(nrOfTheButton<nrButtons)
            buttons.get(nrOfTheButton).addActionListener(listener);
    }

    /**
     * attempts to make a label in the panel visible or not.
     * @param visible true to be visible, false if not.
     * @param nrOfTheLabel the index of the label in the wrongLabels list.
     * @param all true if all the labels to be set visible/unvisible, false if only one of them.
     */
    public void setWrongLabelVisible(boolean visible, int nrOfTheLabel, boolean all)
    {
        if(all)
            for(int i=0;i<nrWrongLabels;i++)
                wrongLabels.get(i).setVisible(visible);
        else
            wrongLabels.get(nrOfTheLabel).setVisible(visible);
    }

    /**
     * @return the number of buttons the panel contains.
     */
    public int getNrButtons() {
        return nrButtons;
    }

}
