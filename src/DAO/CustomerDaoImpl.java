package DAO;

import model.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static utilities.TimeFiles.stringToCalendar;
import helper.JDBC;

/* typically you would also have create, update and read methods*/
public class CustomerDaoImpl {
    //static boolean act;
     public static Customer getCustomer(String customerName) throws SQLException, Exception{
        // type is name or phone, value is the name or the phone #
         //JDBC.openConnection();
        String sqlStatement="select * FROM Customers WHERE customerName  = '" + customerName+ "'";
         //  String sqlStatement="select FROM address";
        Query.makeQuery(sqlStatement);
           Customer customerResult;
           ResultSet result=Query.getResult();
           while(result.next()){
                int customerid=result.getInt("Customer_ID");
                String customerNameG=result.getString("Customer_Name");
                String address=result.getString("Address");
                //int active=result.getInt("active");
                //if(active==1) act=true;
                String postalCode=result.getString("Postal_Code");
                String phone=result.getString("Phone");
                String createDate=result.getString("Create_Date");
                String createdBy=result.getString("Create_By");
                String lastUpdate=result.getString("Last_Update");
                String lastUpdateby=result.getString("Last_Updated_By");
                Calendar createDateCalendar=stringToCalendar(createDate);
                Calendar lastUpdateCalendar=stringToCalendar(lastUpdate);
               int divisionid=result.getInt("Division_ID");
             //   s(int addressId, String address, String address2, int cityId, String postalCode, String phone, Calendar createDate, String createdBy, Calendar lastUpdate, String lastUpdateBy)
                customerResult= new Customer(customerid, customerNameG, address, postalCode, phone, /*act,*/ createDateCalendar, createdBy, lastUpdateCalendar, lastUpdateby, divisionid);
                return customerResult;
           }
             //JDBC.closeConnection();
        return null;
    }
    public static ObservableList<Customer> getAllCustomers() throws SQLException, Exception{
        ObservableList<Customer> allCustomers=FXCollections.observableArrayList();
        //JDBC.openConnection();
            String sqlStatement="select * from Customers";
            Query.makeQuery(sqlStatement);
            ResultSet result=Query.getResult();
             while(result.next()){
                 int customerid=result.getInt("Customer_ID");
                 String customerNameG=result.getString("Customer_Name");
                 String address=result.getString("Address");
                 //int active=result.getInt("active");
                 //if(active==1) act=true;
                 String postalCode=result.getString("Postal_Code");
                 String phone=result.getString("Phone");
                 String createDate=result.getString("Create_Date");
                 String createdBy=result.getString("Created_By");
                 String lastUpdate=result.getString("Last_Update");
                 String lastUpdateby=result.getString("Last_Updated_By");
                 Calendar createDateCalendar=stringToCalendar(createDate);
                 Calendar lastUpdateCalendar=stringToCalendar(lastUpdate);
                 int divisionid=result.getInt("Division_ID");
             //   s(int addressId, String address, String address2, int cityId, String postalCode, String phone, Calendar createDate, String createdBy, Calendar lastUpdate, String lastUpdateBy)
                Customer customerResult= new Customer(customerid, customerNameG, address, postalCode, phone, /*act,*/ createDateCalendar, createdBy, lastUpdateCalendar, lastUpdateby, divisionid);
                allCustomers.add(customerResult);
                
            }
             //JDBC.closeConnection();
        return allCustomers;
    } 
}
