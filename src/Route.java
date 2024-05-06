/*******************************************************************
* Name: William Marriner
* Date: 28 April, 2024
* Assignment: SDC330 Performance Assessment - Constructors and Access Specifiers
*
* This class constructor with no imput arguments. This is
*done by making the main constructor private and using a no 
*argument constructor set safe values calling the private constructor
*/
import java.util.ArrayList;

public class Route {
    //properties
    public int RID;
    public String RouteName;
    public int SupervisorID;
    

    public Route(int rID, String name){
        RID = rID;
        RouteName = name;
    }
    
    public Route(String name){
        RouteName = name;
    }

    public Route(){

    }

    //setters and getters
    public String getRouteName() {
        return RouteName;
    }

    public void setRouteName(String name) {
        RouteName = name;
    }


    //Methods
    

    public String getStructureInformation() {
        String rval = "";
        for (Lot lot : lots) {
            rval += lot.getStructureDetails();
        }
        return rval;
    }
    
    public String getLotsInformation() {
        String rval = "";
        for (Lot lot : lots){
            rval += lot.toString();
        }
        return rval;
    }

    public String getWorkArea(Lot lot) {
        String rval = "";
        //for (Lot lot : lots){
            rval += String.format("On this %dsqft lot,there is %,.0fsqft of workable area with %d feet of fencing.\n\n",
                                    lot.getLotSqft(), lot.getWorkableArea(),lot.getFenceLengthFt());
        //}
        return rval;
    }

    public String getLotStructureArea() {
        String rval = "";
        for (Lot lot : lots){
            rval += lot.getStructureArea() + "\n";
            rval += getWorkArea(lot);                      
        }
        
        return rval;
    };
        
    
}
