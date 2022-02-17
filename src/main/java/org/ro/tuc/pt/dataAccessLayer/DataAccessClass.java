package org.ro.tuc.pt.dataAccessLayer;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Generic class that dynamically writes (and executes) SQL queries accessing the database, using reflection.
 * @author Chereji Iulia
 * @param <T> class that must be mapped to a table in the database.
 */
public class DataAccessClass<T> {
    private final Class<T> type;
    private ConnectionFactory connectionFactory;

    /**
     * Creates a new instance of DataAccessClass.
     * @param connectionFactory is the object holding the connection to the database.
     * @param instance is an instance of class T used to store its type.
     */
    public DataAccessClass(ConnectionFactory connectionFactory, T instance)
    {
        this.type=(Class<T>) (instance.getClass());
        this.connectionFactory= connectionFactory;
    }

    /**
     * Reads all the entries from the database table which corresponds to the class T.
     * @return a list of created objects of type T.
     */
    public ArrayList<T> findAll()
    {
        ResultSet rs = null;
        Statement st=null;
        try {
            st = connectionFactory.getConnection().createStatement();
            rs = st.executeQuery("SELECT * from " + connectionFactory.getDATABASENAME() + "." + type.getSimpleName());
            return createObjects(rs);
        }
        catch (SQLException e)
        {
            System.out.println("ERROR trying to findAll\n");
            e.printStackTrace();
            return null;
        }
        finally {
           try{ if(rs!=null) rs.close(); if(st!=null) st.close();}
           catch(SQLException e){ System.out.println("ERROR closing\n"); e.printStackTrace();}
        }
    }

    /**
     * Attempts to update an entry in the database table which corresponds to the class T.
     * @param instance the object whose data will be written in the table.
     */
    public void update(T instance)
    {
        Statement st=null;
        try {
            st = connectionFactory.getConnection().createStatement();
            String statement = new String("update " + connectionFactory.getDATABASENAME() + "." + type.getSimpleName()+" set ");
            String where = " WHERE ";
            boolean first=true;
            for(Field field: type.getDeclaredFields()) {
                try {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(),type);
                    Method method = propertyDescriptor.getReadMethod();
                    Object value;
                    value = method.invoke(instance);
                    if(first)
                    {
                        where = where + field.getName() + " = '" + String.valueOf(value) +"';";
                        first=false;
                    } else
                        statement = statement + field.getName() + " = '" + String.valueOf(value) + "', ";

                } catch (Exception e) { System.out.println("ERROR updating\n"); e.printStackTrace();}
            }
            statement= statement.substring(0, statement.length()-2);
            statement= statement + where;
            st.executeUpdate(statement);
        }
        catch (SQLException e) { System.out.println("SQL problem when trying to update\n"); }
        finally {
            try{ if(st!=null) st.close();}
            catch(SQLException e){ System.out.println("ERROR closing\n"); e.printStackTrace();}
        }
    }

    /**
     * Attempts to add an entry in the database table which corresponds to the class T.
     * @param instance the object whose data will be written in the table.
     */
    public void insert(T instance)
    {
        Statement st=null;
        try {
            st = connectionFactory.getConnection().createStatement();
            String statement = new String("INSERT INTO " + connectionFactory.getDATABASENAME() + "." + type.getSimpleName()+" (");
            String values = " VALUES (";
            for(Field field: type.getDeclaredFields()) {
                try {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(),type);
                    Method method = propertyDescriptor.getReadMethod();
                    Object value;
                    value = method.invoke(instance);

                    statement = statement + "`"+field.getName()+"`, ";
                        values = values + "'"+String.valueOf(value)+"', ";

                } catch (Exception e) { System.out.println("ERROR adding\n"); e.printStackTrace();}
            }
            statement= statement.substring(0, statement.length()-2);
            statement= statement + ")";
            values=values.substring(0, values.length()-2);
            values=values+");";
            statement = statement + values;
            st.executeUpdate(statement);
        }
        catch (SQLException e) { System.out.println("SQL problem when trying to insert\n"); }
        finally {
            try{ if(st!=null) st.close();}
            catch(SQLException e){ System.out.println("ERROR closing\n"); e.printStackTrace();}
        }
    }

    /**
     * Attempts to delete an entry in the database table which corresponds to the class T.
     * @param id the id of the entry which will be deleted.
     */
    public void deleteByID(int id)
    {
        Statement st=null;
        try
        {
            st = connectionFactory.getConnection().createStatement();
            String statement = new String("DELETE FROM " + connectionFactory.getDATABASENAME() + "." + type.getSimpleName()+" WHERE id="+id+";");
            st.executeUpdate(statement);
        }
        catch (SQLException e)
        {
            System.out.println("SQL problem when trying to delete by ID\n");
        }
        finally {
            try{ if(st!=null) st.close();}
            catch(SQLException e){ System.out.println("ERROR closing\n"); e.printStackTrace();}
        }
    }

    /**
     * Searches for an entry having the specified id in the table which corresponds to the class T.
     * @param id the id to be found.
     * @return object of type T if id is found, or null if not.
     */
    public T findByID(int id)
    {
        ResultSet rs = null;
        Statement st=null;
        try
        {
            st = connectionFactory.getConnection().createStatement();
            String statement = new String("SELECT * FROM " + connectionFactory.getDATABASENAME() + "." + type.getSimpleName()+" WHERE id="+id+";");
            rs=st.executeQuery(statement);
            return createObjects(rs).get(0);
        }
        catch (SQLException e)
        {
            System.out.println("SQL problem when trying to find by ID\n");
            return null;
        }
        finally {
            try{ if(rs!=null) rs.close(); if(st!=null) st.close();}
            catch(SQLException e){ System.out.println("ERROR closing\n"); e.printStackTrace();}
        }
    }

    private ArrayList<T> createObjects(ResultSet rs)
    {
        ArrayList<T> list= new ArrayList<>();
        try {
            while(rs.next()) {
                    T instance = type.getDeclaredConstructor().newInstance();
                    //T instance = type.newInstance();
                    for(Field field: type.getDeclaredFields()) {
                        Object value= rs.getObject(field.getName());
                        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(),type);
                        Method method = propertyDescriptor.getWriteMethod();
                        method.invoke(instance, value);
                    }
                    list.add(instance);
            }
        }
        catch (Exception e) {
            System.out.println("error while creating objects\n");
            e.printStackTrace();
        }
        finally { return list; }
    }
}
