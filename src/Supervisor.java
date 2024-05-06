/*******************************************************************
* Name: William Marriner
* Date: 28 April, 2024
* Assignment: SDC330 Project Part 3
*
* This class implements the Employee interface. 
*/

public class Supervisor implements Employee {
    public int ID;
    public String Name;

    //constructor
    public Supervisor(int iD, String name) {
        ID = iD;
        Name = name;
    }

    public Supervisor(String name) {        
        Name = name;
    }

    public Supervisor(){
        
    }

    
    
    @Override
    public String getName() {
        return Name;
    }

    @Override
    public String getRole() {
        return getClass().getName();
    }

    @Override
    public String toString() {
        return String.format("Supervisor %s, and they are able to drive.%n",Name);
    }

}
