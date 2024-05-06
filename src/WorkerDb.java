/*******************************************************************
* Name: William Marriner
* Date: 03 May, 2024
* Assignment: SDC330 Project Part 4
*
* Class to handle all interactions with the Worker table in the
* database, including creating the table if it doesn't exist and all
* CRUD (Create, Read Update, Delete) operations on the Workers table.
* Note that the interactions are all done using standard SQL syntax
* that is then executed by the SQLite JDBC library.
*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class WorkerDb {
    public static boolean createTable(Connection conn) {
        // SQL statement for creating a new table
        
        String sql =
            "CREATE TABLE IF NOT EXISTS Worker (\n"
            + " ID integer PRIMARY KEY\n"
            + " ,Name varchar(40)\n"
            + " ,License Boolean);";
        
        System.out.println(sql);
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static void addWorker(Connection conn, Worker w) {
        String sql =
            "INSERT INTO Worker(Name, License) VALUES(?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, w.Name);
            pst.setBoolean(2, w.License);
            pst.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateWorker(Connection conn, Worker w) {
        String sql =
        "UPDATE Worker SET Name=?, License=? WHERE id=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, w.Name);
            pst.setBoolean(2, w.License);
            pst.setInt(3, w.ID);
            pst.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteWorker(Connection conn, int id) {
        String sql = "DELETE from Worker WHERE ID=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Worker> getAllWorker(Connection conn) {
        ArrayList<Worker> workers = new ArrayList<>();
        String sql = "SELECT * FROM Worker";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Worker w = new Worker(rs.getInt("ID"), rs.getString("Name"), rs.getBoolean("License"));
                workers.add(w);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return workers;
    }

    public static Worker getWorker(Connection conn, int id) {
        Worker w = new Worker();
        String sql = "SELECT * FROM Worker WHERE ID=?";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                w.ID = rs.getInt("ID");
                w.Name = rs.getString("Name");
                w.License = rs.getBoolean("License");
            } else {
                w.ID = id;
                w.Name = "Not Found";
                w.License = null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return w;
    }
}