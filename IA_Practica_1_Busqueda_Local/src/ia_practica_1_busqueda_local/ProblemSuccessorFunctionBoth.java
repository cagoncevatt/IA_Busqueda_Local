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
public class ProblemSuccessorFunctionBoth implements SuccessorFunction{

	public List getSuccessors(Object state){
		/*Kosmas: This function creates the successors of the current state.
		 *It uses the operator that changes an edge's destination.
		 *It creates n^2 new states, where n is the size of the network
		 *It is asymptotically optimal: it takes O(n^2) steps.
		 *Of course there is much space for optimization.
		 */
		
		
		ProblemSuccessorFunction sf = new ProblemSuccessorFunction();
		ProblemSuccessorFunctionSwap sfs = new ProblemSuccessorFunctionSwap();
		
		List l1 = sf.getSuccessors(state);
		List l2 = sf.getSuccessors(state);		
		List retVal = l1;
		retVal.addAll(l2);
		return retVal;
		
	}
}
		
		