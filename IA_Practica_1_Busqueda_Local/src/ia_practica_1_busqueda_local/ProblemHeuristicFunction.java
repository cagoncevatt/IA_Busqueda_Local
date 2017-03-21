/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_practica_1_busqueda_local;

import aima.search.framework.HeuristicFunction;

/**
 *
 * @author Ale
 */
public class ProblemHeuristicFunction implements HeuristicFunction {

    public double getHeuristicValue(Object n){
        return ((DistributionNetwork) n).heuristic();
    }
}
