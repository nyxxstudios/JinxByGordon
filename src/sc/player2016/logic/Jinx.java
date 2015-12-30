package sc.player2016.logic;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

import sc.plugin2016.GameState;
import sc.plugin2016.Move;
import sc.player2016.logic.Field;
import sc.player2016.logic.Board;
import sc.plugin2016.FieldType;


public class Jinx {

    static int x = 0;
    static int y = 6;
    
    public static int visualRange = 2; // 2 or 1
    
    static Move lastlastMove;  
    
    public static boolean firstMove = true;
    
    public static Board board ;  /*not in use**/
     
    
    public static Move findMove (GameState gameState){
        
        Move selection;
        
        if (firstMove) {
            firstMove = false;
            
        };
        
        preselectMoves(gameState, lastlastMove);
        
        //Just for Testing 
        if (gameState.getTurn() == 3) {x = 18;y = 7;}
        if (gameState.getTurn() == 5) {x = 18;y = 9;}
        
        selection = new Move(x, y); 
 
        return selection;
    } 

    public static ArrayList<Field> preselectMoves (GameState gameState, Move lastlastMove){
         ArrayList<Field> preselectMoves = new ArrayList();
         
         
           /* preselected moves in realtion to a move **/ 
         int[][] visualRange1 = {
                      {-1,2},{-1,-2},{1,2},{-2,1},{2,1},{-2,-1},{2,-1},{1,-2} 
                };    
           
         int[][] visualRange2 = {
                          {-2,4},{0,4},{2,4},
                       {-3,3},{-1,3},{1,3},{3,3},
                     {-4,2},                      {-4,2},
                          {-3,1},             {3,1},
                     {-4,0},                         {4,0},     
                          {-3,-1},             {3,-1},
                     {-4,-2},                      {-4,-2},
                       {-3,-3},{-1,-3},{1,-3},{3,-3},        
                         {-2,-4},{0,-4},{2,-4}
         };   
         
         int[][] visualRangeSel = null;       
                 
         switch (visualRange) {
            case 1:  visualRange = 1;
                     visualRangeSel = new int[visualRange1.length][visualRange1.length];
                     System.arraycopy(visualRange1, 0, visualRangeSel, 0, visualRange1.length);
                     break;
            case 2:  visualRange = 2;                   
                     visualRangeSel = new int[visualRange1.length + visualRange2.length][visualRange1.length + visualRange2.length ];

                     System.arraycopy(visualRange1, 0, visualRangeSel, 0, visualRange1.length);
                     System.arraycopy(visualRange2, 0, visualRangeSel, visualRange1.length, visualRange2.length);
                     
                     break;
            default:break;
        }
          
         preselectMoves.addAll(pMsub(gameState.getLastMove(), gameState, visualRangeSel));
         
         if (gameState.getTurn() >= 4){ //if GrandKo starts second
         preselectMoves.addAll(pMsub(lastlastMove, gameState, visualRangeSel));
         }
         
         return preselectMoves;
    }
    
    private static ArrayList<Field> pMsub(Move occuMove, GameState gameState, int[][] visualRangeSel) {
        
                ArrayList<Field> preselectMoves = new ArrayList();
                
                for (int[] a : visualRangeSel){
                    int Mx = occuMove.getX();
                    int My = occuMove.getY(); 

                    Mx = Mx + a[0];        
                    My = My + a[1];
                    
                    if (Mx < 24 && My < 24) {
                        Field relatedMove = new Field(Mx, My);

                        relatedMove.assignType(gameState);

                        if (Mx >= 0 && Mx < 24 && My >= 0 && My < 24){
                            //if (Field.Type.SWAMP != relatedMove.getType() && Field.Type.OPPONENT != relatedMove.getType()){
                                    preselectMoves.add(relatedMove);
                                    System.out.println(relatedMove.x + ", " + relatedMove.y);

                           // }
                        }
                    }
            }
            lastlastMove = gameState.getLastMove();
            return preselectMoves;   
            }
}

