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
	private double mTotalData=0;
	private double mTotalCost=0;

	private final Connection[] mNetwork;

	/* Constructor */
	public DistributionNetwork(int sensors, int sensorSeed, int centers, int centerSeed) {
		mCenters = new CentrosDatos(centers, centerSeed);
		mSensors = new Sensores(sensors, sensorSeed);

		mNetwork = new Connection[centers + sensors];


		for (int i = 0; i < mNetwork.length; ++i){
			if (i < sensors) {
				System.out.println("Coords of sensor " + i + " (" + mSensors.get(i).getCoordX() + "," + mSensors.get(i).getCoordY());
			} else {
				System.out.println("Coords of center " + (i-sensors) + " (" + mCenters.get(i-sensors).getCoordX() + "," + mCenters.get(i-sensors).getCoordY());
			}
			mNetwork[i] = new Connection();
		}

		generateInitialSolutionSec();

		System.out.println(distanceSquared(0,0));
		System.out.println(distanceSquared(0,4));
		System.out.println(distanceSquared(1,5));
		System.out.println(distanceSquared(2,6));
		System.out.println(distanceSquared(3,7));
		//System.out.println(mSensors.size());

		//System.out.println(mCenters.size());




		//for (int i = 0; i < mNetwork.length; ++i)
		//	System.out.println(mNetwork[i].getConnections());

		ProblemSuccessorFunction  psf = new ProblemSuccessorFunction();
		psf.getSuccessors((Object)this);

		System.out.println(heuristic());
		// Generate a new initial solution
	}


	public DistributionNetwork(DistributionNetwork net) {

		//kosmas: fixed copy constructor.
		Connection[] oldNetwork = net.getNetwork(); //.clone();
		mNetwork = new Connection[net.getNetwork().length];
		for (int i = 0; i < mNetwork.length; ++i) {
			mNetwork[i] = new Connection();
			mNetwork[i].setConnectionTo(oldNetwork[i].getConnectionToIndex());
			ArrayList<Integer> fromConnections = oldNetwork[i].getFromConnections();
			int j;
			for (j=0;j<fromConnections.size();j++) {
				mNetwork[i].addConnectionFrom(fromConnections.get(j));
			}
		}


	}

	/* Operator */
	// ChangeEdge / ChangeConnection
	public void changeConnection(Integer from, Integer to) {
		Connection cFrom = mNetwork[from];

		mNetwork[cFrom.getConnectionToIndex()].removeConnectionFrom(from);
		mNetwork[to].addConnectionFrom(from);
		cFrom.setConnectionTo(to);
	}

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
		int c,conn;
		//These two are the only ingredients of our heuristic. We will calculate them now.
		//However, note that it is not necessary to compute the value of the heuristic of every successor;
		//It is much faster to just compute the difference from the old state.
		mTotalCost= 0;
		mTotalData=0;

		for (c=0; c<mCenters.size(); c++) { //for each data center...
			int dataOfCenter=0;                //..we will compute the data it receives...
			ArrayList<Integer> fromConnections = mNetwork[mSensors.size()+c].getFromConnections(); 

			for (conn=0; conn<fromConnections.size(); conn++) { //for every sensor attached to it...

				int child = fromConnections.get(conn);    //...count how much data is received by it.
				double receivedData = calculateDataReceived(child,c+mSensors.size());
				dataOfCenter+=receivedData;


			} 
			dataOfCenter = (dataOfCenter > 150) ? 150 : dataOfCenter;
			mTotalData= mTotalData+dataOfCenter;
		}
		
		//Calculate the cost now:
		
		
		

		//Now we can calculate the heuristic.
		retVal = mTotalCost/mTotalData;//mTotalData; //TODO: Think of a better Heuristic
		//System.out.println(retVal);
		return retVal;
	}


	private double calculateDataReceived(int src, int dst) {

		double distancesq = distanceSquared(src,dst); 
		
		///here, we return the amount of data that is transmitted to the dst.
		//Also we calculate the cost of the transmission, which is dist^2*data.


		double retVal; //the amount of data that will be delivered to src.

		double data=(mSensors.get(src).getCapacidad());
		//the data that is read by the sensor itself.
		//now we compute the data received by this sensor:

		ArrayList<Integer> fromConnections = mNetwork[src].getFromConnections(); //get connections
		int i;

		for (i=0; i<fromConnections.size(); i++) {
			int child=fromConnections.get(i);
			double datum= calculateDataReceived(child,src); //datum is singular of data :P (like forum-fora)
			data+= datum;
		}
		//now we have to cut the data that could not be stored in the src

		double maxData = 3*( mSensors.get(src).getCapacidad());
		retVal = (data > maxData ) ? maxData : data;
		//now we will pay for the data of this transmission.
		
		mTotalCost+= distancesq*retVal;

		return retVal;
	}	

	private double distanceSquared(int node1,int node2) {
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

		return ((x1-x2)*(x1-x2)) + ((y1-y2)*(y1-y2));

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


	public double getCost() {
		return mTotalCost;
	}
	public double getData() {
		return mTotalData; 
	}
}

