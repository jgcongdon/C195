package DAO;

import helper.JDBC;
import model.Customer;

import java.sql.*;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class creates the Customer database methods.
 * @author Jackson Congdon
 */
public class CustomerDaoImpl {
    /**
     * This is the method to create an ObservableList to get all customers. This method executes an SQL query to find all customers and then adds the customers to the ObservableList allCustomers.
     * @return the ObservableList allCustomers
     * @throws SQLException
     * @throws Exception
     */
    public static ObservableList<Customer> getAllCustomers() throws SQLException, Exception{
        ObservableList<Customer> allCustomers=FXCollections.observableArrayList();
        String sqlStatement="select * from Customers";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){
            int customerid=result.getInt("Customer_ID");
            String customerNameG=result.getString("Customer_Name");
            String address=result.getString("Address");
            String postalCode=result.getString("Postal_Code");
            String phone=result.getString("Phone");
            Timestamp createDate=result.getTimestamp("Create_Date");
            LocalDateTime createDateCalendar=createDate.toLocalDateTime();
            String createdBy=result.getString("Created_By");
            Timestamp lastUpdate=result.getTimestamp("Last_Update");
            LocalDateTime lastUpdateCalendar=lastUpdate.toLocalDateTime();
            String lastUpdateby=result.getString("Last_Updated_By");
            int Division_ID=result.getInt("Division_ID");
            Customer customerResult= new Customer(customerid, customerNameG, address, postalCode, phone, createDateCalendar, createdBy, lastUpdateCalendar, lastUpdateby, Division_ID);
            allCustomers.add(customerResult);
        }
        return allCustomers;
    }

    /**
     * This is the method to add a customer. The method executes an SQL query to insert a new Customer into the Customers table of the database using the data inputted on the CustomersAdd screen.
     * @param customerName the name of the customer to add
     * @param customerAddress the address of the customer to add
     * @param postalCode the postal code of the customer to add
     * @param customerPhone the phone number of the customer to add
     * @param createDate the create date of the customer to add
     * @param createdBy the user creating the customer to add
     * @param lastUpdate the last updated time of the customer to add
     * @param lastUpdateBy the last user to update the customer to add
     * @param Division_ID the division ID of the customer to add
     */
    public static void addCustomer(String customerName, String customerAddress, String postalCode, String customerPhone, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdateBy, int Division_ID) {
        try {
            String sqlca = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement psti = JDBC.connection.prepareStatement(sqlca);

            psti.setString(1, customerName);
            psti.setString(2, customerAddress);
            psti.setString(3, postalCode);
            psti.setString(4, customerPhone);
            psti.setTimestamp(5, createDate);
            psti.setString(6, createdBy);
            psti.setTimestamp(7, lastUpdate);
            psti.setString(8, lastUpdateBy);
            psti.setInt(9, Division_ID);

            psti.execute();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * This is the method to modify a Customer. The method executes an SQL query to update an existing Customer in the Customers table of the database using the data inputted on the CustomersModify screen.
     * @param customerID the ID of the customer to modify
     * @param customerName the name of the customer to modify
     * @param customerAddress the address of the customer to modify
     * @param postalCode the postal code of the customer to modify
     * @param customerPhone the phone number of the customer to modify
     * @param lastUpdate the last updated time of the customer to modify
     * @param lastUpdateBy the last user to update the customer to modify
     * @param Division_ID the division ID of the customer to modify
     */
    public static void modifyCustomer(int customerID, String customerName, String customerAddress, String postalCode, String customerPhone, Timestamp lastUpdate, String lastUpdateBy, int Division_ID) {
        try {
            String sqlcm = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";
            PreparedStatement psti = JDBC.connection.prepareStatement(sqlcm);

            psti.setString(1, customerName);
            psti.setString(2, customerAddress);
            psti.setString(3, postalCode);
            psti.setString(4, customerPhone);
            psti.setTimestamp(5, lastUpdate);
            psti.setString(6, lastUpdateBy);
            psti.setInt(7, Division_ID);
            psti.setInt(8, customerID);

            psti.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This is the method to delete a customer. The method executes an SQL query to delete an existing Customer in the Customers table of the database with the selected Customer ID.
     * @param customerID the ID of the customer to delete
     */
    public static void deleteCustomer(int customerID) {
        try {
            String sqlcd = "DELETE FROM customers WHERE Customer_ID = ?";
            PreparedStatement psti = JDBC.connection.prepareStatement(sqlcd);

            psti.setInt(1, customerID);

            psti.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This is the method to get a Customer object from a Customer ID. The method executes an SQL query to select the customer from the Customers table with the selected Customer ID.
     * @param customerID the customer ID to get the customer object for
     * @return the Customer getCustomerFromCustomerIDResult
     * @throws SQLException
     */
    public static Customer getCustomerFromCustomerID(int customerID) throws SQLException {
        String sqlStatement = "select * from customers where Customer_ID = " + customerID;
        Query.makeQuery(sqlStatement);
        Customer getCustomerFromCustomerIDResult;
        ResultSet result = Query.getResult();
        while (result.next()) {
            int Customer_ID = result.getInt("Customer_ID");
            String Customer_Name = result.getString("Customer_Name");
            String Address = result.getString("Address");
            String Postal_Code = result.getString("Postal_Code");
            String Phone = result.getString("Phone");
            Timestamp createDate=result.getTimestamp("Create_Date");
            LocalDateTime createDateCalendar=createDate.toLocalDateTime();
            String Created_By = result.getString("Created_By");
            Timestamp lastUpdate=result.getTimestamp("Last_Update");
            LocalDateTime lastUpdateCalendar=lastUpdate.toLocalDateTime();
            String Last_Updated_By = result.getString("Last_Updated_By");
            int Division_ID = result.getInt("Division_ID");

            getCustomerFromCustomerIDResult = new Customer(Customer_ID, Customer_Name, Address, Postal_Code, Phone, createDateCalendar, Created_By, lastUpdateCalendar, Last_Updated_By, Division_ID);
            return getCustomerFromCustomerIDResult;
        }
        return null;
    }

    /**
     * This is the method to count the number of customers with the selected Country ID. The method executes an SQL query to count the numbers of customers where the Division ID is found by using the selected Country ID and then matching the Country ID to its range of FirstLevelDivision IDs.
     * @param Country_ID the country ID of which to count customers for
     * @return the number of customers with the selected Country ID
     * @throws SQLException
     */
    public static int countCustomers(int Country_ID) throws SQLException {
        String sqlStatement = "SELECT COUNT(*) AS customerCountry FROM customers WHERE Division_ID IN (SELECT Division_ID FROM First_Level_Divisions WHERE Country_ID = " + Country_ID + ")";
        Query.makeQuery(sqlStatement);
        int countCustomersResult = 0;
        ResultSet result = Query.getResult();
        while(result.next()) {
            countCustomersResult = result.getInt("customerCountry");

            return countCustomersResult;
        }
        return countCustomersResult;
    }
}
