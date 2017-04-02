/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_practica_1_busqueda_local;

import java.util.ArrayList;
import java.util.List;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import java.util.Random;

/**
 *
 * @author Ale
 */
public class ProblemSuccessorFunctionSA implements SuccessorFunction {
    @Override
    public List getSuccessors(Object state) {
        List retVal = new ArrayList();
        DistributionNetwork net = (DistributionNetwork) state;
        DistributionNetwork newNet = new DistributionNetwork(net);
        int s1, s2;
        Random rng = new Random();
        
        // We only have two Operators, so we can use a nextBoolean to choose one of them
        if (rng.nextBoolean()) {
            s1 = rng.nextInt(net.getSensors().size());
            
            do {
                s2 = rng.nextInt(net.getNetwork().length);
            } while (!validChange(s1, s2, net));
            
            newNet.changeConnection(s1, s2);
        }
        else {
            do {
                s1 = rng.nextInt(net.getSensors().size());
                s2 = rng.nextInt(net.getSensors().size());
            } while (!validSwap(s1, s2, net));
            
            newNet.swapConnection(s1, s2);
        }
        
        retVal.add(new Successor("", newNet));
        return retVal; 
    }
    
    // These functions check that the elements involved will leave a valid solution
    private boolean validChange(int src, int tar, DistributionNetwork net) {
        // Check that target can receive new connections
        if (!net.canReceiveConnection(tar))
            return false;
        
        // Check that we won't connect to an element of src's subtree
        return notInSubtree(src, tar, net.getSensors().size(), net.getNetwork());
    }
    
    private boolean validSwap(int s1, int s2, DistributionNetwork net) {
        // We only need to make sure that s2 is not in the subtree of s1, and viceversa
        if (notInSubtree(s1, s2, net.getSensors().size(), net.getNetwork()))
            return notInSubtree(s2, s1, net.getSensors().size(), net.getNetwork());
        
        return false;
    }
    
    private boolean notInSubtree(int src, int tar, int sensors, Connection[] c) {
        for (int i = tar; i < sensors;) {
            
            // If we can reach src from tar, then tar is in src's subtree
            if (i == src)
                return false;
            
            i = c[i].getConnectionToIndex();
        }
        
        return true;
    }
}
