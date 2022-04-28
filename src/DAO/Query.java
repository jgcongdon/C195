package DAO;

import java.sql.ResultSet;
import java.sql.Statement;
import static helper.JDBC.*;

/**
 * This class creates the query database methods.
 * @author Jackson Congdon
 */
public class Query {
    private static String query;
    private static Statement stmt;
    private static ResultSet result;

    /**
     * This is the method to make an SQL query.
     * @param q the String to make the query from
     */
    public static void makeQuery(String q){
        query =q;
        try{
            stmt=connection.createStatement();
            // determine query execution
            if(query.toLowerCase().startsWith("select"))
                result=stmt.executeQuery(q);
             if(query.toLowerCase().startsWith("delete")||query.toLowerCase().startsWith("insert")||query.toLowerCase().startsWith("update"))
                stmt.executeUpdate(q);
        } catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
    }

    /**
     * This is the method to return the result from the ResultSet of the SQL query.
     * @return the result from ResultSet
     */
    public static ResultSet getResult(){
        return result;
    }
}