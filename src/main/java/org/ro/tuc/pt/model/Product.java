package org.ro.tuc.pt.model;

/**
 * The class will hold the product data (i.e. id, name, price, stock).
 * It is mapped to a table in the database.
 * @author Chereji Iulia
 */
public class Product {
    private int id;
    private String name;
    private float price;
    private int stock;

    /**
     * Parameterless constructor.
     */
    public Product()
    {
        this.id=0;
        this.name=null;
        this.price=0.0f;
        this.stock=0;
    }

    /**
     * Creates a new instance of Product.
     * @param id valid integer.
     * @param name not null.
     * @param price valid float greater than 0.
     * @param stock valid integer greater than 0.
     */
    public Product(int id, String name, float price, int stock)
    {
        this.id=id;
        this.name=name;
        this.price=price;
        this.stock=stock;
    }

    /**
     * sets the name of the product.
     * @param name not null.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * @return the id of the product.
     */
    public int getId() {
        return id;
    }

    /**
     * sets the id of the product.
     * @param id valid integer greater than 0.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the price of the product.
     */
    public float getPrice() {
        return price;
    }

    /**
     * @return the stock of the product.
     */
    public int getStock() {
        return stock;
    }

    /**
     * sets the price of the product.
     * @param price valid float greater than 0.
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * sets the stock of the product.
     * @param stock valid integer greater than 0.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }


    /**
     * @return a String containing the name, price and id of the product.
     */
    @Override
    public String toString() {
        return "" + name + ", price: " + price+", id: "+id;
    }
}

