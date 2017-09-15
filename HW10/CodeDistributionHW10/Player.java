/* File Name: Player.java
 * Authors: Sameena Bajwa (sbajwa@bu.edu) and Nathan Weinberg (nathansw@bu.edu)
 * Purpose: Act as class blueprint for any Player object in SimGame.java
 * Date: April 29, 2016
 * CS112 - Final Project
 */

import java.lang.Math;

public class Player {
    
    // maximum depth
    private static final int D = 6;
    
    // "infinity" value
    private final int Inf = 1000000;
    
    // initialize Best move
    private Move best;

    // Picks best next move for the graph 
    public Move chooseMove(Graph G) {
        
        int max = -Inf;
        
        // iterate through each possible edge 
        for (int i = 0; i < G.board.length; ++i) { 
            for (int j = 1; j < G.board.length; ++ j) {     
                
                // checks for blank edge 
                if ((G.getEdge(i, j) == 0) && i != j) {
                    
                    // creates new potential move
                    Move newMove = new Move (i, j);
                    
                    // executes new potential move
                    G.addEdge (i, j, -1);
                    
                    // finds value of new potential move from minMax method
                    int val = minMax (G, D, -Inf, Inf);
                    
                    // checks if new move is in fact the best possible move 
                    if (val > max) {
                        
                        best = newMove;
                        max = val;
                        
                    }
                    
                    // removes move for next iteration
                    G.removeEdge (i, j);
                    
                }
            }
        }
        
        return best;
        
    }

    
    // Calculates a move value based off board value based off eval method
    public int minMax(Graph G, int depth, int alpha, int beta) {
        
        // if there is a winning move on the board or depth is reached, return score of current board
        if ( (G.isCycleOfLength(3, -1)) || (G.isCycleOfLength(3, 1)) || (depth == D) )
            
            return eval(G);
        
        // if G is a "max" board
        else if ( depth % 2 == 0 ) {
            
            int val = -100000;
            
            // iterate through each possible edge
            for (int i = 0; i < G.board.length; ++i) {               
                for (int j = 1; j < G.board.length; ++ j) { 
                    
                    if ((G.isEdge (i, j) == false) && (i != j)) {
                        
                        // update alpha with max so far
                        alpha = Math.max(alpha, val);
                    
                        if (beta <= alpha) {
                            break;
                        }
                        
                        G.addEdge (i, j, -1);
                        
                        val = Math.max(val, minMax(G, depth + 1, alpha, beta));
                        G.removeEdge (i, j);
                    }
                }
                
            }
            return val;
        }
        
        // if G is a "min" board
        else {
            
            int val = 100000;
            
            // iterate through each possible edge
            for (int i = 0; i < G.board.length; ++i) {            
                for (int j = 1; j < G.board.length; ++ j) {
                    
                     if ((G.isEdge (i, j) == false) && (i != j)) {
                    
                         // update beta with min so far
                         beta = Math.min(beta, val);
                    
                         if (beta <= alpha)
                             break;
                         
                         G.addEdge (i, j, 1);
          
                         val = Math.min(val, minMax(G, depth + 1, alpha, beta));
                         
                         G.removeEdge (i, j);
                     }
                }
            }

            return val;                         
        }
    }
    
    // evaluation function
    public int eval(Graph G) {
        
        // returns a winning score for blue if there is a red triangle
        if (G.isCycleOfLength(3, 1) == true)
            return 100000;
        
        // returns a losing score for blue if there is a blue triangle
        else if (G.isCycleOfLength(3, -1) == true) 
            return -100000;
        
        // else, return int based off strategy detailed on assignment page/wiki
        else {
            
            int A = 0;
            int B = 0;
            
            for (int i = 0; i < G.board.length; ++i) {            
                for (int j = 1; j < G.board.length; ++ j) {      
                    
                    if ( (G.isEdge(i, j) == true) && (G.getEdge(i, j) == -1) )
                        B++;
                    
                    if ( (G.isEdge(i, j) == true) && (G.getEdge(i, j) == 1) )
                        A++;                    
                }
            }    
            
            return B - A;
        }    
    }
        

}