/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_practica_1_busqueda_local;

import java.util.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;

/**
 *
 * @author Ale
 * Based on: http://www1.cs.columbia.edu/~bert/courses/3137/hw3_files/GraphDraw.java
 */

public class GraphCanvas extends JPanel {
    
    public enum NodeType {
        SENSOR, CENTER
    }
    
    private final int ARR_SIZE = 4;
    private final ArrayList<Node> nodes;
    private final ArrayList<Edge> edges;
    
    //Constructor
    public GraphCanvas() {
	nodes = new ArrayList<>();
	edges = new ArrayList<>();
    }
    
    public void clearGraph() {
        nodes.clear();
        edges.clear();
    }
    
    public void addNode(String name, int x, int y, NodeType t) { 
	//add a node at pixel (x,y)
	nodes.add(new Node(name, (x * 5) + 5, (y * 5) + 5, t));
    }
    public void addEdge(int i, int j) {
	//add an edge between nodes i and j
	edges.add(new Edge(i, j));
    }
    
    @Override
    public void paint(Graphics g) { // draw the nodes and edges
	FontMetrics f = g.getFontMetrics();
	int nodeHeight = 4;

	g.setColor(Color.black);
        
        for (Edge e : edges) {
            drawArrow(g, nodes.get(e.getSrc()).getX(), nodes.get(e.getSrc()).getY(),
                    nodes.get(e.getTar()).getX(), nodes.get(e.getTar()).getY());
        }

	for (Node n : nodes) {
	    int nodeWidth = 4;
            
            if (n.getType() == NodeType.CENTER)
                g.setColor(Color.black);
            else
                g.setColor(Color.red);
            
	    g.fillOval(n.getX()-nodeWidth/2, n.getY()-nodeHeight/2, 
		       nodeWidth, nodeHeight);
	    
	    g.drawString(n.getName(), n.getX() + 3,
			 n.getY() + 6);
	}
    }
    
    private void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
        // From: http://stackoverflow.com/questions/4112701/drawing-a-line-with-arrow-in-java
        Graphics2D g = (Graphics2D) g1.create();

        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        // Draw horizontal arrow starting in (0, 0)
        g.drawLine(0, 0, len, 0);
        g.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
                      new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
    }
    
    private class Node {
        private final int x, y;
	private final String name;
        private final NodeType type;
	
	public Node(String myName, int myX, int myY, NodeType t) {
	    x = myX;
	    y = myY;
	    name = myName;
            type = t;
	}
        
        public int getX() {
            return x;
        }
        
        public int getY() {
            return y;
        }
        
        public String getName() {
            return name;
        }
        
        public NodeType getType() {
            return type;
        }
    }
    
    private class Edge {
	private final int i,j;
	
	public Edge(int ii, int jj) {
	    i = ii;
	    j = jj;	    
	}
        
        public int getSrc() {
            return i;
        }
        
        public int getTar() {
            return j;
        }
    }
}
