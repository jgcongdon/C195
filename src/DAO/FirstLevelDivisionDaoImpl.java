package DAO;

import model.FirstLevelDivision;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class creates the FirstLevelDivision database methods.
 * @author Jackson Congdon
 */
public class FirstLevelDivisionDaoImpl {
    /**
     * This is the method to get the FirstLevelDivision from the getValue of the divisionCombo combo box in CustomersModify. The method executes an SQL query that select the FirstLevelDivision containing the Division matching the getValue of the divisionCombo combo box.
     * @param FirstLevelDivision
     * @return the FirstLevelDivision divisionResult
     * @throws SQLException
     * @throws Exception
     */
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

    /**
     * This is the method to set the ObservableList div with FirstLevelDivisions from a Country ID. The method executes an SQL query to select the FirstLevelDivisions with a Country ID matching the selected Country ID.
     * @param country_id
     * @return the ObservableList div
     * @throws SQLException
     */
    public static ObservableList<FirstLevelDivision> getDiv(int country_id) throws SQLException {
        ObservableList<FirstLevelDivision> div = FXCollections.observableArrayList();
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

            FirstLevelDivision divResult = new FirstLevelDivision(Division_ID, Division, createDateCalendar, Created_By, lastUpdateCalendar, Last_Updated_By, COUNTRY_ID);
            div.add(divResult);
        }
        return div;
    }

    /**
     * This is the method to get a FirstLevelDivision from a Division ID. The method executes an SQL query to select the FirstLevelDivision object with the selected Division ID.
     * @param division_id
     * @return the FirstLevelDivision getDivFromDivIDResult
     * @throws SQLException
     */
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