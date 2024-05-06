/*******************************************************************
* Name: William Marriner
* Date: 03 May, 2024
* Assignment: SDC330 Project Part 4
*
* Class to handle all interactions with the Building table in the
* database, including creating the table if it doesn't exist and all
* CRUD (Create, Read Update, Delete) operations on the Buildings table.
* Note that the interactions are all done using standard SQL syntax
* that is then executed by the SQLite JDBC library.
*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BuildingDb {
    public static boolean createTable(Connection conn) {
        // SQL statement for creating a new table
        
        String sql =
            "CREATE TABLE IF NOT EXISTS Building (\n"
            + " ID integer PRIMARY KEY\n"
            + " ,Length Double(3,1)\n"
            + " ,Width Double(3,1)\n"
            + " ,Color varchar(20)\n"
            + " ,NumFloors int\n"
            + " ,LotID int FOREIGN KEY REFERENCE Lot(LotID) ON DELETE CASCADE);";
        
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

    public static void addBuilding(Connection conn, Building b) {
        String sql =
            "INSERT INTO Building(Length, Width, Color, NumFloors, LotID) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setDouble(1, b.Length);
            pst.setDouble(2, b.Width);
            pst.setString(3, b.Color);
            pst.setInt(4, b.NumFloors);
            pst.setInt(5, b.LotID);
            pst.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateBuilding(Connection conn, Building b) {
        String sql =
        "UPDATE Building SET Length=?, Width=?, Color=?, NumFloors=?, LotID=? WHERE id=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setDouble(1, b.Length);
            pst.setDouble(2, b.Width);
            pst.setString(3, b.Color);
            pst.setInt(4, b.NumFloors);
            pst.setInt(5, b.LotID);
            pst.setInt(6, b.ID);
            pst.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteBuilding(Connection conn, int id) {
        String sql = "DELETE from Building WHERE ID=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Building> getAllBuilding(Connection conn) {
        ArrayList<Building> buildings = new ArrayList<>();
        String sql = "SELECT * FROM Building";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Building b = new Building(rs.getInt("ID"), rs.getDouble("Length"), rs.getDouble("Width"),
                                            rs.getString("Color"), rs.getInt("NumFloors"));
                buildings.add(b);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return buildings;
    }

    public static Building getBuilding(Connection conn, int id) {
        Building b = new Building();
        String sql = "SELECT * FROM Building WHERE ID=?";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                b.ID = rs.getInt("ID");
                b.Length = rs.getDouble("Length");
                b.Width = rs.getDouble("Width");
                b.Color = rs.getString("Color");
                b.NumFloors = rs.getInt("NumFloors");
                b.LotID = rs.getInt("LotID");
            } else {
                b.ID = id;
                b.Length = 0.0;
                b.Width = 0.0;
                b.Color = "Not Found";
                b.NumFloors = -1;
                b.LotID = -1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return b;
    }
}
