package org.ro.tuc.pt.model;

/**
 * The class will hold the order data (i.e. id, idClient, idProduct, quantity, price, dateAndTime).
 * It is mapped to a table in the database.
 * @author Chereji Iulia
 */
public class Order {
    private int id;
    private int idClient;
    private int idProduct;
    private int quantity;
    private float price;
    private String dateAndTime;

    /**
     * Parameterless constructor.
     */
    public Order()
    {
        id=0;
        idClient=0;
        idProduct=0;
        quantity=0;
        price=0;
        dateAndTime="";
    }

    /**
     * Sets the id of the order.
     * @param id valid integer.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the id of the order.
     */
    public int getId() {
        return id;
    }

    /**
     * @return the price of the order
     */
    public float getPrice() {
        return price;
    }

    /**
     * @return the id of the client who made the order.
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * @return the id of the product that was ordered.
     */
    public int getIdProduct() {
        return idProduct;
    }

    /**
     * @return the quantity of the product that was ordered.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @return the date and time when the order was placed.
     */
    public String getDateAndTime() {
        return dateAndTime;
    }

    /**
     * Sets the date and time of the order.
     * @param dateAndTime must be of the format 'yyyy-mm-dd hours:minutes:seconds'
     */
    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    /**
     * Sets the id of the client.
     * @param idClient valid integer.
     */
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     * sets the id of the product to be ordered.
     * @param idProduct valid integer.
     */
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    /**
     * sets the quantity of the product to be ordered.
     * @param quantity valid integer greater than 0.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * sets the price of the order.
     * @param price valid float greater than 0.
     */
    public void setPrice(float price) {
        this.price = price;
    }

}
