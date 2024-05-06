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

public class LotDb {
    public static boolean createTable(Connection conn) {
        // SQL statement for creating a new table
        
        String sql =
            "CREATE TABLE IF NOT EXISTS Lot (\n"
            + " LotID integer PRIMARY KEY\n"
            + " ,NumParkingSpaces integer\n"
            + " ,FenceLengthFt integer\n"
            + " ,LotSqft integer\n"
            + " ,Name varchar(40)\n"
            + " ,StreetAddress varchar(80)\n"
            + " ,City varchar(40)\n"
            + " ,State varchar(2)\n"
            + " ,Zip varchar(5));";
        
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

    public static void addLot(Connection conn, Lot lot) {
        String sql =
            "INSERT INTO Lot(NumParkingSpaces, FenceLengthFt, LotSqft, Name, StreetAddress, City, State, Zip) VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, lot.NumParkingSpaces);
            pst.setInt(2, lot.FenceLengthFt);
            pst.setInt(3, lot.LotSqft);
            pst.setString(4, lot.Name);
            pst.setString(5, lot.StreetAddress);
            pst.setString(6, lot.City);
            pst.setString(7, lot.State);
            pst.setString(8, lot.Zip);            
            pst.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateLot(Connection conn, Lot lot) {
        String sql =
        "UPDATE Lot SET NumParkingSpaces=?, FenceLengthFt=?, LotSqft=?, Name=?, StreetAddress=?, City=?, State=?, Zip=? WHERE LotID=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, lot.NumParkingSpaces);
            pst.setInt(2, lot.FenceLengthFt);
            pst.setInt(3, lot.LotSqft);
            pst.setString(4, lot.Name);
            pst.setString(5, lot.StreetAddress);
            pst.setString(6, lot.City);
            pst.setString(7, lot.State);
            pst.setString(8, lot.Zip);
            pst.setInt(9, lot.LotID);
            pst.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteLot(Connection conn, int id) {
        String sql = "DELETE from Lot WHERE LotID=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Lot> getAllLots(Connection conn) {
        ArrayList<Lot> lots = new ArrayList<>();
        String sql = "SELECT * FROM Lot";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Lot lot = new Lot(rs.getInt("LotID"), rs.getInt("NumParkingSpaces"), rs.getInt("FenceLengthFt"),
                            rs.getInt("LotSqft"), rs.getString("Name"), rs.getString("StreetAddress"), rs.getString("City"),
                            rs.getString("State"),rs.getString("Zip"));
                lots.add(lot);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lots;
    }

    public static Lot getLot(Connection conn, int id) {
        Lot lot = new Lot();
        String sql = "SELECT * FROM Lot WHERE LotID=?";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                lot.LotID = rs.getInt("LotID");
                lot.NumParkingSpaces = rs.getInt("NumParkingSpaces");
                lot.FenceLengthFt = rs.getInt("FenceLengthFt");
                lot.LotSqft = rs.getInt("LotSqft");
                lot.Name = rs.getString("Name");
                lot.StreetAddress = rs.getString("StreetAddress");
                lot.City = rs.getString("City");
                lot.State = rs.getString("State");
                lot.Zip = rs.getString("Zip");
            } else {
                lot.LotID = id;
                lot.NumParkingSpaces = 0;
                lot.FenceLengthFt = 0;
                lot.LotSqft = 0;
                lot.Name = "Not";
                lot.StreetAddress = "Found";
                lot.City = "";
                lot.State = "";
                lot.Zip = "";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lot;
    }
}
