package org.ro.tuc.pt.dataAccessLayer;

import java.sql.*;

/**
 * This class is used for creating the connection with the database.
 * @author Chereji Iulia
 */
public class ConnectionFactory {
    private final String DBURL = "jdbc:mysql://127.0.0.1:3306/ptassignment3";
    private final String USER = "root";
    private final String PASS = "root";
    private final String DATABASENAME = "ptassignment3";
    private Connection connection=null;

    /**
     * Creates a new instance of ConnectionFactory.
     * Attempts to make the connection and prints an error message in case of failure.
     */
    public ConnectionFactory()
    {
        try{
            connection= DriverManager.getConnection(DBURL,USER,PASS);
        }
        catch (SQLException exception) {

            System.out.println("An error occurred while connecting SQL databse");
            exception.printStackTrace();
        }
    }

    /**
     * @return the connection with the database.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * @return the name of the database, required in queries.
     */
    public String getDATABASENAME() {
        return DATABASENAME;
    }

    /**
     * Attempts to close the established connection with the database.
     * @return 0 in case of succes and -1 if an error occured.
     */
    public int closeConnection()
    {
        try{
            if(connection!=null && !connection.isClosed())
            {
                connection.close();
                return 0;
            }
            return -1;
        }
        catch(SQLException e)
        {
            System.out.println("Error closing connection\n");
            return -1;
        }
    }
}
