package DAO;

import model.Contact;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivision;

public class ContactDaoImpl {
    public static Contact getContact(String contactName) throws SQLException, Exception {
        String sqlStatement = "select * FROM Contacts WHERE contactName  = '" + contactName + "'";
        Query.makeQuery(sqlStatement);
        Contact contactResult;
        ResultSet result = Query.getResult();
        while (result.next()) {
            int Contact_ID = result.getInt("Contact_ID");
            String Contact_Name = result.getString("Contact_Name");
            String Email = result.getString("Email");
            contactResult = new Contact(Contact_ID, Contact_Name, Email);
            return contactResult;
        }
        return null;
    }

    public static ObservableList<Contact> getAllContacts() throws SQLException, Exception {
        ObservableList<Contact> allContacts = FXCollections.observableArrayList();
        String sqlStatement = "select * from Contacts";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        while (result.next()) {
            int Contact_ID = result.getInt("Contact_ID");
            String Contact_Name = result.getString("Contact_Name");
            String Email = result.getString("Email");
            Contact contactResult = new Contact(Contact_ID, Contact_Name, Email);
            allContacts.add(contactResult);
        }
        return allContacts;
    }

    public static Contact getContactFromContactID(int contactID) throws SQLException {
        String sqlStatement = "select * from contacts where Contact_ID = " + contactID;
        Query.makeQuery(sqlStatement);
        Contact getContactFromContactIDResult;
        ResultSet result = Query.getResult();
        while (result.next()) {
            int Contact_ID = result.getInt("Contact_ID");
            String Contact_Name = result.getString("Contact_Name");
            String Email = result.getString("Email");
            getContactFromContactIDResult = new Contact(Contact_ID, Contact_Name, Email);
            return getContactFromContactIDResult;
        }
        return null;
    }


}
