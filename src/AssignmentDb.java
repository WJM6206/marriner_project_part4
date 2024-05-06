/*******************************************************************
* Name: William Marriner
* Date: 03 May, 2024
* Assignment: SDC330 Project Part 4
*
* Class to handle all interactions with the Assignment table in the
* database, including creating the table if it doesn't exist and all
* CRUD (Create, Read Update, Delete) operations on the Assignment table.
* Note that the interactions are all done using standard SQL syntax
* that is then executed by the SQLite JDBC library.
* This is a helper table for the many-to-many relationship between
* employees and assigned routes.
*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AssignmentDb {
    public static boolean createTable(Connection conn) {
        // SQL statement for creating a new table
        
        String sql =
            "CREATE TABLE IF NOT EXISTS Assignment (\n"
            + " ID integer PRIMARY KEY\n"
            + " ,RID integer\n"
            + " ,EID integer\n"
            + " ,CONSTRAINT UC_Assignment UNIQUE (RID,EID));";
        
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

    public static void addAssignment(Connection conn, Route r, Worker w) {
        String sql =
            "INSERT INTO Assignment(RID, EID) VALUES(?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);            
            pst.setInt(1, r.RID);
            pst.setInt(2, w.ID);
            pst.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateAssignment(Connection conn, Route r, Worker w) {
        String sql =
        "UPDATE Assignment SET RID=?, EID=? WHERE id=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, r.RID);
            pst.setInt(2, w.ID);            
            pst.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteAssignment(Connection conn, int id) {
        String sql = "DELETE from Assignment WHERE ID=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Assignment> getAllAssignmentsByRoute(Connection conn, int id) {
        ArrayList<Assignment> assignments = new ArrayList<>();
        String sql = "SELECT * FROM Route WHERE RID=?";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);            
            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                Assignment a = new Assignment(rs.getInt("ID"), rs.getInt("RID"), rs.getInt("EID"));
                assignments.add(a);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return assignments;
    }

    public static ArrayList<Assignment> getAllAssignmentsByEmployee(Connection conn, int id) {
        ArrayList<Assignment> assignments = new ArrayList<>();
        String sql = "SELECT * FROM Assignments WHERE EID=?";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);            
            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                Assignment a = new Assignment(rs.getInt("ID"), rs.getInt("RID"), rs.getInt("EID"));
                assignments.add(a);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return assignments;
    }
}
