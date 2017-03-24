/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_practica_1_busqueda_local;

import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;
/**
 *
 * @author Ale
 */
public class IA_Practica_1_Busqueda_Local {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        DistributionNetwork network = new DistributionNetwork(100, 1234, 4, 1234);
        
        /*
        Problem p = new Problem(network,
                new ProblemSuccessorFunction(),
                new ProblemGoalTest(),
                new ProblemHeuristicFunction());
        
        Search alg = new HillClimbingSearch();
        SerachAgent agent = new SearchAgent(problem, alg);
        */
    }
    
}
