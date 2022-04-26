package DAO;

import helper.JDBC;
import model.Appointment;
import model.appointmentType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class creates the Appointment database methods.
 * @author Jackson Congdon
 */
public class AppointmentDaoImpl {
    /**
     * This is the method to create an ObservableList of appointments containing the User ID. This method executes an SQL query to find all appointments containing the User ID and then adds the appointments to the ObservableList userIDAppointments.
     * @param userID
     * @return the ObservableList userIDAppointments containing appointments with the User ID
     * @throws SQLException
     * @throws Exception
     */
    public static ObservableList<Appointment> getAppointmentUserID(int userID) throws SQLException, Exception{
        ObservableList<Appointment> userIDAppointments=FXCollections.observableArrayList();
        String sqlStatement="select * FROM Appointments WHERE User_ID  = '" + userID + "'";
        Query.makeQuery(sqlStatement);
        Appointment appointmentResult;
        ResultSet result=Query.getResult();
        while(result.next()){
            int Appointment_ID=result.getInt("Appointment_ID");
            String Title=result.getString("Title");
            String Description=result.getString("Description");
            String Location=result.getString("Location");
            String Type=result.getString("Type");
            Timestamp Start=result.getTimestamp("Start");
            LocalDateTime StartCalendar=Start.toLocalDateTime();
            Timestamp End=result.getTimestamp("End");
            LocalDateTime EndCalendar=End.toLocalDateTime();
            Timestamp Create_Date=result.getTimestamp("Create_Date");
            LocalDateTime Create_DateCalendar=Create_Date.toLocalDateTime();
            String Created_By=result.getString("Created_By");
            Timestamp Last_Update=result.getTimestamp("Last_Update");
            LocalDateTime Last_UpdateCalendar=Last_Update.toLocalDateTime();
            String Last_Updated_By=result.getString("Last_Updated_By");
            int Customer_ID=result.getInt("Customer_ID");
            int User_ID=result.getInt("User_ID");
            int Contact_ID=result.getInt("Contact_ID");

            appointmentResult= new Appointment(Appointment_ID, Title, Description, Location, Type, StartCalendar, EndCalendar, Create_DateCalendar, Created_By, Last_UpdateCalendar, Last_Updated_By, Customer_ID, User_ID, Contact_ID);
            userIDAppointments.add(appointmentResult);
        }
        return userIDAppointments;
    }

