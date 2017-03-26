/*

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
   

    class costAndData {
	double cost;
	int data;
	public double getCost() {
		return cost;
	}
	public int getData() {
		return data;
	}
	public void setCost(double c) {
		cost=c;
	}
	public void setData(int d) {
		data=d;
	}
    }
 
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

	double retVal=0.0;
	//first we calculate the data.
	int i;
	int data=0;
	cost=0;
	for (i=0; i<mCenters.size(); i++) {
	    int dataOfCenter=0;
	    ArrayList<Integer> fromConnections = mNetwork[mSensors.size()+node].getConnections();
	    for (i=0; i<fromConnections.size(); i++) {
       	        int child = fromConnections.get(i);
		calculateDataReceivedBy(i,receivedData,cost);
		dataOfCenter+=receivedData;
	  		
	    } 
	    data += (dataOfCenter > 150) ? 150 : dataOfCenter;
	}
        // Calc data stored and data lost in this solution, aswell as total cost
        return retVal;
    }

    private costAndData calculateDataReceivedBy(int node) {
	
	double retVal;
   	int data=( (int) mSensors.at(node).getCapacidad() );	
	ArrayList<Integer> fromConnections = mNetwork[node].getConnections();
	for (i=0; i<fromConnections.size(); i++) {
       	    int child = fromConnections.at(i);
	    datum= calculateDataReceivedBy(child); //datum is singular of data :P (como forum-fora)
	    distance = distance(node,child);
	    data+= datum;
	    cost+= distance*distance*datum;
	}
	
	//now we have to determine the type of data center, and cut the data that cannot be stored :(
	int maxData = 3*((int) mSensors.at(node).getCapacidad());
	retVal = (data > maxData ) ? maxData : data;			
	return retVal;
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
    //Kosmas:removed source parameter, it is not used here.    
    public boolean canReceiveConnection(int target) {
        Connection c = mNetwork[target];
        return (target < mSensors.size()) ? (c.getConnections() < 3) : (c.getConnections() < 25);
    }
}
