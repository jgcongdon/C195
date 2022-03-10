package DAO;

import model.Appointment;
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

}