    /**
     * This is the method to create an ObservableList to get all appointments. This method executes an SQL query to find all appointments and then adds the appointments to the ObservableList allAppointments.
     * @return the ObservableList allAppointments with all appointments
     * @throws SQLException
     * @throws Exception
     */
    public static ObservableList<Appointment> getAllAppointments() throws SQLException, Exception{
        ObservableList<Appointment> allAppointments=FXCollections.observableArrayList();
        String sqlStatement="select * from Appointments";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){
            int Appointment_ID=result.getInt("Appointment_ID");
            String Title=result.getString("Title");
            String Description=result.getString("Description");
            String Location=result.getString("Location");
            String Type=result.getString("Type");
            Timestamp Start=result.getTimestamp("Start");
            LocalDateTime StartCalendar=Start.toLocalDateTime();
            Timestamp End=result.getTimestamp("End");
            LocalDateTime EndCalendar=End.toLocalDateTime();
            Timestamp Create_Date=result.getTimestamp("Create_Date");
            LocalDateTime Create_DateCalendar=Create_Date.toLocalDateTime();
            String Created_By=result.getString("Created_By");
            Timestamp Last_Update=result.getTimestamp("Last_Update");
            LocalDateTime Last_UpdateCalendar=Last_Update.toLocalDateTime();
            String Last_Updated_By=result.getString("Last_Updated_By");
            int Customer_ID=result.getInt("Customer_ID");
            int User_ID=result.getInt("User_ID");
            int Contact_ID=result.getInt("Contact_ID");

            Appointment appointmentResult= new Appointment(Appointment_ID, Title, Description, Location, Type, StartCalendar, EndCalendar, Create_DateCalendar, Created_By, Last_UpdateCalendar, Last_Updated_By, Customer_ID, User_ID, Contact_ID);
            allAppointments.add(appointmentResult);
        }
        return allAppointments;
    }

    /**
     * This is the method to add an appointment. The method executes an SQL query to insert a new Appointment into the Appointments table of the database using the data inputted on the AppointmentsAdd screen.
     * @param appointmentTitle
     * @param appointmentDescription
     * @param appointmentLocation
     * @param appointmentType
     * @param appointmentStart
     * @param appointmentEnd
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdateBy
     * @param customerID
     * @param userID
     * @param contactID
     */
    public static void addAppointment(String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType, LocalDateTime appointmentStart, LocalDateTime appointmentEnd, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdateBy, int customerID, int userID, int contactID) {
        try{
            String sqlaa = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement psti = JDBC.connection.prepareStatement(sqlaa);

            psti.setString(1, appointmentTitle);
            psti.setString(2, appointmentDescription);
            psti.setString(3, appointmentLocation);
            psti.setString(4, appointmentType);
            psti.setTimestamp(5, Timestamp.valueOf(appointmentStart));
            psti.setTimestamp(6, Timestamp.valueOf(appointmentEnd));
            psti. setTimestamp(7, createDate);
            psti.setString(8, createdBy);
            psti.setTimestamp(9, lastUpdate);
            psti.setString(10, lastUpdateBy);
            psti.setInt(11, customerID);
            psti.setInt(12, userID);
            psti.setInt(13, contactID);

            psti.execute();
        } catch(SQLException ex){
            ex.printStackTrace();
            }
        }

    /**
     * This is the method to modify an appointment. The method executes an SQL query to update an existing Appointment in the Appointments table of the database using the data inputted on the AppointmentsModify screen.
     * @param appointmentID
     * @param appointmentTitle
     * @param appointmentDescription
     * @param appointmentLocation
     * @param appointmentType
     * @param appointmentStart
     * @param appointmentEnd
     * @param lastUpdate
     * @param lastUpdateBy
     * @param customerID
     * @param userID
     * @param contactID
     */
    public static void modifyAppointment(int appointmentID, String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType, LocalDateTime appointmentStart, LocalDateTime appointmentEnd, Timestamp lastUpdate, String lastUpdateBy, int customerID, int userID, int contactID) {
        try{
            String sqlam = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
            PreparedStatement psti = JDBC.connection.prepareStatement(sqlam);

            psti.setString(1, appointmentTitle);
            psti.setString(2, appointmentDescription);
            psti.setString(3, appointmentLocation);
            psti. setString(4, appointmentType);
            psti.setTimestamp(5, Timestamp.valueOf(appointmentStart));
            psti.setTimestamp(6, Timestamp.valueOf(appointmentEnd));
            psti.setTimestamp(7, lastUpdate);
            psti.setString(8, lastUpdateBy);
            psti.setInt(9, customerID);
            psti.setInt(10, userID);
            psti.setInt(11, contactID);
            psti.setInt(12, appointmentID);

            psti.execute();
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This is the method to delete an appointment. The method executes an SQL query to delete an existing Appointment in the Appointments table of the database with the selected Appointment ID.
     * @param appointmentID
     */
    public static void deleteAppointment(int appointmentID) {
        try {
            String sqlad = "DELETE FROM appointments WHERE Appointment_ID = ?";
            PreparedStatement psti = JDBC.connection.prepareStatement(sqlad);

            psti.setInt(1, appointmentID);

            psti.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This is the method to delete an appointment when a customer is deleted. The method executes an SQL query to delete an existing Appointment in the Appointments table of the database with the selected Customer ID.
     * @param customerID
     * @param appointmentID
     */
    public static void deleteApptCustID(int customerID, int appointmentID) {
        try {
            String sqlcd = "DELETE FROM appointments WHERE Customer_ID = ? AND Appointment_ID = ?";
            PreparedStatement psti = JDBC.connection.prepareStatement(sqlcd);

            psti.setInt(1, customerID);
            psti.setInt(2, appointmentID);

            psti.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This is the method to create the ObservableList typeAppt with only the distinct appointment types. The method executes an SQL query to select only the distinct Type strings from the Appointments table.
     * @return the ObservableList typeAppt with only the distinct appointment types
     * @throws SQLException
     */
    public static ObservableList<appointmentType> typeAppt() throws SQLException {
        ObservableList<appointmentType> typeAppt=FXCollections.observableArrayList();
        String sqlStatement = "select distinct Type from appointments";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){
            String Type=result.getString("Type");

            appointmentType appointmentTypeResult= new appointmentType(Type);
            typeAppt.add(appointmentTypeResult);
        }
    return typeAppt;
    }

    /**
     * This is the method to count the number of appointments with both the selected type and the selected month. The method executes an SQL query to count from the Appointments table where the selected type and the selected month are in an appointment.
     * @param selectedType
     * @param month
     * @return the number of appointments with both the selected type and the selected month
     * @throws SQLException
     */
    public static int countMonthType(appointmentType selectedType, String month) throws SQLException {
        String sqlStatement = "SELECT COUNT(*) AS monthType FROM appointments WHERE Type  = '" + selectedType + "' AND MONTHNAME(Start) = '" + month + "'";
        Query.makeQuery(sqlStatement);
        int countMonthTypeResult = 0;
        ResultSet result = Query.getResult();
        while(result.next()) {
            countMonthTypeResult = result.getInt("monthType");

            return countMonthTypeResult;
        }
        return countMonthTypeResult;
    }

    /**
     * The Lambda filters appointments based on Contact ID
     * @param selectedContactID
     * @return the ObservableList contactList of appointments containing the Contact ID
     * @throws SQLException
     * @throws Exception
     */
    public static ObservableList<Appointment> getAppointmentsContactID(int selectedContactID) throws SQLException, Exception{
        ObservableList<Appointment> allAppointments = getAllAppointments();
        ObservableList<Appointment> contactList = allAppointments.filtered(a -> {
            if (a.getContact_ID() == selectedContactID){
                return true;
            }
            return false;
        });
        return contactList;
    }

    /**
     * This is the method to create the ObservableList currentMonthAppointments with only appointments with start timestamps within the current month. The method executes an SQL query to select only appointments in the Appointments table where the month of the start attribute matches the current month.
     * @return the ObservableList currentMonthAppointments of appointments this month
     * @throws SQLException
     * @throws Exception
     */
    public static ObservableList<Appointment> getCurrentMonthAppointments() throws SQLException, Exception{
        ObservableList<Appointment> currentMonthAppointments=FXCollections.observableArrayList();
        String sqlStatement="select * from Appointments where MONTH(Start) = MONTH(CURDATE())";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){
            int Appointment_ID=result.getInt("Appointment_ID");
            String Title=result.getString("Title");
            String Description=result.getString("Description");
            String Location=result.getString("Location");
            String Type=result.getString("Type");
            Timestamp Start=result.getTimestamp("Start");
            LocalDateTime StartCalendar=Start.toLocalDateTime();
            Timestamp End=result.getTimestamp("End");
            LocalDateTime EndCalendar=End.toLocalDateTime();
            Timestamp Create_Date=result.getTimestamp("Create_Date");
            LocalDateTime Create_DateCalendar=Create_Date.toLocalDateTime();
            String Created_By=result.getString("Created_By");
            Timestamp Last_Update=result.getTimestamp("Last_Update");
            LocalDateTime Last_UpdateCalendar=Last_Update.toLocalDateTime();
            String Last_Updated_By=result.getString("Last_Updated_By");
            int Customer_ID=result.getInt("Customer_ID");
            int User_ID=result.getInt("User_ID");
            int Contact_ID=result.getInt("Contact_ID");

            Appointment appointmentResult= new Appointment(Appointment_ID, Title, Description, Location, Type, StartCalendar, EndCalendar, Create_DateCalendar, Created_By, Last_UpdateCalendar, Last_Updated_By, Customer_ID, User_ID, Contact_ID);
            currentMonthAppointments.add(appointmentResult);
        }
        return currentMonthAppointments;
    }

    /**
     * This is the method to create the ObservableList currentWeekAppointments with only appointments with start timestamps within the current week. The method executes an SQL query to select only appointments in the Appointments table where the week of the start attribute matches the current week.
     * @return the ObservableList currentWeekAppointments of appointments this week
     * @throws SQLException
     * @throws Exception
     */
    public static ObservableList<Appointment> getCurrentWeekAppointments() throws SQLException, Exception{
        ObservableList<Appointment> currentWeekAppointments=FXCollections.observableArrayList();
        String sqlStatement="select * from Appointments where WEEK(Start) = WEEK(CURDATE())";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){
            int Appointment_ID=result.getInt("Appointment_ID");
            String Title=result.getString("Title");
            String Description=result.getString("Description");
            String Location=result.getString("Location");
            String Type=result.getString("Type");
            Timestamp Start=result.getTimestamp("Start");
            LocalDateTime StartCalendar=Start.toLocalDateTime();
            Timestamp End=result.getTimestamp("End");
            LocalDateTime EndCalendar=End.toLocalDateTime();
            Timestamp Create_Date=result.getTimestamp("Create_Date");
            LocalDateTime Create_DateCalendar=Create_Date.toLocalDateTime();
            String Created_By=result.getString("Created_By");
            Timestamp Last_Update=result.getTimestamp("Last_Update");
            LocalDateTime Last_UpdateCalendar=Last_Update.toLocalDateTime();
            String Last_Updated_By=result.getString("Last_Updated_By");
            int Customer_ID=result.getInt("Customer_ID");
            int User_ID=result.getInt("User_ID");
            int Contact_ID=result.getInt("Contact_ID");

            Appointment appointmentResult= new Appointment(Appointment_ID, Title, Description, Location, Type, StartCalendar, EndCalendar, Create_DateCalendar, Created_By, Last_UpdateCalendar, Last_Updated_By, Customer_ID, User_ID, Contact_ID);
            currentWeekAppointments.add(appointmentResult);
        }
        return currentWeekAppointments;
    }

    /**
     * This is the method to count the number of appointments with the selected Customer ID. The method executes an SQL query to count from the Appointments table where the selected Customer ID is in an appointment.
     * @param customerID
     * @return the number of appointments containing the Customer ID
     * @throws SQLException
     */
    public static int countCustAppt(int customerID) throws SQLException {
        String sqlStatement = "SELECT COUNT(*) AS custAppt FROM appointments WHERE Customer_ID  = '" + customerID + "'";
        Query.makeQuery(sqlStatement);
        int countCustApptResult = 0;
        ResultSet result = Query.getResult();
        while(result.next()) {
            countCustApptResult = result.getInt("custAppt");

            return countCustApptResult;
        }
        return countCustApptResult;
    }
}