/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools.dijkstra;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import verkkolelu.model.Edge;
import verkkolelu.model.Graph;
import verkkolelu.model.Node;
import verkkolelu.tools.Tool;
import verkkolelu.view.DrawPanel;

/**
 *
 * @author ahathoor
 */
public class DijkstraTool implements Tool {

    private Graph graph;
    private DijkstraListener listener;
    private Dijkstrawindow dw;
    /**
     * variables for the algorithm proper
     */
    private Node startNode;
    private Node endNode;
    private HashMap<Node, Integer> dist;
    private HashMap<Node, Node> previous;
    private PriorityQueue<Node> Q;

    protected enum State {
        SET_S, SET_T, INITIALIZED;
    }

    private void reset() {
        startNode = null;
        endNode = null;
        state = State.SET_S;
    }
    protected State state = State.SET_S;

    public DijkstraTool(Graph i) {
        graph = i;
        dist = new HashMap<>();
        previous = new HashMap<>();
        Q = new PriorityQueue<>(graph.nodeCount(), new NodeComparator(dist));
        dw = new Dijkstrawindow(this);
        listener = new DijkstraListener(this);
    }

    /**
     * Initializes the Dijkstra algorithm
     */
    private void init() {
        char c = 'A';
        for (Node n : graph.getNodes()) {
            dist.put(n, Integer.MAX_VALUE);
            previous.put(n, null);
            Q.add(n);
            n.setLabel("INF");
            n.setSign("" + c);
            c++;
        }

        dist.put(startNode, 0);
        Q.remove(startNode);
        Q.add(startNode);
        startNode.setLabel("0");
    }

    /**
     * Steps the algorithm forward
     */
    private void step() {
        if (Q.isEmpty()) {
            System.out.println("The algorithm has finished");
            return;
        }
        Node u = Q.poll();
        if (dist.get(u) == Integer.MAX_VALUE) {
            System.out.println("Rest of the nodes unreachable");
            return;
        }
        for (Edge edge : graph.getEdges().get(u)) {
            Node v = edge.getNode2();
            int alt = dist.get(u) + edge.getWeight();
            if (alt < dist.get(v)) {
                dist.put(v, alt);
                previous.put(v, u);
                v.setLabel("prev: " + previous.get(v).getSign() + " dist: " + alt);
                //TODO Decrease key implementation
                Q.remove(v);
                Q.add(v);
            }
        }
    }

    void command(DijkstraCommand command) {
        if (command == DijkstraCommand.SELECT_START) {
            state = State.SET_S;
        }
        if (command == DijkstraCommand.SELECT_END) {
            state = State.SET_T;
        }
        if (command == DijkstraCommand.INIT) {
            init();
        }
        if (command == DijkstraCommand.STEP) {
            step();
        }
        if (command == DijkstraCommand.RESET) {
            reset();
        }
    }

    protected Node getStartNode() {
        return startNode;
    }

    protected Node getEndNode() {
        return endNode;
    }

    protected Graph getGraph() {
        return graph;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public void setEndNode(Node endNode) {
        this.endNode = endNode;
    }

    @Override
    public void select(DrawPanel p) {
        reset();
        p.addMouseListener(listener);
        dw.open();
    }

    @Override
    public void deselect(DrawPanel p) {
        p.removeMouseListener(listener);
        dw.close();
    }

    @Override
    public String getName() {
        return "Dijkstra";
    }
}
