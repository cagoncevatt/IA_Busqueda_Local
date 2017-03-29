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
import java.util.Random;

/**
 *
 * @author Ale
 */
public class DistributionNetwork {



	/* Variables for State Data Structure */
	private static CentrosDatos mCenters;
	private static Sensores mSensors;
	private double mTotalData;
	private double mTotalCost;

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

	// SwapEdge / SwapConnection
        // s1 and s2 must be sensors index (0..mSensors.size() - 1)
        public void swapConnection(int s1, int s2) {
            Connection c1 = mNetwork[s1];
            Connection c2 = mNetwork[s2];
            
            int c1To = c1.getConnectionToIndex();
            int c2To = c2.getConnectionToIndex();
            
            mNetwork[c1To].removeConnectionFrom(s1);
            mNetwork[c1To].addConnectionFrom(s2);
            mNetwork[c2To].removeConnectionFrom(s2);
            mNetwork[c2To].addConnectionFrom(s1);
            
            c1.setConnectionTo(c2To);
            c2.setConnectionTo(c1To);
        }

	/* Heuristic function */
	public double heuristic() {

		double retVal=0.0;
		int c,conn,data=0;
		//These two are the only ingredients of our heuristic. We will calculate them now.
		//However, note that it is not necessary to compute the value of the heuristic of every successor;
		//It is much faster to just compute the difference from the old state.
		mTotalCost= 0;
		mTotalData=0;

		for (c=0; c<mCenters.size(); c++) { //for each data centera...
			int dataOfCenter=0;  //..we will compute the data it receives...
			ArrayList<Integer> fromConnections = mNetwork[mSensors.size()+c].getFromConnections(); 
			for (conn=0; conn<fromConnections.size(); conn++) { //for every sensor attached to it...
				int child = fromConnections.get(conn);    //...count how much data is received by it.
				int receivedData = calculateDataReceived(c,child);
				dataOfCenter+=receivedData;

			} 
			dataOfCenter = (dataOfCenter > 150) ? 150 : dataOfCenter;
			mTotalData+=dataOfCenter;
		}
		//Now we can calculate the heuristic.
		retVal = mTotalCost/mTotalData; //TODO: Think of a better Heuristic
		return retVal;
	}

	private int calculateDataReceived(int src, int dst) {

		///here, we return the amount of data that is transmitted to the src.
		//Also we calculate the cost of the transmission, which is dist^2*data.


		int retVal; //the amount of data that will be delivered to src.

		int data=( (int) mSensors.get(dst).getCapacidad() ); //the data that is read by the sensor itself.
		//now we compute the data received by this sensor:

		ArrayList<Integer> fromConnections = mNetwork[dst].getFromConnections(); //get connections
		int i;
		int child = fromConnections.get(0);
		for (i=0; i<fromConnections.size(); i++, child=fromConnections.get(i)) {
			int datum= calculateDataReceived(dst,child); //datum is singular of data :P (like forum-fora)
			data+= datum;
		}
		//now we have to cut the data that could not be stored in the dst
		int maxData = 3*((int) mSensors.get(dst).getCapacidad());
		retVal = (data > maxData ) ? maxData : data;
		//now we will pay for the data of this transmission.
		double distance = distance(src,dst); 
		mTotalCost+= distance*distance*retVal;

		return retVal;
	}	

	private double distance(int node1,int node2) {
		double x1,y1,x2,y2;
		if (node1 < mSensors.size() ) {
			x1 = (double) mSensors.get(node1).getCoordX();
			y1 = (double) mSensors.get(node1).getCoordY();
		} else {
			x1 = (double) mCenters.get(node1-mSensors.size()).getCoordX();
			y1 = (double) mCenters.get(node1-mSensors.size()).getCoordY();
		}

		if (node2 < mSensors.size() ) {
			x2 = (double) mSensors.get(node2).getCoordX();
			y2 = (double) mSensors.get(node2).getCoordY();
		} else {
			x2 = (double) mCenters.get(node2-mSensors.size()).getCoordX();
			y2 = (double) mCenters.get(node2-mSensors.size()).getCoordY();
		}

		return Math.sqrt((((x1-x2)*(x1-x2)) + ((y1-y2)*(y1-y2))));

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

	private void generateInitialSolution() {
		// First proposition for an Initial Solution: Fixed Assignment
		// Sensors, in the order they are generated, will be assigned to the centers, also in their generated order
		// Once all centers can't receive anymore connections, sensors will start to be assigned to other sensors in the same default order

		int s = 0;

		// Assign sensors to centers
		for (int c = mSensors.size(); c < mNetwork.length && s < mSensors.size(); ++c) {
			// Connect 25 sensors to center (c - mSensors.size)
			for (int l = 0; l < 25 && s < mSensors.size(); ++l, ++s) {
				mNetwork[s].setConnectionTo(c);
				mNetwork[c].addConnectionFrom(s);
			}
		}

		// Assign sensors to sensors
		for (int tarS = 0; s < mSensors.size(); ++tarS) {
			// Connect 3 sensors to sensor tarS
			for (int l = 0; l < 3 && s < mSensors.size(); ++l, ++s) {
				mNetwork[s].setConnectionTo(tarS);
				mNetwork[tarS].addConnectionFrom(s);
			}
		}
	}

	private void generateInitialSolutionSec() {
		// This method will assign sensors to other random sensors or centers
		Random rng = new Random();

		// Pick sensors in order
		// Pick a random center
		// If the center doesn't have space, then connect the sensor to another sensor connected to the center
		// Else If the center has sensors connected and space available 
		//     -> 50/50 to connect to the center or pick a random sensor
		//   ^ Applies equally for the sensor chosen if is the case
		// Else connect the sensor to the center

		for (int s = 0; s < mSensors.size(); ++s)
			stablishRandomConnection(s, rng.nextInt(mCenters.size()) + mSensors.size(), rng);
	}

	private void stablishRandomConnection(int source, int target, Random rng) {
		if (!canReceiveConnection(target)) {
			// If the target can't receive more connections we know there are "children"
			// so we try to add source to one of them
			int next = rng.nextInt(mNetwork[target].getConnections());
			stablishRandomConnection(source, mNetwork[target].getConnectionFromIndex(next), rng);
		}
		else if (mNetwork[target].getConnections() > 0) {
			// If the target can receive connections, but also has "children"
			// then we generate a random boolean to know if we add source to target or one of its children
			if (rng.nextBoolean()) {
				int next = rng.nextInt(mNetwork[target].getConnections());
				stablishRandomConnection(source, mNetwork[target].getConnectionFromIndex(next), rng);
			}
			else {
				mNetwork[source].setConnectionTo(target);
				mNetwork[target].addConnectionFrom(source);
			}
		}
		else {
			// Finally the target can receive connections and doesn't have children, we add source to it
			mNetwork[source].setConnectionTo(target);
			mNetwork[target].addConnectionFrom(source);
		}
	}
}
