package org.ro.tuc.pt.presentation;

import javax.swing.*;

/**
 * The class extends JFrame. It will be the main window of the application, containing three panels.
 * @author Chereji Iulia
 */
public class MainFrame extends JFrame {
    private int screenHeight = 950;
    private int screenWidth = 1600;
    /** the list of panels contained in the frame, from which only 1 will be displayed at a given moment */
    public JPanel[] panels;

    /**
     * Creates a new instance of MainFrame.
     * @param title the title of the frame
     */
    public MainFrame(String title)
    {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(150,50,screenWidth,screenHeight);
        this.setLayout(null);
        this.setVisible(true);
        this.setResizable(false);
        panels = new JPanel[3];
        panels[0]=new ClientsViewPanel(screenHeight, screenWidth);
        panels[1]=new ProductsViewPanel(screenHeight, screenWidth);
        panels[2]= new OrderViewPanel(screenHeight,screenWidth);
        this.setContentPane(panels[0]);
        this.revalidate();
        this.repaint();
    }

    /**
     * sets the content pane of the frame to be one of the panels.
     * @param panelNumber index in the panels list of the desired panel to be shown.
     */
    public void setPanel(int panelNumber)
    {
        this.setContentPane(panels[panelNumber]);
        this.revalidate();
        this.repaint();
    }
}
