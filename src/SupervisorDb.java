/*******************************************************************
* Name: William Marriner
* Date: 03 May, 2024
* Assignment: SDC330 Project Part 4
*
* Class to handle all interactions with the Supervisor table in the
* database, including creating the table if it doesn't exist and all
* CRUD (Create, Read Update, Delete) operations on the Supervisors table.
* Note that the interactions are all done using standard SQL syntax
* that is then executed by the SQLite JDBC library.
*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SupervisorDb {
    public static boolean createTable(Connection conn) {
        // SQL statement for creating a new table
        
        String sql =
            "CREATE TABLE IF NOT EXISTS Supervisor (\n"
            + " ID integer PRIMARY KEY\n"
            + " ,Name varchar(40);";
        
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

    public static void addSupervisor(Connection conn, Supervisor s) {
        String sql =
            "INSERT INTO Supervisor(Name) VALUES(?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, s.Name);            
            pst.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateSupervisor(Connection conn, Supervisor s) {
        String sql =
        "UPDATE Supervisor SET Name=? WHERE id=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, s.Name);
            pst.setInt(2, s.ID);
            pst.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteSupervisor(Connection conn, int id) {
        String sql = "DELETE from Supervisor WHERE ID=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Supervisor> getAllSupervisors(Connection conn) {
        ArrayList<Supervisor> supervisors = new ArrayList<>();
        String sql = "SELECT * FROM Supervisor";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Supervisor s = new Supervisor(rs.getInt("ID"), rs.getString("Name"));
                supervisors.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return supervisors;
    }

    public static Supervisor getSupervisor(Connection conn, int id) {
        Supervisor s = new Supervisor();
        String sql = "SELECT * FROM Supervisor WHERE ID=?";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                s.ID = rs.getInt("ID");
                s.Name = rs.getString("Name");                
            } else {
                s.ID = id;
                s.Name = "Not Found";                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return s;
    }
}