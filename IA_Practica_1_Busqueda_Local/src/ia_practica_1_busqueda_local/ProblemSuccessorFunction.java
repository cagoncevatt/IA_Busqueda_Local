/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_practica_1_busqueda_local;

import aima.search.framework.SuccessorFunction;
import aima.search.framework.Successor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ale
 */
public class ProblemSuccessorFunction implements SuccessorFunction{
    public List getSuccessors(Object state){
        ArrayList retval = new ArrayList();
        DistributionNetwork board = (DistributionNetwork) state;

        // Some code here
        // Generate successors based operators, for Simulated Annealing will need another way to generate the successors
        // new_state has to be a copy of state

        return retval;

    }

}
