package DAO;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import controller.CustomersModify;
import helper.Globals;
import helper.JDBC;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerDaoImpl {
     public static Customer getCustomer(String customerName) throws SQLException, Exception{
        String sqlStatement="select * FROM Customers WHERE customerName  = '" + customerName+ "'";
        Query.makeQuery(sqlStatement);
           Customer customerResult;
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
               customerResult= new Customer(customerid, customerNameG, address, postalCode, phone, createDateCalendar, createdBy, lastUpdateCalendar, lastUpdateby, Division_ID);
               return customerResult;
           }
        return null;
    }

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

    public static void addCustomer(String customerName, String customerAddress, String postalCode, String customerPhone, String createdBy, String lastUpdateBy, int Division_ID) {
        try {
            String sqlca = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Created_By, Last_Updated_By, Division_ID) VALUES(?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement psti = JDBC.connection.prepareStatement(sqlca);

            psti.setString(1, customerName);
            psti.setString(2, customerAddress);
            psti.setString(3, postalCode);
            psti.setString(4, customerPhone);
            psti.setString(5, createdBy);
            psti.setString(6, lastUpdateBy);
            psti.setInt(7, Division_ID);

            psti.execute();

        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

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


}
