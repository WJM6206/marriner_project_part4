/*******************************************************************
* Name: William Marriner
* Date: 20 April, 2024
* Assignment: SDC330 Performance Assessment - Constructors and Access Specifiers
*
* This class demonstrates using public, private, and protected access
* specifiers. Of note, the protected constructor means that this
* class cannot be instantiated directly - only a subclass can call
* this class' constructor. The member variables are not accessible
* outside of this class. The protected methods are available to
* subclasses and other classes within the same package as this class.
*/
import java.util.ArrayList;

public class Lot {
    public int LotID;
    public int NumParkingSpaces;
    public int FenceLengthFt;
    public int LotSqft;
    public String Name;
    public String StreetAddress;
    public String City;
    public String State;
    public String Zip;
    public int RID;
    private ArrayList<Structure> structures;
    
    //constructor
    public Lot(int iD, int numParkingSpaces, int fenceLengthFt, int lotSqft, String name, String streetAddress,
            String city, String state, String zip, int rID) {
        LotID = iD;
        NumParkingSpaces = numParkingSpaces;
        FenceLengthFt = fenceLengthFt;
        LotSqft = lotSqft;
        Name = name;
        StreetAddress = streetAddress;
        City = city;
        State = state;
        Zip = zip;
        RID = rID;
        structures = new ArrayList<>();     
    }
    
    public Lot(int iD, int numParkingSpaces, int fenceLengthFt, int lotSqft, String name, String streetAddress,
            String city, String state, String zip) {
        LotID = iD;
        NumParkingSpaces = numParkingSpaces;
        FenceLengthFt = fenceLengthFt;
        LotSqft = lotSqft;
        Name = name;
        StreetAddress = streetAddress;
        City = city;
        State = state;
        Zip = zip;
        structures = new ArrayList<>();     
    }

    public Lot(int numParkingSpaces, int fenceLengthFt, int lotSqft, String name, String streetAddress,
            String city, String state, String zip) {
        NumParkingSpaces = numParkingSpaces;
        FenceLengthFt = fenceLengthFt;
        LotSqft = lotSqft;
        Name = name;
        StreetAddress = streetAddress;
        City = city;
        State = state;
        Zip = zip;
        structures = new ArrayList<>();
        
    }

    public Lot(){

    }

    

    //public getters; protected setters
    public int getNumParkingSpaces() {
        return NumParkingSpaces;
    }
    public void setNumParkingSpaces(int numParkingSpaces) {
        NumParkingSpaces = numParkingSpaces;
    }

    public int getFenceLengthFt() {
        return FenceLengthFt;
    }
    public void setFenceLengthFt(int fenceLengthFt) {
        FenceLengthFt = fenceLengthFt;
    }

    public int getLotSqft() {
        return LotSqft;
    }
    public void setLotSqft(int lotSqft) {
        LotSqft = lotSqft;
    }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    public String getStreetAddress() {
        return StreetAddress;
    }
    public void setStreetAddress(String streetAddress) {
        StreetAddress = streetAddress;
    }

    public String getCity() {
        return City;
    }
    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }
    public void setState(String state) {
        State = state;
    }

    public String getZip() {
        return Zip;
    }
    public void setZip(String zip) {
        Zip = zip;
    }    
    

    //calculate working area by subtracting strucure area from the lot size
    public Double getWorkableArea(ArrayList<Structure> structures) {
        Double size = 0.0;
        Double structureSize = 0.0;
        for (Structure structure : structures){  
            structureSize = structureSize + structure.getArea();
            size = getLotSqft() - structureSize;
        }

        return size;
    }

    //return structure area details for a lot
    public String getStructureArea() {
        int i=structures.size();
        String msg = "At " + getFullAddress() + " there is:";
        for (Structure structure : structures){
            if (i==structures.size()){
                msg += String.format(" a %,.0f sqft building",structure.getArea());
                i--;
            } 
            else if (i > 1){
                msg += String.format(", a %,.0f sqft building",structure.getArea());
                i--;
            }
            else if (i==1) {
                msg += String.format(", and a %,.0f sqft building",structure.getArea());
                i--;
            }
        }
        return msg;
    }
    
    
    //formatted structure information
    public String getStructureDetails() {
        String msg = "";
        for (Structure structure : structures){  
            msg += "At " + getStreetAddress() + ", ";         
            msg += structure.toString();
        }
        return msg;
    }

    //formatted full address
    public String getFullAddress() {
        return String.format("%s %s %s %s ",getStreetAddress(),getCity(),getState(),getZip());
    }
    
}
