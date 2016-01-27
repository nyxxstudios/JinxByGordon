package sc.player2016.logic;

import java.util.ArrayList;
import sc.plugin2016.GameState;
import sc.plugin2016.Board;

/**
 * @author Gordon
=== can contain all Info of one field ===
*/

public class Field {
    
    /* coordinate - 0<x<24  0<y<24 **/
    public int x;
    public int y;
    
    public static Type type;
 
    
    
    public enum Type { //kind of Field my game init   
        JINX, 
        OPPONENT,
        SWAMP,
        NORMAL,
    }
    
    private ArrayList<Field> connections = new ArrayList<Field>();
    
    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Field(int x, int y, Type type, ArrayList<Field> connections) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.connections = connections;
    }
    
    public int getX(){
		return x;
    }
	
    public int getY(){
		return y;
    }
    
    public Type getType (){
        return type;
    }
    
    public void setType (Type type){
        this.type = type;
    }

    
    public void assignType (GameState gameState) {
        //maybe not the easiest way
        switch (gameState.getBoard().getField(this.x, this.y).getType().toString()) {
                    case "BLUE":
                        if (gameState.getCurrentPlayerColor().toString().equals("BLUE") ){
                            this.setType(type.JINX);
                        } else if (gameState.getCurrentPlayerColor().toString().equals("RED")){
                            this.setType(type.OPPONENT);
                        }
                         break;
                    case "RED":
                        if (gameState.getCurrentPlayerColor().toString().equals("BLUE") ){
                            this.setType(type.OPPONENT);
                        } else if (gameState.getCurrentPlayerColor().toString().equals("RED")){
                            this.setType(type.JINX);
                        }
                         break;
                    case "SWAMP":     
                        this.setType(type.SWAMP);
                         break;   
                    case "NORMAL":     
                        this.setType(type.NORMAL);
                         break;
                    default: break;
        }   
        
    }
    
}
