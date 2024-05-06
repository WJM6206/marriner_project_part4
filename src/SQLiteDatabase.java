/*******************************************************************
* Name: William Marriner
* Date: 03 May, 2024
* Assignment: SDC330 Project Part 4
*
* Class to handle database interactions with a SQLite database. The
* connect method will either connect to an existing database or
* create the database if the database doesn't exist.
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDatabase {
    public static Connection connect(String database) {
        String url = "jdbc:sqlite:" + database;
        Connection conn = null;
    
    try {
        conn = DriverManager.getConnection(url);
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }

    return conn;
    }
}