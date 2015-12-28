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
    public static Type Type;
   
    public enum Type {
        Jinx,
        Opponent,
        Blocked,
        Free
    }
    
    private ArrayList<Field> connections = new ArrayList<Field>();
    
    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Field(int x, int y, Type type, ArrayList<Field> connections) {
        this.x = x;
        this.y = y;
        this.Type = type;
        this.connections = connections;
    }
    
    public int getX(){
		return x;
	}
	
    public int getY(){
		return y;
	}
    
    public Type getType (){
        return Type;
    }
    
    public void setType (Type type){
        this.Type = type;
    }
    
    
}
