package database;

import java.sql.*;

/**
 * This singleton class represents the database connection via JDBC.
 * All database specific operations are handled here.
 * Author: Mario Pagger, 1330049
 */
public class DatabaseManager {
    private static DatabaseManager instance = null;
    private Connection conn = null;
    private ResultSet rs = null;
    private Statement statement = null;
    private PreparedStatement preStatement = null;

    private DatabaseManager()
    {
       try
       {
           Class.forName("com.mysql.jdbc.Driver");
           conn = DriverManager.getConnection("jdbc:mysql://localhost/streetmapdatabase",
                                                "sa2014", "12345");
           System.out.println("Connected! :)");
       }
       catch (Exception e)
       {
           System.out.println("Error connecting database: " + e.getMessage());
       }
    }

    public static DatabaseManager getInstance()
    {
        if(instance == null)
            instance = new DatabaseManager();
        return instance;
    }

    public void disconnect()
    {
        try
        {
            conn.close();
        }
        catch (SQLException ex)
        {
            System.out.println("SQL Exception: " + ex.getMessage());
        }
    }

    public ResultSet getStops()
    {
        ResultSet rs = null;
        try {
        String stops_query = "SELECT * FROM stops";
        preStatement = conn.prepareStatement(stops_query);
            rs = preStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }

        return rs;
    }
}
