package DAO;

import model.Contact;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static utilities.TimeFiles.stringToCalendar;
import helper.JDBC;

/* typically you would also have create, update and read methods*/
public class ContactDaoImpl {
    //static boolean act;
    public static Contact getContact(String contactName) throws SQLException, Exception{
        // type is name or phone, value is the name or the phone #
        //JDBC.openConnection();
        String sqlStatement="select * FROM Contacts WHERE contactName  = '" + contactName + "'";
        //  String sqlStatement="select FROM address";
        Query.makeQuery(sqlStatement);
        Contact contactResult;
        ResultSet result=Query.getResult();
        while(result.next()){
            int Contact_ID=result.getInt("Contact_ID");
            String Contact_Name=result.getString("Contact_Name");
            String Email=result.getString("Email");
            contactResult= new Contact(Contact_ID, Contact_Name, Email);
            return contactResult;
        }
        //JDBC.closeConnection();
        return null;
    }
    public static ObservableList<Contact> getAllContacts() throws SQLException, Exception{
        ObservableList<Contact> allContacts=FXCollections.observableArrayList();
        //JDBC.openConnection();
        String sqlStatement="select * from Contacts";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){
            int Contact_ID=result.getInt("Contact_ID");
            String Contact_Name=result.getString("Contact_Name");
            String Email=result.getString("Email");
            Contact contactResult= new Contact(Contact_ID, Contact_Name, Email);
            allContacts.add(contactResult);

        }
        //JDBC.closeConnection();
        return allContacts;
    }
}
