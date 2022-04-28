package DAO;

import helper.JDBC;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class creates the User database methods.
 * @author Jackson Congdon
 */
public class UserDaoImpl {
    /**
     * This is the method to validate that a User exists in the Users table with both the provided username and the provided password. The method executes an SQL query to select all users with both a username and a password matching the username and password provided, and then return true if any users are found or return false if no users are found.
     * @param User_Name the username to validate for
     * @param Password the password to validate for
     * @return true if user is found matching username and password, false if no user is found
     * @throws SQLException
     */
    public static boolean validateLogIn(String User_Name, String Password) throws SQLException {
        try (PreparedStatement ps = JDBC.connection.prepareStatement("select * FROM Users WHERE User_Name = ? AND Password = ?")) {
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

    /**
     * This is the method to validate that a username exists in the Users table. The method executes an SQL query to select all users with a username matching the username provided, and then return true if any users are found or return false if no users are found.
     * @param User_Name the username to validate that it exists
     * @return true if user is found matching username, false if no user is found
     * @throws SQLException
     */
    public static boolean validateUserName(String User_Name) throws SQLException {
        try (PreparedStatement ps = JDBC.connection.prepareStatement("SELECT * FROM users WHERE User_Name = ?")) {
            ps.setString(1, User_Name);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * This is the method to validate that a password exists in the Users table. The method executes an SQL query to select all users with a password matching the password provided, and then return true if any users are found or return false if no users are found.
     * @param Password the password to validate if it exists
     * @return true is user is found matching password, false if no user is found
     * @throws SQLException
     */
    public static boolean validatePassword(String Password) throws SQLException {
        try (PreparedStatement ps = JDBC.connection.prepareStatement("SELECT * FROM users WHERE Password = ?")) {
            ps.setString(1, Password);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * This is the method to get a User from the provided userName. The method executes an SQL query to select the user from the Users table with a matching userName.
     * @param userName the username to get the User object for
     * @return the User userResult matching the selected userName
     * @throws SQLException
     * @throws Exception
     */
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

    /**
     * This is the method to get all users. The method executes an SQL query that selects all users from the Users table and adds the users to the ObservableList allUsers.
     * @return the ObservableList allUsers containing all users
     * @throws SQLException
     * @throws Exception
     */
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

    /**
     * This is the method to return the User matching the selected userID. The method executes an SQL query to select the User from the Users table with a matching userID.
     * @param userID the userID to get the User object for
     * @return the User getUserFromUserIDResult matching the selected userID
     * @throws SQLException
     */
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

    /**
     * This is the method to return the User ID of a userName. The method executes an SQL query to select the User ID matching the selected userName.
     * @param userName the username to get the userID for
     * @return the User ID matching the selected userName
     * @throws SQLException
     */
    public static int getUserIDFromUserName(String userName) throws SQLException {
        int userID = 0;
        String sqlStatement = "select User_ID from users where User_Name = '" + userName+ "'";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        while (result.next()) {
            userID = result.getInt("User_ID");

            int getUserIDFromUserNameResult = userID;
            return getUserIDFromUserNameResult;
        }
        return userID;
    }
}