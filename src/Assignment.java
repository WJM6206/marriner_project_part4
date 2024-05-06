public class Assignment {
    
    //properties to store the combination of a route and an employee
    //helper table for the many-to-many relationship
    public int AID;
    public int RID;
    public int EID;

    public Assignment(int aID, int rID, int eID) {
        AID = aID;
        RID = rID;
        EID = eID;
    }

    public Assignment(int rID, int eID) {        
        RID = rID;
        EID = eID;
    }

    public Assignment(){

    }

    //getters and setters
    public int getAID() {
        return AID;
    }
    public void setAID(int aID) {
        AID = aID;
    }

    public int getRID() {
        return RID;
    }
    public void setRID(int rID) {
        RID = rID;
    }

    public int getEID() {
        return EID;
    }
    public void setEID(int eID) {
        EID = eID;
    }

    
    

}
