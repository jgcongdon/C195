package DAO;

import model.Customer;
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

}
