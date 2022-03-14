package DAO;

import helper.JDBC;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                String createDate=result.getString("Create_Date");
                String createdBy=result.getString("Created_By");
                String lastUpdate=result.getString("Last_Update");
                String lastUpdateby=result.getString("Last_Updated_By");
                Calendar createDateCalendar=stringToCalendar(createDate);
                Calendar lastUpdateCalendar=stringToCalendar(lastUpdate);
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
                String createDate=result.getString("Create_Date");
                String createdBy=result.getString("Created_By");
                String lastUpdate=result.getString("Last_Update");
                String lastUpdateby=result.getString("Last_Updated_By");
                Calendar createDateCalendar=stringToCalendar(createDate);
                Calendar lastUpdateCalendar=stringToCalendar(lastUpdate);
                User userResult= new User(userid, userNameG, password, createDateCalendar, createdBy, lastUpdateCalendar, lastUpdateby);
                allUsers.add(userResult);
            }
        return allUsers;
    }

}
