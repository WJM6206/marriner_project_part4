/*******************************************************************
* Name: William Marriner
* Date: 03 May, 2024
* Assignment: SDC330 Project Part 4
*
* This class implements the Employee interface. 
*/

public class Worker implements Employee {
    public int ID;
    public String Name;
    public Boolean License;

    //Constructors
    public Worker(int iD, String name, Boolean license) {
        ID = iD;
        Name = name;
        License = license;
    }
    
    public Worker(String name, Boolean license) {
        Name = name;
        License = license;
    }

    public Worker(){

    }


    //Getters and setters
    public void setName(String name) {
        Name = name;
    }

    public String getLicense() {
        String msg;
        if (License == true){
            msg = "able to drive.";
        } else {
            msg = "not able to drive.";
        }
        return msg;
    }

    public void setLicense(Boolean license) {
        License = license;
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
        return String.format("Worker %s, and they are %s%n",Name,getLicense());
    }

}
