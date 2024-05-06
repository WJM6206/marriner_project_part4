/*******************************************************************
* Name: William Marriner
* Date: 03 May, 2024
* Assignment: SDC330 Project Part 4
*
* Class to handle all interactions with the Route table in the
* database, including creating the table if it doesn't exist and all
* CRUD (Create, Read Update, Delete) operations on the Routes table.
* Note that the interactions are all done using standard SQL syntax
* that is then executed by the SQLite JDBC library.
*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RouteDb {
    public static boolean createTable(Connection conn) {
        // SQL statement for creating a new table
        
        String sql =
            "CREATE TABLE IF NOT EXISTS Route (\n"
            + " RID integer PRIMARY KEY\n"
            + " ,RouteName varchar(20)\n"
            + " ,SupervisorID integer\n"
            + " ,Color varchar(20)\n"
            + " ,NumFloors int);";
        
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

    public static void addRoute(Connection conn, Route r) {
        String sql =
            "INSERT INTO Route(Length, Width, Color, NumFloors, LotID) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setDouble(1, r.Length);
            pst.setDouble(2, r.Width);
            pst.setString(3, br.Color);
            pst.setInt(4, r.NumFloors);
            pst.setInt(5, r.LotID);
            pst.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateRoute(Connection conn, Route r) {
        String sql =
        "UPDATE Route SET Length=?, Width=?, Color=?, NumFloors=?, LotID=? WHERE id=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setDouble(1, r.Length);
            pst.setDouble(2, r.Width);
            pst.setString(3, r.Color);
            pst.setInt(4, r.NumFloors);
            pst.setInt(5, r.LotID);
            pst.setInt(6, r.ID);
            pst.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteRoute(Connection conn, int id) {
        String sql = "DELETE from Route WHERE ID=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Route> getAllRoutes(Connection conn) {
        ArrayList<Route> routes = new ArrayList<>();
        String sql = "SELECT * FROM Route";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Route r = new Route(rs.getInt("RID"), rs.getString("RouteName"), rs.getDouble("Width"),
                                            rs.getString("Color"), rs.getInt("NumFloors"));
                routes.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return routes;
    }

    public static Route getRoute(Connection conn, int id) {
        Route r = new Route();
        String sql = "SELECT * FROM Route WHERE ID=?";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                r.ID = rs.getInt("ID");
                r.Length = rs.getDouble("Length");
                r.Width = rs.getDouble("Width");
                r.Color = rs.getString("Color");
                r.NumFloors = rs.getInt("NumFloors");
                r.LotID = rs.getInt("LotID");
            } else {
                r.ID = id;
                r.Length = 0.0;
                r.Width = 0.0;
                r.Color = "Not Found";
                r.NumFloors = -1;
                r.LotID = -1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return r;
    }
}
