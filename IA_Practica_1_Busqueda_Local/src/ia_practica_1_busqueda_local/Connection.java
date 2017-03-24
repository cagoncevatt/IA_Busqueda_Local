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
    private ArrayList<Integer> mFrom;
    
    public Connection() {
        mToIndex = -1;
    }
    
    public Connection(Connection c) {
        mToIndex = c.getConnectionToIndex();
        mFrom = c.getFromConnections();
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
}
