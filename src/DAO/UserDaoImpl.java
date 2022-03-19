package DAO;

import helper.JDBC;
import model.Customer;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static utilities.TimeFiles.stringToCalendar;

public class UserDaoImpl {

    public static boolean validateLogIn(String User_Name, String Password) throws SQLException {

        try (
            PreparedStatement ps = JDBC.connection.prepareStatement("SELECT * FROM users WHERE User_Name = ? and Password = ? ")) {
            ps.setString(1, User_Name);
            ps.setString(2, Password);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
         e.printStackTrace();

        }

        return false;
    }

    public static User getUser(String userName) throws SQLException, Exception{
        String sqlStatement="select * FROM Users WHERE User_Name  = '" + userName+ "'";
        Query.makeQuery(sqlStatement);
           User userResult;
           ResultSet result=Query.getResult();
           while(result.next()){
                int userid=result.getInt("User_ID");
                String userNameG=result.getString("User_Name");
                String password=result.getString("Password");
               Timestamp createDate=result.getTimestamp("Create_Date");
               LocalDateTime createDateCalendar=createDate.toLocalDateTime();
               String createdBy = result.getString("Created_By");
               Timestamp lastUpdate=result.getTimestamp("Last_Update");
               LocalDateTime lastUpdateCalendar=lastUpdate.toLocalDateTime();
                String lastUpdateby=result.getString("Last_Updated_By");
                userResult= new User(userid, userNameG, password,  createDateCalendar, createdBy, lastUpdateCalendar, lastUpdateby);
                return userResult;
           }
        return null;
    }

    public static ObservableList<User> getAllUsers() throws SQLException, Exception{
        ObservableList<User> allUsers=FXCollections.observableArrayList();
            String sqlStatement="select * from Users";
            Query.makeQuery(sqlStatement);
            ResultSet result=Query.getResult();
             while(result.next()){
                int userid=result.getInt("User_ID");
                String userNameG=result.getString("User_Name");
                String password=result.getString("Password");
                 Timestamp createDate=result.getTimestamp("Create_Date");
                 LocalDateTime createDateCalendar=createDate.toLocalDateTime();
                 String createdBy = result.getString("Created_By");
                 Timestamp lastUpdate=result.getTimestamp("Last_Update");
                 LocalDateTime lastUpdateCalendar=lastUpdate.toLocalDateTime();
                String lastUpdateby=result.getString("Last_Updated_By");
                User userResult= new User(userid, userNameG, password, createDateCalendar, createdBy, lastUpdateCalendar, lastUpdateby);
                allUsers.add(userResult);
            }
        return allUsers;
    }

    public static User getUserFromUserID(int userID) throws SQLException {
        String sqlStatement = "select * from users where User_ID = " + userID;
        Query.makeQuery(sqlStatement);
        User getUserFromUserIDResult;
        ResultSet result = Query.getResult();
        while (result.next()) {
            int User_ID = result.getInt("User_ID");
            String User_Name = result.getString("User_Name");
            String Password = result.getString("Password");
            Timestamp createDate=result.getTimestamp("Create_Date");
            LocalDateTime createDateCalendar=createDate.toLocalDateTime();
            String Created_By = result.getString("Created_By");
            Timestamp lastUpdate=result.getTimestamp("Last_Update");
            LocalDateTime lastUpdateCalendar=lastUpdate.toLocalDateTime();
            String Last_Updated_By = result.getString("Last_Updated_By");

            getUserFromUserIDResult = new User(User_ID, User_Name, Password, createDateCalendar, Created_By, lastUpdateCalendar, Last_Updated_By);
            return getUserFromUserIDResult;
        }
        return null;
    }

}
