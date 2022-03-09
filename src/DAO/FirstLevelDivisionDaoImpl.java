package DAO;

import model.FirstLevelDivision;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static utilities.TimeFiles.stringToCalendar;
import helper.JDBC;

/* typically you would also have create, update and read methods*/
public class FirstLevelDivisionDaoImpl {
    //static boolean act;
    public static FirstLevelDivision getDivision(String FirstLevelDivision) throws SQLException, Exception {
        // type is name or phone, value is the name or the phone #
        //JDBC.openConnection();
        String sqlStatement = "select * FROM first_level_divisions WHERE FirstLevelDivision  = '" + FirstLevelDivision + "'";
        //  String sqlStatement="select FROM address";
        Query.makeQuery(sqlStatement);
        FirstLevelDivision divisionResult;
        ResultSet result = Query.getResult();
        while (result.next()) {
            int Division_ID = result.getInt("Division_ID");
            String Division = result.getString("Division");
            Timestamp Create_Date = result.getTimestamp("Create_Date");
            LocalDateTime createDateCalendar = Create_Date.toLocalDateTime();
            String Created_By = result.getString("Created_By");
            Timestamp Last_Update = result.getTimestamp("Last_Update");
            LocalDateTime lastUpdateCalendar = Last_Update.toLocalDateTime();
            String Last_Updated_By = result.getString("Last_Updated_By");
            int COUNTRY_ID = result.getInt("COUNTRY_ID");
            divisionResult = new FirstLevelDivision(Division_ID, Division, createDateCalendar, Created_By, lastUpdateCalendar, Last_Updated_By, COUNTRY_ID);
            return divisionResult;
        }
        //JDBC.closeConnection();
        return null;
    }

    public static ObservableList<FirstLevelDivision> getAllDivisions() throws SQLException, Exception {
        ObservableList<FirstLevelDivision> allDivisions = FXCollections.observableArrayList();
        //JDBC.openConnection();
        String sqlStatement = "select * from first_level_divisions";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        while (result.next()) {
            int Division_ID = result.getInt("Division_ID");
            String Division = result.getString("Division");
            Timestamp Create_Date = result.getTimestamp("Create_Date");
            LocalDateTime createDateCalendar = Create_Date.toLocalDateTime();
            String Created_By = result.getString("Created_By");
            Timestamp Last_Update = result.getTimestamp("Last_Update");
            LocalDateTime lastUpdateCalendar = Last_Update.toLocalDateTime();
            String Last_Updated_By = result.getString("Last_Updated_By");
            int COUNTRY_ID = result.getInt("COUNTRY_ID");
            FirstLevelDivision divisionResult = new FirstLevelDivision(Division_ID, Division, createDateCalendar, Created_By, lastUpdateCalendar, Last_Updated_By, COUNTRY_ID);
            allDivisions.add(divisionResult);

        }
        //JDBC.closeConnection();
        return allDivisions;
    }

    public static ObservableList<FirstLevelDivision> getDivUS() throws SQLException, Exception {
        ObservableList<FirstLevelDivision> divUS = FXCollections.observableArrayList();
        String sqlStatement = "select * from first_level_divisions where COUNTRY_ID = 1";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        while (result.next()) {
            int Division_ID = result.getInt("Division_ID");
            String Division = result.getString("Division");
            Timestamp Create_Date = result.getTimestamp("Create_Date");
            LocalDateTime createDateCalendar = Create_Date.toLocalDateTime();
            String Created_By = result.getString("Created_By");
            Timestamp Last_Update = result.getTimestamp("Last_Update");
            LocalDateTime lastUpdateCalendar = Last_Update.toLocalDateTime();
            String Last_Updated_By = result.getString("Last_Updated_By");
            int COUNTRY_ID = result.getInt("COUNTRY_ID");
            FirstLevelDivision divUSResult = new FirstLevelDivision(Division_ID, Division, createDateCalendar, Created_By, lastUpdateCalendar, Last_Updated_By, COUNTRY_ID);
            divUS.add(divUSResult);
        }
    return divUS;
    }
}
