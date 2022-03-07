package DAO;

import model.Country;
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
public class CountryDaoImpl {
    //static boolean act;
    public static Country getCountry(String countryName) throws SQLException, Exception{
        // type is name or phone, value is the name or the phone #
        //JDBC.openConnection();
        String sqlStatement="select * FROM countries WHERE countryName  = '" + countryName + "'";
        //  String sqlStatement="select FROM address";
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
        //JDBC.closeConnection();
        return null;
    }
    public static ObservableList<Country> getAllCountries() throws SQLException, Exception{
        ObservableList<Country> allCountries=FXCollections.observableArrayList();
        //JDBC.openConnection();
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
        //JDBC.closeConnection();
        return allCountries;
    }
}
