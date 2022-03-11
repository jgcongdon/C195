package DAO;

import model.FirstLevelDivision;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FirstLevelDivisionDaoImpl {
    public static FirstLevelDivision getDivision(String FirstLevelDivision) throws SQLException, Exception {
        String sqlStatement = "select * FROM first_level_divisions WHERE FirstLevelDivision  = '" + FirstLevelDivision + "'";
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
        return null;
    }

    public static int getDivID(String FirstLevelDivision) throws SQLException, Exception {
        String sqlStatement = "select Division_ID from first_level_divisions where Division = '" + FirstLevelDivision + "'";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        while (result.next()) {
            int Division_ID = result.getInt("Division_ID");
            return Division_ID;
        }
        return Integer.parseInt(null);
    }

    public static ObservableList<FirstLevelDivision> getAllDivisions() throws SQLException, Exception {
        ObservableList<FirstLevelDivision> allDivisions = FXCollections.observableArrayList();
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

    public static ObservableList<FirstLevelDivision> getDivUK() throws SQLException, Exception {
        ObservableList<FirstLevelDivision> divUK = FXCollections.observableArrayList();
        String sqlStatement = "select * from first_level_divisions where COUNTRY_ID = 2";
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
            FirstLevelDivision divUKResult = new FirstLevelDivision(Division_ID, Division, createDateCalendar, Created_By, lastUpdateCalendar, Last_Updated_By, COUNTRY_ID);
            divUK.add(divUKResult);
        }
        return divUK;
    }

    public static ObservableList<FirstLevelDivision> getDivCAN() throws SQLException, Exception {
        ObservableList<FirstLevelDivision> divCAN = FXCollections.observableArrayList();
        String sqlStatement = "select * from first_level_divisions where COUNTRY_ID = 3";
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
            FirstLevelDivision divCANResult = new FirstLevelDivision(Division_ID, Division, createDateCalendar, Created_By, lastUpdateCalendar, Last_Updated_By, COUNTRY_ID);
            divCAN.add(divCANResult);
        }
        return divCAN;
    }

}
