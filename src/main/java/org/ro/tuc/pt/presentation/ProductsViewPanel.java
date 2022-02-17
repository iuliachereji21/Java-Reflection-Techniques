package org.ro.tuc.pt.presentation;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * The class extends JPanel. It will contain the necessary swing components for performing product operations (add, edit, delete product).
 * @author Chereji Iulia
 */
public class ProductsViewPanel extends JPanel {
    private int nrButtons;
    /** the list of buttons in the view. */
    public ArrayList<JButton> buttons;
    /** the table where the products will be displayed */
    public JTable productsTable;
    private DefaultTableModel tableModel;
    private int nrFields;
    /** the list of text fields in the view. */
    public ArrayList<JTextField> fields;
    private int nrWrongLabels;
    /** the list of labels which will be used for showing error messages */
    public ArrayList<JLabel> wrongLabels;

    /**
     * Creates a new instance of ProductsViewPanel.
     * @param height height of the panel
     * @param width width of the panel
     */
    public ProductsViewPanel(int height, int width)
    {
        super();
        this.setBounds(0,0, height, width);
        this.setLayout(null);
        this.setBackground(Color.DARK_GRAY);
        nrButtons=7;
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
        buttons.get(3).setText("EDIT PRODUCT");
        buttons.get(3).setBounds(970, 150, 150, 50);
        buttons.get(4).setText("DELETE PRODUCT");
        buttons.get(4).setBounds(1140, 150, 200, 50);
        buttons.get(5).setText("ADD PRODUCT");
        buttons.get(5).setBounds(1360, 150, 200, 50);
        buttons.get(6).setText("SAVE");
        buttons.get(6).setBounds(1360, 700, 200, 50);

        int nrDataLabels = 4;
        JLabel[] dataLabels = new JLabel[nrDataLabels];
        for(int i = 0; i< nrDataLabels; i++)
        {
            dataLabels[i]=new JLabel();
            dataLabels[i].setFont(new Font("TimesRoman",20,20));
            dataLabels[i].setForeground(Color.WHITE);
            this.add(dataLabels[i]);
            dataLabels[i].setVisible(true);
        }

        dataLabels[3].setText("Id:");
        dataLabels[3].setBounds(970,250,150,30);
        dataLabels[0].setText("Name:");
        dataLabels[0].setBounds(970,330,150,30);
        dataLabels[1].setText("Price:");
        dataLabels[1].setBounds(970,410,150,30);
        dataLabels[2].setText("Stock:");
        dataLabels[2].setBounds(970,490,150,30);

        nrFields=4;
        fields = new ArrayList<>();

        for(int i=0;i<nrFields;i++)
        {
            JTextField field=new JTextField();
            field.setFont(new Font("TimesRoman",20,20));
            field.setForeground(Color.WHITE);
            field.setOpaque(false);
            this.add(field);
            fields.add(field);
        }

        fields.get(3).setBounds(1120,245,440,45);
        fields.get(3).setEditable(false);
        fields.get(0).setBounds(1120,325,440,45);
        fields.get(1).setBounds(1120,405,440,45);
        fields.get(2).setBounds(1120,485,440,45);

        Border border = BorderFactory.createEmptyBorder();

        JPanel tablePanel = new JPanel();
        tablePanel.setBackground(Color.DARK_GRAY);
        tablePanel.setBounds(50,150,900, 650);
        tablePanel.setBorder(border);

        String [] columns={"id", "name", "price", "stock"};
        tableModel= new DefaultTableModel();

        for(int i=0;i<columns.length;i++)
            tableModel.addColumn(columns[i]);

        productsTable= new JTable();
        productsTable.setModel(tableModel);
        productsTable.setBackground(Color.DARK_GRAY);
        productsTable.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
        productsTable.setForeground(Color.WHITE);
        productsTable.setFont(new Font("TimesRoman",20,20));
        productsTable.setRowHeight(40);
        productsTable.getTableHeader().setFont(new Font("TimesRoman",20,20));
        productsTable.getTableHeader().setBackground(Color.DARK_GRAY);
        productsTable.getTableHeader().setForeground(Color.WHITE);
        productsTable.setPreferredScrollableViewportSize(new Dimension(900,650));
        productsTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(productsTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(870,640));
        scrollPane.setBorder(border);
        tablePanel.add(scrollPane);
        this.add(tablePanel);

        nrWrongLabels=4;
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

        wrongLabels.get(0).setText("*please introduce a name");
        wrongLabels.get(0).setBounds(1120,290,440,30);
        wrongLabels.get(1).setText("*please introduce a valid price");
        wrongLabels.get(1).setBounds(1120,370,440,30);
        wrongLabels.get(2).setText("*please introduce a valid stock");
        wrongLabels.get(2).setBounds(1120,450,440,30);
        wrongLabels.get(3).setText("*please select a row");
        wrongLabels.get(3).setBounds(970,200,440,30);
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
     * @return the number of buttons the panel contains.
     */
    public int getNrButtons() {
        return nrButtons;
    }

    /**
     * receives a list of objects and creates the header of the table by accessing the fields of the elements of data through reflection, and then adds entries in the table.
     * @param data list of objects
     */
    public void updateTable(ArrayList<?> data)
    {
        ArrayList<String> columns=new ArrayList<>();
        Class cls = data.get(0).getClass();
        ArrayList<Method> readMethods = new ArrayList<>();

        for(Field field: cls.getDeclaredFields()) {
            columns.add(field.getName());
            try {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(),cls);
                readMethods.add(propertyDescriptor.getReadMethod());
            } catch (Exception e) { System.out.println("ERROR updating Table Clients\n"); e.printStackTrace();}
        }
        tableModel.setColumnCount(0);
        for(int i=0;i<columns.size();i++)
            tableModel.addColumn(columns.get(i));
        int nr= tableModel.getRowCount();
        for(int i=nr-1;i>=0;i--)
            tableModel.removeRow(i);
        Object[] values= new Object[columns.size()];
        try{
            for(int i=0;i< data.size();i++)
            {
                for(int j=0;j<readMethods.size();j++) {
                    values[j] = (readMethods.get(j)).invoke(data.get(i));
                }
                tableModel.addRow(values);
            }
        } catch (Exception e) { System.out.println("ERROR updating Table Clients\n"); e.printStackTrace(); }
        productsTable.setModel(tableModel);
    }

    /**
     * sets the text shown in the fields of the panel.
     * @param row number of the row in the table from which data must be taken.
     * @param filled true if the fields must be filled with information taken from the table (in case of edit), false if the fields must be made empty.
     */
    public void updateFieldsToEdit(int row, boolean filled)
    {
        if(filled)
        {
            fields.get(0).setText((String)(productsTable.getValueAt(row,1)));
            fields.get(1).setText(String.valueOf(productsTable.getValueAt(row,2)));
            fields.get(2).setText(String.valueOf(productsTable.getValueAt(row,3)));
            fields.get(3).setText(String.valueOf(productsTable.getValueAt(row,0)));
        }
        else {
            fields.get(0).setText(null);
            fields.get(1).setText(null);
            fields.get(2).setText(null);
            fields.get(3).setText(null);
        }
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

}
