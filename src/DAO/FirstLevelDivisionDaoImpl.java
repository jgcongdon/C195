package DAO;

import model.FirstLevelDivision;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FirstLevelDivisionDaoImpl {
    public static FirstLevelDivision getDivision(FirstLevelDivision FirstLevelDivision) throws SQLException, Exception {
        String sqlStatement = "select * FROM first_level_divisions WHERE Division  = '" + FirstLevelDivision + "'";
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

    public static ObservableList<FirstLevelDivision> getDiv(int country_id) throws SQLException {
        ObservableList<FirstLevelDivision> divCAN = FXCollections.observableArrayList();
        String sqlStatement = "select * from first_level_divisions where COUNTRY_ID = " + country_id;
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

    public static FirstLevelDivision getDivFromDivID(int division_id) throws SQLException {
        String sqlStatement = "select * from first_level_divisions where DIVISION_ID = " + division_id;
        Query.makeQuery(sqlStatement);
        FirstLevelDivision getDivFromDivIDResult;
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

            getDivFromDivIDResult = new FirstLevelDivision(Division_ID, Division, createDateCalendar, Created_By, lastUpdateCalendar, Last_Updated_By, COUNTRY_ID);
            return getDivFromDivIDResult;
        }
        return null;
    }
}