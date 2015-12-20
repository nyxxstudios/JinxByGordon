package sc.player2016.logic;


import sc.player2016.logic.Field;



/**
 *
 * @author gordonkoehn
 */
public class Board {
    Field[][] fields = new Field[24][24];
    
    public Field getField(int x, int y){
		return fields[x] [y];
	}
}
