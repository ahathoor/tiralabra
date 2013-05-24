/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.model;

import java.awt.Color;
import java.awt.Point;
import verkkolelu.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 *
 * @author ahathoor
 */
public class Graph {

    ArrayList<Node> nodes;
    HashMap<Node, ArrayList<Edge>> edges;

    public Graph() {
        empty();
    }

    /**
     * Empties the graph
     */
    public void empty() {
        nodes = new ArrayList<>();
        edges = new HashMap<>();
        notifyListeners();
    }
    /**
     * Generates a string from the graph that can be used to load the graph
     *
     * @return
     */
    public String saveToString() {
        /**
         * Save the nodes
         */
        String nodesave = "";
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            nodesave += i + "<nodeInternal>" 
                    + node.getPoint().x + "<nodeInternal>" 
                    + node.getPoint().y + "<nodeInternal>" 
                    + node.getSign() + "<nodeInternal>" 
                    + node.getColor().getRGB() + "<node>";
        }
        /**
         * Save the edges
         */
        String edgeSave = "";
        Iterator<Entry<Node, ArrayList<Edge>>> edgeI = edges.entrySet().iterator();
        while (edgeI.hasNext()) {
            Entry<Node, ArrayList<Edge>> edgeEntry = edgeI.next();
            Node node1 = edgeEntry.getKey();
            for (Edge edge : edgeEntry.getValue()) {
                Node node2 = edge.getNode2();
                int weight = edge.getWeight();
                edgeSave += nodes.indexOf(node1) + "<edgeInternal>"
                        + nodes.indexOf(node2) + "<edgeInternal>"
                        + weight + "<edge>";
            }
        }
        return nodesave + "<graphInternal>" + edgeSave;
    }

    /**
     * Loads the graph from a saved string
     *
     * @param save
     */
    public void loadFromString(String save) {
        empty();
        if (save.split("<graphInternal>").length == 0) {
            //Graph has no nodes
            return;
        }
        String nodesave = save.split("<graphInternal>")[0];
        String[] nodeStrings = nodesave.split("<node>");
        for (String nodeString : nodeStrings) {
            int index = Integer.parseInt(nodeString.split("<nodeInternal>")[0]);
            int x = Integer.parseInt(nodeString.split("<nodeInternal>")[1]);
            int y = Integer.parseInt(nodeString.split("<nodeInternal>")[2]);
            String sign = nodeString.split("<nodeInternal>")[3];
            int rgb = Integer.parseInt(nodeString.split("<nodeInternal>")[4]);
            Node added = addNode(new Point(x, y));
            added.setSign(sign);
            added.setColor(new Color(rgb));
        }

        if (save.split("<graphInternal>").length == 1) {
            //Graph has no edges
            return;
        }
        String edgeSave = save.split("<graphInternal>")[1];
        String[] edgeStrings = edgeSave.split("<edge>");
        for (String edgeString : edgeStrings) {
            int index1 = Integer.parseInt(edgeString.split("<edgeInternal>")[0]);
            int index2 = Integer.parseInt(edgeString.split("<edgeInternal>")[1]);
            int weight = Integer.parseInt(edgeString.split("<edgeInternal>")[2]);
            this.linkNodes(nodes.get(index1), nodes.get(index2), weight);
        }
    }

    public int nodeCount() {
        return nodes.size();
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }
    
    /**
     * Returns a list of edges that go out from the given node.
     * @param node
     * @return 
     */
    public ArrayList<Edge> getEdgesFrom(Node node) {
        return edges.get(node);
    }

    /**
     * Returns a node nearest to the given point.
     *
     * @param p
     * @return
     */
    public Node nodeNearPoint(Point p) {
        for (Node n : nodes) {
            if (n.getPoint().distance(p) <= 10) {
                return n;
            }
        }
        return null;
    }

    /**
     * Adds a new Node at the given point
     *
     * @param point
     * @return the created node
     */
    public Node addNode(Point point) {
        notifyListeners();
        Node newNode = new Node(point, this);
        nodes.add(newNode);
        edges.put(newNode, new ArrayList<Edge>());
        return newNode;
    }

    /**
     * Makes a two-way edge between given nodes, with the given weight
     *
     * @param n1
     * @param n2
     * @param weight
     */
    public void crossLinkNodes(Node n1, Node n2, int weight) {
        linkNodes(n1, n2, weight);
        linkNodes(n2, n1, weight);
    }
    
    /**
     * Links node n1 to node n2 with given weight
     * @param n1
     * @param n2
     * @param weight 
     */
    public void linkNodes(Node n1, Node n2, int weight) {
        notifyListeners();
        Edge edgeTo2 = new Edge(n2, weight);
        edges.get(n1).add(edgeTo2);
    }

    /**
     * Deletes a given node from the graph
     *
     * @param n
     */
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

    /**
     * Notifies the registered GraphChangeListeners of changes.
     */
    protected void notifyListeners() {
        for (GraphChangeListener graphChangeListener : graphListeners) {
            graphChangeListener.graphChanged();
        }
    }

    /**
     * Adds a GraphChangeListener to this graph
     * @param gl 
     */
    public void addListener(GraphChangeListener gl) {
        graphListeners.add(gl);
    }
    /**
     * Removes a GraphChangeListener from this graph
     * @param gl 
     */
    public void removeListener(GraphChangeListener gl) {
        graphListeners.remove(gl);
    }
}
