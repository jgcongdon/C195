package DAO;

import model.Country;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CountryDaoImpl {
    public static Country getCountry(String countryName) throws SQLException, Exception{
        String sqlStatement="select * FROM countries WHERE countryName  = '" + countryName + "'";
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

    public static int countCountry(Country country) throws SQLException {

        String sqlStatement = "SELECT COUNT(*) AS test FROM countries";
        Query.makeQuery(sqlStatement);
        int countCountryResult = 0;
        ResultSet result = Query.getResult();
        while(result.next()) {
            countCountryResult = result.getInt("test");
            return countCountryResult;
        }
        return countCountryResult;
    }

}
