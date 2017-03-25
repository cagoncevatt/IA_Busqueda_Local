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
	/*Kosmas: This function creates the successors of the current state.
	 *It uses the operator changes an edge's destination.
	 *It creates n^2 new states, where n is the size of the network
	 *It is asymptotically optimal: it takes O(n^2) steps.
	 *Of course there is much space for optimization.
	*/

        ArrayList retVal = new ArrayList(); 
        DistributionNetwork oldState = (DistributionNetwork) state;
	Connection[] connections= oldState.getNetwork();
	int src;
	int size=connections.size;               //total number of components
	int nSensors = oldState.mSensors.size(); //number of sensors
	int possible[size];                      //array which is used to mark the possible
						 //acceptors of the node
	//we check the nodes that can receive another edge
	for (int i=0;i<size;i++){
		if (oldState.canReceiveConnection(i)) possible[i]=0;
		else possible[i]=-1;
	}
	//For each possible source node, we specify which nodes cannot be targets: The ones that can reach it.
	//This is done with markImpossibleConnections, which is a DFS traversal of the subtree of src.
	//addNewsStates then traverses the whole possible array, and for every zero it meets, it generates a new state.
	for (src=0; src<nSensors; src++) {
		int target;
		markImpossibleConnections(src,connections,possible);
		addNewStates(src,connections,possible,possible_size,retVal);
	}		
        // Some code here
        // Generate successors based operators, for Simulated Annealing will need another way to generate the successors
        // new_state has to be a copy of state
        return retVal;
    }

    markImpossibleConnections(int father,Connection[] connections,int[] possible) {

	ArrayList<Integer> children = connections[father].getFromConnections();
	//iterator 
	int child;
	for (child=0; child<children.size(); child++) {
		if (possible[child]!= -1) {
		 	possible[child]=1; //set it to be impossible
		}
		markImpossibleConnections(child,connections,possible);
	}
    }
   
    addNewStates(int src, Connection[] connections,int[] possible,int size, ArrayList retVal, DistributionNetwork oldState) {
    
	int dst;	
	for (dst=0; dst<size; dst++) {
	    if (possible[dst]==0) {
		DistributionNetwork newState = new DistributionNetwork(oldState);
		newState.changeConnection(src,dst);
		retVal.add(newState);
	    }else if (possible[dst]==1) {	
		//we must set it back to zero, to be used by the next source
		possible[dst]=0;
	    }
	}
    }

	

	
		
		

}
