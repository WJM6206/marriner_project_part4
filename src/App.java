/*******************************************************************
* Name: William Marriner
* Date: 03 May, 2024
* Assignment: SDC330 Project Part 4
*
* Main application class.
*/

import java.sql.Connection;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        final String dbName = "william.db";
        System.out.println("\nWilliam Marriner, Project Part 4\n");
        Connection conn = SQLiteDatabase.connect(dbName);
        
        
        //create employees and add to DB
        SupervisorDb.addSupervisor(conn, new Supervisor("Will Marriner"));
        SupervisorDb.addSupervisor(conn, new Supervisor("Hey Arnold"));

        WorkerDb.addWorker(conn, new Worker("Bob Trimmer", true));
        WorkerDb.addWorker(conn, new Worker("Ray Cutter", false));
        WorkerDb.addWorker(conn, new Worker("Dwayne Wheels", true));
        WorkerDb.addWorker(conn, new Worker("Sherry Lauper", false));

        Building house1 = new Building(60.0,40.0,"Blue",2,1);
        Building house2 = new Building(120.0,62.0,"Grey",1,2);
        Building house3 = new Building(84.0,39.0,"Red Brick",1,3);
        Building house4 = new Building(60.0,60.0,"Yellow",3,4);
        
        
        //Create Lots and add to DB
        LotDb.addLot(conn, new Lot(10,600,19200,"Mike Roman",
                    "123 ABC St.","Gainsville","FL","33658"));
        LotDb.addLot(conn, new Lot(4,200,10000,"Nancy Pullman",
                    "21894 Betty Ave.","Ocala","FL","33478"));
        LotDb.addLot(conn, new Lot(2,80,10000,"Omar Skywalker",
                    "123 Runaround Way","Gainsville","FL","33658"));
        LotDb.addLot(conn, new Lot(6,0,10000,"Linda Loudmouth",
                    "35621 Othello Ct.","Stark","FL","52489"));        
        
        //Add buildings to the DB by creating them with LotID set.
        //Ideally this would come from a list or dropdown to select ID.
        BuildingDb.addBuilding(conn, house1);
        BuildingDb.addBuilding(conn, house2);
        BuildingDb.addBuilding(conn, house3);
        BuildingDb.addBuilding(conn, house4);
        BuildingDb.addBuilding(conn, new Building(20.0,10.0,"White",1,1));
        BuildingDb.addBuilding(conn, new Building(10.0,5.0,"Pink",1,1));
        BuildingDb.addBuilding(conn, new Building(20.0,20.0,"White and Blue",6,3));
    
        //create Routes
        Route route1 = new Route("A Monday");
        Route route2 = new Route("A Tuesday");
        
        //add lots to routes
        route1.addLot(lot1);
        route1.addLot(lot3);
        route2.addLot(lot2);
        route2.addLot(lot4);

        //add employees to routes
        route1.addEmployee(sup1);
        route1.addEmployee(worker1);
        route1.addEmployee(worker2);

        route2.addEmployee(sup2);
        route2.addEmployee(worker1);
        route2.addEmployee(worker3);
        route2.addEmployee(worker4);

        
        
        //add buildings to lots
        lot1.addStructures(house1);
        lot1.addStructures(house4);
        lot1.addStructures(house3);        
        lot2.addStructures(house2);
        lot3.addStructures(house3);
        lot4.addStructures(house4);
        

        //print employees assigned to routes
        System.out.println("Employees assigned to the route: ");
        System.out.println(route1.getRouteName() + ":");
        System.out.println(route1.getEmployeeInformation());
        System.out.println();
        System.out.println(route2.getRouteName() + ":");
        System.out.println(route2.getEmployeeInformation());

        //print list of buildings on each route
        System.out.println("structures on each route: ");
        System.out.println(route1.getRouteName() + ":");
        System.out.println(route1.getStructureInformation());
        System.out.println();
        System.out.println(route2.getRouteName() + ":");
        System.out.println(route2.getStructureInformation());

        //print list of workable area on each lot for each route
        System.out.println("Workable area on each lot route: ");
        System.out.println(route1.getRouteName() + ":");
        System.out.println(route1.getLotStructureArea());        
        System.out.println();
        System.out.println(route2.getRouteName() + ":");
        System.out.println(route2.getLotStructureArea());        

        
 
    }

    private static void printRoutes(ArrayList<Route> routes) {
        for (Route r : routes) {
            printRoute(r);
        }
    }

    private static void printRoute(Route r) {
        System.out.println("Address " + r.ID + ": ");
        System.out.println("    " + r.Address1);
        if (!"".equals(a.Address2)){
            System.out.println("    " + a.Address2);
        }
        System.out.println("    " + a.City + ", " + a.State + ", "+ a.ZipCode);
    }
}
