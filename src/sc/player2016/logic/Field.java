package sc.player2016.logic;

import java.util.ArrayList;

/**
 * @author Gordon

=== can contain all Info of one field ====
* 
*/

public class Field {
    /* coordinate - 0<x<25  0<y<25 **/
    private int x;
    private int y;
    
    /* Type of Field - free, blocked, jinx, opponent**/ 
    private String type;
    
    private ArrayList<Field> connecions = new ArrayList<Field>();
    
}
