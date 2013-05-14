/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ahathoor
 */
public class Graph {

    ArrayList<Node> nodes;
    HashMap<Node, ArrayList<Edge>> edges;

    public Graph() {
        nodes = new ArrayList<>();
        edges = new HashMap<>();
    }

    public int nodeCount() {
        return nodes.size();
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public HashMap<Node, ArrayList<Edge>> getEdges() {
        return edges;
    }

    public Node nodeNearPoint(Point p) {
        for (Node n : nodes) {
            if (n.getPoint().distance(p) <= 10) {
                return n;
            }
        }
        return null;
    }

    public Node addNode(Point point) {
        notifyListeners();
        Node newNode = new Node(point, this);
        nodes.add(newNode);
        edges.put(newNode, new ArrayList<Edge>());
        return newNode;
    }

    public void linkNodes(Node n1, Node n2, int weight) {
        notifyListeners();
        Edge edgeFrom1to2 = new Edge(n2, weight);
        edges.get(n1).add(edgeFrom1to2);
        Edge edgeFrom2to1 = new Edge(n1, weight);
        edges.get(n2).add(edgeFrom2to1);
    }

    public void deleteNode(Node n) {
        notifyListeners();
        ArrayList<Edge> edgesFromN = edges.get(n);
        for (Edge e : edgesFromN) {
            ArrayList<Edge> neighboursEdges = edges.get(e.getNode2());
            for (int i = 0; i < neighboursEdges.size(); i++) {
                if (neighboursEdges.get(i).getNode2() == n) {
                    neighboursEdges.remove(neighboursEdges.get(i));
                }
            }
        }
        edges.remove(n);
        nodes.remove(n);
    }
    
    private ArrayList<GraphChangeListener> graphListeners = new ArrayList();

    public void notifyListeners() {
        for (GraphChangeListener graphChangeListener : graphListeners) {
            graphChangeListener.graphChanged();
        }
    }

    public void addListener(GraphChangeListener gl) {
        graphListeners.add(gl);
    }

    public void removeListener(GraphChangeListener gl) {
        graphListeners.remove(gl);
    }
}
