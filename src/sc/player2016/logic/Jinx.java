package sc.player2016.logic;

import java.security.SecureRandom;
import java.util.Random;

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
}
