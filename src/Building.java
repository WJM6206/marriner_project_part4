/*******************************************************************
* Name: William Marriner
* Date: 28 April, 2024
* Assignment: SDC330 Performance Assessment - Constructors and Access Specifiers
*
* 
*/
public class Building extends Structure {
    //properties
    public int ID;
    public Double Length;
    public Double Width;
    public String Color;
    public int NumFloors;
    public int LotID;

        
    //constructors
    public Building(int iD, Double length, Double width, String color, int numFloors, int lotId) {
        ID = iD;
        Length = length;
        Width = width;
        Color = color;
        NumFloors = numFloors;
        LotID = lotId;        
    }
    
    public Building(int iD, Double length, Double width, String color, int numFloors) {
        ID = iD;
        Length = length;
        Width = width;
        Color = color;
        NumFloors = numFloors;        
    }

    public Building(Double length, Double width, String color, int numFloors, int lotID) {
        Length = length;
        Width = width;
        Color = color;
        NumFloors = numFloors;
        LotID = lotID;
    }

    public Building(Double length, Double width, String color, int numFloors) {
        Length = length;
        Width = width;
        Color = color;
        NumFloors = numFloors;
        LotID = -1;
    }

    public Building() {
        
    }

    //Getters and Setters
    public Double getLength() {
        return Length;
    }
    public void setLength(Double length) {
        Length = length;
    }

    public Double getWidth() {
        return Width;
    }
    public void setWidth(Double width) {
        Width = width;
    }

    public String getColor() {
        return Color;
    }
    public void setColor(String color) {
        Color = color;
    }

    public int getNumFloors() {
        return NumFloors;
    }
    public void setNumFloors(int numFloors) {
        NumFloors = numFloors;
    }

    public void setLotID(int lotID){
        LotID = lotID;
    }

    @Override
    public Double getArea(){
        Double area = Length*Width;
        return area;
    }

    @Override 
    public String toString() {
        return String.format("there is a %.0f by %.0f %s %d story building.%n",Length,Width,Color,NumFloors);
    }


    


    

      
}
