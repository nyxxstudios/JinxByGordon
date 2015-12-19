package sc.player2016.logic;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

import sc.plugin2016.GameState;
import sc.plugin2016.Move;

/**
 *
 * @author gordonkoehn
 */
public class Jinx {

    static int x = 1;
    static int y = 2;

    public static Move findMove (GameState gameState){
        Move selection;
        
        selection = new Move(x, y); 

        return selection;
    }     
    public static ArrayList<Field> preselectMoves (GameState gameState){
        ArrayList<Field> preselectMoves = null;
        
        return preselectMoves;
    }
}
