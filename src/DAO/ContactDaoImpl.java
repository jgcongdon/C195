package DAO;

import model.Contact;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class creates the Contact database methods.
 * @author Jackson Congdon
 */
public class ContactDaoImpl {
    /**
     * This is the method to create an ObservableList to get all contacts. This method executes an SQL query to find all contacts and then adds the contacts to the ObservableList allContacts.
     * @return the ObservableList allContacts
     * @throws SQLException
     * @throws Exception
     */
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

    /**
     * This is the method to get a Contact object from a Contact ID. The method executes an SQL query to select the contact from the Contacts table with the selected Contact ID.
     * @param contactID the contact ID to find the contact object for
     * @return the Contact getContactFromContactIDResult
     * @throws SQLException
     */
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
