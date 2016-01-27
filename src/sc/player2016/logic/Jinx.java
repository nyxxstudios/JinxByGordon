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
import sc.plugin2016.PlayerColor;


public class Jinx {
    
    private static final Random rand = new SecureRandom();
    
    static int x = 6;
    static int y = 0;
    
    public static int visualRange = 2; // 2 or 1
    
    static Move lastlastMove;  
    
    public static boolean firstMove = true;
    
    public static Board board ;  /*not in use**/
     
    
    public static Move findMove (GameState gameState){
        
        Move selection;
        
        selection = new Move(x, y); 
        
        if (firstMove) {
            firstMove = false;
             System.out.println("Hey There");
            selection = getFirstMove(gameState);
            System.out.println(selection.getX() + ", " + selection.getY());
         };
        
        //preselectMoves(gameState, lastlastMove);
        
        //Just for Testing 
        //if (gameState.getTurn() == 3) {x = 7;y = 0;}
        //if (gameState.getTurn() == 5) {x = 8;y = 0;}
        
 
        return selection;
    } 
    
    public static Move getFirstMove (GameState gameState) {
            ArrayList<Move> firstMoves1 = new ArrayList();
            ArrayList<Move> firstMoves2 = new ArrayList();
            ArrayList<Move> firstMoves3 = new ArrayList();
            
            int [] horiz = {8, 15};
            int [] vertic = {5, 18};
            
            switch (gameState.getStartPlayerColor()) {
                case BLUE : 
                    //do nothing default;
                    break;
                case RED :
                    horiz[0] = 5; 
                    horiz[1] = 18;
                    vertic[0] = 8;
                    vertic[1] = 15;
                    break;
            }  
            
            firstMoves1.addAll(gameState.getPossibleMoves());
            
            System.out.println("Debug1");
            
    //Level 1
            // tests if Move is in Field of Interest(the Middel)
            for (int i = (firstMoves1.size() - 1); i >= 0; i--) {
                if (firstMoves1.get(i).getX() >= horiz[0] && horiz[1] >= firstMoves1.get(i).getX()){
                    if (firstMoves1.get(i).getY() >= vertic[0] && vertic[1] >= firstMoves1.get(i).getY()) {
                        firstMoves2.add(firstMoves1.get(i));
                    }
                }
            System.out.println(i);
            }
    // Level 2    
            System.out.println("Debug2");
    
            int [][] neighborhood = {
                {0, -1}, {1, -1}, {1,0}, {1,1}, {0,1}, {-1,1}, {-1, 0}, {-1,-1}
            };
    
            for (int i = (firstMoves2.size() -1) ; i >= 0;  i--) { 
                boolean swampNear = false;
                for (int j = (neighborhood.length -1); j >= 0; j--) {
                    
                    int mx = firstMoves2.get(i).getX();
                    int my = firstMoves2.get(i).getY(); 
                        
                    mx = mx + neighborhood[j][0];
                    my = my + neighborhood[j][1];
                    
                    Field occuField = new Field(mx, my);
                    
                    occuField.assignType(gameState);
                    
                    if (occuField.getType() == Field.type.SWAMP){
                        swampNear = true;
                    }
                    
                } 
                if (!swampNear){
                    firstMoves3.add(firstMoves2.get(i));
                }
            
            } 
    //Level 3     
            System.out.println("Debug3");
            
            
            Move result = firstMoves3.get(rand.nextInt(firstMoves3.size()));;
            
            return result;
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


    public static int randInt(int min, int max) {

        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}

