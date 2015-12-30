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
    
    public static Type Type;
    public static State State; 
    
    public enum State { //is captured and if by whom
        JINX,
        OPPONENT,
        BLANK,
    }
    
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

    Field(int x, int y, Type type, ArrayList<Field> connections, GameState gameState, State state) {
        this.x = x;
        this.y = y;
        this.Type = type;
        this.connections = connections;
        this.State = state;
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
    
    public void setState (State state) {
        this.State = state;
    }
    
    public State getState() {
        return State;
    }
    
    public void assignType (GameState gameState) {
        //maybe not the easiest way
        switch (gameState.getBoard().getField(this.x, this.y).getType().toString()) {
                    case "BLUE":
                        if (gameState.getCurrentPlayerColor().toString().equals("BLUE") ){
                            this.setType(Type.JINX);
                        } else if (gameState.getCurrentPlayerColor().toString().equals("RED")){
                            this.setType(Type.OPPONENT);
                        }
                         break;
                    case "RED":
                        if (gameState.getCurrentPlayerColor().toString().equals("BLUE") ){
                            this.setType(Type.OPPONENT);
                        } else if (gameState.getCurrentPlayerColor().toString().equals("RED")){
                            this.setType(Type.JINX);
                        }
                         break;
                    case "SWAMP":     
                        this.setType(Type.SWAMP);
                         break;   
                    case "NORMAL":     
                        this.setType(Type.NORMAL);
                         break;
                    default: break;
        }   
        
    }
    
}
