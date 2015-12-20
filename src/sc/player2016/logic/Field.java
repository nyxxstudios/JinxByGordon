package sc.player2016.logic;

import java.util.ArrayList;

/**
 * @author Gordon

=== can contain all Info of one field ====
* 
*/

public class Field {
    /* coordinate - 0<x<25  0<y<25 **/
    public int x;
    public int y;
    
    /* Type of Field - free, blocked, jinx, opponent**/ 
    /*  CHANGE TO ENUM**/
   
    public String type;
    
    private ArrayList<Field> connections = new ArrayList<Field>();
    
    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX(){
		return x;
	}
	
    public int getY(){
		return y;
	}
    
    
}
