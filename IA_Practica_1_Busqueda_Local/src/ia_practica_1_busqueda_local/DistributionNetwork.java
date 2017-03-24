/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_practica_1_busqueda_local;

import IA.Red.CentrosDatos;
import IA.Red.Sensores;
import IA.Red.Centro;
import IA.Red.Sensor;
import java.util.ArrayList;

/**
 *
 * @author Ale
 */
public class DistributionNetwork {
    
    /* Variables for State Data Structure */
    private static CentrosDatos mCenters;
    private static Sensores mSensors;
    
    private final Connection[] mNetwork;
    
    /* Constructor */
    public DistributionNetwork(int sensors, int sensorSeed, int centers, int centerSeed) {
        mCenters = new CentrosDatos(centers, centerSeed);
        mSensors = new Sensores(sensors, sensorSeed);
        
        mNetwork = new Connection[centers + sensors];
        
        for (int i = 0; i < mNetwork.length; ++i)
            mNetwork[i] = new Connection();
        
        // Generate a new initial solution
    }
    
    public DistributionNetwork(DistributionNetwork net) {
        mNetwork = net.getNetwork().clone();
    }
    
    /* Operator */
    // ChangeEdge / ChangeConnection
    public void changeConnection(Integer from, Integer to) {
       Connection cFrom = mNetwork[from];
       
       mNetwork[cFrom.getConnectionToIndex()].removeConnectionFrom(from);
       mNetwork[to].addConnectionFrom(from);
       cFrom.setConnectionTo(to);
    }
    
    // Maybe a SwapEdge with other sensor

    /* Heuristic function */
    public double heuristic() {
        // Calc data stored and data lost in this solution, aswell as total cost
        return 0;
    }

    /* Goal test */
    public boolean isGoal() {
        // Not needed for HC and SA, so always returns false.
        return false;
    }
     
    public CentrosDatos getDataCenters() {
        return mCenters;
    }
     
    public Sensores getSensors() {
        return mSensors;
    }
    
    public Connection[] getNetwork() {
        return mNetwork;
    }
    
    public boolean canReceiveConnection(int source, int target) {
        Connection c = mNetwork[target];
        return (target < mSensors.size()) ? (c.getConnections() < 3) : (c.getConnections() < 25);
    }
}
