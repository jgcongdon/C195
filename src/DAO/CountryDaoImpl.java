package DAO;

import model.Country;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class creates the Country database methods.
 * @author Jackson Congdon
 */
public class CountryDaoImpl {
    /**
     * This is the method to create an ObservableList to get all countries. This method executes an SQL query to find all countries and then adds the countries to the ObservableList allCountries.
     * @return the ObservableList allCountries
     * @throws SQLException
     * @throws Exception
     */
    public static ObservableList<Country> getAllCountries() throws SQLException, Exception{
        ObservableList<Country> allCountries=FXCollections.observableArrayList();
        String sqlStatement="select * from countries";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){
            int Country_ID=result.getInt("Country_ID");
            String Country=result.getString("Country");
            Timestamp Create_Date=result.getTimestamp("Create_Date");
            LocalDateTime createDateCalendar=Create_Date.toLocalDateTime();
            String Created_By=result.getString("Created_By");
            Timestamp Last_Update=result.getTimestamp("Last_Update");
            LocalDateTime lastUpdateCalendar=Last_Update.toLocalDateTime();
            String Last_Updated_By=result.getString("Last_Updated_By");

            Country countryResult= new Country(Country_ID, Country, createDateCalendar, Created_By, lastUpdateCalendar, Last_Updated_By);
            allCountries.add(countryResult);
        }
        return allCountries;
    }

    /**
     * This is the method to get a Country object from a Country ID. The method executes an SQL query to select the country from the Countries table with the selected Country ID.
     * @param CountryID the country ID to get the country object for
     * @return the Country countryResult
     * @throws SQLException
     * @throws Exception
     */
    public static Country getCountryFromCountryID(int CountryID) throws SQLException, Exception{
        String sqlStatement="select * FROM countries WHERE Country_ID  = '" + CountryID + "'";
        Query.makeQuery(sqlStatement);
        Country countryResult;
        ResultSet result=Query.getResult();
        while(result.next()){
            int Country_ID=result.getInt("Country_ID");
            String Country=result.getString("Country");
            Timestamp Create_Date=result.getTimestamp("Create_Date");
            LocalDateTime createDateCalendar=Create_Date.toLocalDateTime();
            String Created_By=result.getString("Created_By");
            Timestamp Last_Update=result.getTimestamp("Last_Update");
            LocalDateTime lastUpdateCalendar=Last_Update.toLocalDateTime();
            String Last_Updated_By=result.getString("Last_Updated_By");

            countryResult= new Country(Country_ID, Country, createDateCalendar, Created_By, lastUpdateCalendar, Last_Updated_By);
            return countryResult;
        }
        return null;
    }
}
