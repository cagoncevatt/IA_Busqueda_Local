package ia_practica_1_busqueda_local;

import java.util.ArrayList;
import java.util.List;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

public class ProblemSuccessorFunctionSwap implements SuccessorFunction{

		//what does swap a node mean?
	    //it means that we choose two nodes, and we disconnect them. Then we connect one t
		//ex-father of the other. Is this always a valid move? Or does it create cycles?
	
		//For each node n: which are the nodes that can be swapped with it?
		//1.they can't be datacenters.
	    //2.they can't be its children
		//3.they can't be in its own subtree.
		//4.It can't be its father.
		//Can it be anyone else?
		//What if it someone whose subtree includes n?  This again causes a problem, as the higher node
		//gets connected to one of his ancestor (or itself)
	    //So a good general term is that one cannot be in the subtree of another.
		//what else.. any special cases?? 
		//let's see... leaves
		//leaves can be connected anywhere, but not anything can be connected to them, as there may 
	 	//be cycles.
	    //For a start we implement our generator with only the restriction mentioned above.
		public List getSuccessors(Object state){
			
			System.out.println("DEBUG: Generating Successors");
			
			ArrayList retVal = new ArrayList(); 
			DistributionNetwork oldState = new DistributionNetwork((DistributionNetwork) state);
			Connection[] connections= oldState.getNetwork();
			int src;
			//int size=connections.length;                         //total number of components
			int nSensors = oldState.getSensors().size();         //number of sensors
			int[] possible = new int[nSensors];                      //array which is used to mark 
															     //the possible nodes 
																// the current node this can swap edges with
			for (int i=0;i<nSensors;i++){
				possible[i]=0; //initialize with every node being a possible swapper.
			
			}
		 	//main working loop, O(n^2)	
			for (int s=0;s<nSensors;s++) {
				
				possible[s]=1; //node s does not swap edge with itself 
				markSubtree(s,connections,possible);//O(n). This method marks the whole tree as impossible.
				markPathToRoot(s,connections,possible);//O(n) The path to root is also impossible
				addNewStates(s,connections,possible,retVal,oldState);//This one creates the successors
			}
				 	
			
			return retVal; 
			
		}
		
		private void markSubtree(int node,Connection[] connections,int[] possible) {
				
			possible[node]=1;
			ArrayList<Integer> fromConnections = connections[node].getFromConnections();
			for (int i=0;i<fromConnections.size();i++) {
				int child=fromConnections.get(i);
				markSubtree(child,connections,possible);
			}		
		}
		
		private void markPathToRoot(int node,Connection[] connections,int[] possible) {
			while (node < possible.length) {
				possible[node]=1;
				node = connections[node].getConnectionToIndex();
			}
		}

		
		private void addNewStates(int node1,Connection[] connections,int[] possible,ArrayList retVal, DistributionNetwork oldState){
			
			for (int node2=0; node2<possible.length; node2++) {
				
				if (possible[node2]==1) {
					possible[node2]=0;
					continue; //if we can't swap with this guy, let's move to another.

				}
				DistributionNetwork newState = new DistributionNetwork(oldState);
				newState.swapConnection(node1, node2); //TODO: double check if swapConnection works well
				newState.heuristic();
				String succStr = "Swapping out edges of nodes: " + node1 + "," + node2 + 
				"gives us a state with " + newState.getData() + " data and " + newState.getCost() + " cost.";
				retVal.add( new Successor(succStr,newState));
			}
		}

}
