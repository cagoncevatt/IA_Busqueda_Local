/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_practica_1_busqueda_local;

import java.util.ArrayList;
/**
 *
 * @author Ale
 */
public class Connection {
    private int mToIndex;
    private final ArrayList<Integer> mFrom;
    
    public Connection() {
        mToIndex = -1;
        mFrom = new ArrayList();
    }
    
    public Connection(Connection c) {
    	
    	//kosmas fixed copy constructor
        mToIndex = c.getConnectionToIndex();
        //mFrom = c.getFromConnections(); 
        //the above is a very bad idea!!!
        mFrom = new ArrayList();
        ArrayList<Integer> fromConns = c.getFromConnections();
        for (int i=0;i<fromConns.size();i++){
        	mFrom.add(fromConns.get(i));
        }
    }
    
    public void setConnectionTo(int index) {
        mToIndex = index;
    }
    
    public int getConnectionToIndex() {
        return mToIndex;
    }
    
    public int getConnections() {
        return mFrom.size();
    }
    
    public ArrayList<Integer> getFromConnections() {
        return mFrom;
    }
    
    public void addConnectionFrom(Integer from) {
        mFrom.add(from);
    }
    
    public void removeConnectionFrom(Integer from) {
        mFrom.remove(from);
    }
    
    public Integer getConnectionFromIndex(int index) {
        return mFrom.get(index);
    }
}
