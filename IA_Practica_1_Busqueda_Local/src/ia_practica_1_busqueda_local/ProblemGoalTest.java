/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_practica_1_busqueda_local;

import aima.search.framework.GoalTest;

/**
 *
 * @author Ale
 */
public class ProblemGoalTest implements GoalTest {
    public boolean isGoalState(Object state){
        return((DistributionNetwork) state).isGoal();
    }
}
