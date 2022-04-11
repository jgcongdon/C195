package DAO;

import helper.Globals;
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

public class AppointmentDaoImpl {
    public static Appointment getAppointment(String title) throws SQLException, Exception{
        String sqlStatement="select * FROM Appointments WHERE title  = '" + title + "'";
        Query.makeQuery(sqlStatement);
        Appointment appointmentResult;
        ResultSet result=Query.getResult();
        while(result.next()){
            int Appointment_ID=result.getInt("Appointment_ID");
            String Title=result.getString("Title");
            String Description=result.getString("Address");
            String Location=result.getString("Location");
            String Type=result.getString("TYpe");
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
            return appointmentResult;
        }
        return null;
    }

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

    public static ObservableList<Appointment> getAppointmentsContactID(int selectedContactID) throws SQLException, Exception{
        ObservableList<Appointment> AppointmentsContactID=FXCollections.observableArrayList();
        String sqlStatement="select * from Appointments where Contact_ID = '" + selectedContactID + "'";
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
            AppointmentsContactID.add(appointmentResult);

        }
        return AppointmentsContactID;
    }

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

    }
