package org.ro.tuc.pt.model;

/**
 * The class will hold the clients data (i.e. id, name, address, phone, email, date of birth).
 * It is mapped to a table in the database.
 * @author Chereji Iulia
 */
public class Client {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String dateOfBirth;

    /**
     * Creates a new instance of Client.
     * @param id valid integer.
     * @param name valid name containing only letters and spaces.
     * @param address can be null
     * @param phone can be null, otherwise a String object of length 10 and containing only digits.
     * @param email a valid email.
     * @param dateOfBirth can be null, otherwise a String of the format 'yyyy-mm-dd'
     */
    public Client(int id, String name, String address, String phone, String email, String dateOfBirth)    {
        this.id=id;
        this.name=name;
        this.address=address;
        this.phone=phone;
        this.email=email;
        this.dateOfBirth=dateOfBirth;
    }

    /**
     * Parameterless constructor.
     */
    public Client()
    {
        this.id=0;
        this.name=null;
        this.address=null;
        this.phone=null;
        this.email=null;
        this.dateOfBirth=null;
    }

    /**
     * Sets the id of the client.
     * @param id valid integer.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the id of the client.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the phone of the client.
     * @param phone can be null, otherwise a String object of length 10 and containing only digits.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Sets the address of the client.
     * @param address can be null.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the dateOfBirth of the client.
     * @param dateOfBirth can be null.
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Sets the email of the client.
     * @param email valid email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return client's date of birth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @return client's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return client's phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @return client's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return client's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the client.
     * @param name not null, containing only letters and spaces.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String containing the name and id of the client to be printed in the GUI.
     */
    @Override
    public String toString() {
        return ""+name+", id: "+id;
    }

    /**
     * @return String containing the name, id, address, phone and email of the client to be printed in the bill when he makes an order.
     */
    public String toStringBill(){
        return "name: "+name+"; id: "+id +"; address: "+address+"; phone: "+phone+"; email: "+email;
    }
}

