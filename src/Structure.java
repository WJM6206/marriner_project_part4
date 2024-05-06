/*******************************************************************
* Name: William Marriner
* Date: 28 April, 2024
* Assignment: SDC330 Project Part 3
*
* Super class for the various structures that can be on
* the lots. Provides properties for length and width in 
* order to find the area the structure takes up on the lot. 
*/
public abstract class Structure {
    protected Double length;
    protected Double width;
    
    
    //methods
    public Double getArea() {
        Double area;
        area = (length * width);
        return area;
    }
     

   
}
