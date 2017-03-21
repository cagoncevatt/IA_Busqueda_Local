/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_practica_1_busqueda_local;

/**
 *
 * @author Ale
 */
public class DistributionNetwork {
    
    /* Variables for State Data Structure */
    
    
    /* Constructor */
    public DistributionNetwork() {
        // PH
    }
    
    /* Operator */
    // ChangeEdge / ChangeConnection
    
    // Maybe a SwapEdge with other sensor, center

    /* Heuristic function */
    public double heuristic(){
        // compute the number of coins out of place respect to solution
        return 0;
    }

     /* Goal test */
     public boolean isGoal(){
         // compute if board = solution
         return false;
     }
}
