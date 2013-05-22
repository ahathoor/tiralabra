/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools.dijkstra;

import java.awt.Color;
import verkkolelu.util.HashMap;
import java.util.PriorityQueue;
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
    private PriorityQueue<Node> NodeHeap;

    protected enum State {

        SET_S, SET_T, INITIALIZED, FINISHED;
    }
    protected State state = State.SET_S;

    public DijkstraTool(Graph i) {
        graph = i;
        dist = new HashMap<>();
        previous = new HashMap<>();
        NodeHeap = new PriorityQueue<>(graph.nodeCount(), new NodeComparator(dist));
        dw = new Dijkstrawindow(this);
        listener = new DijkstraListener(this);
    }

    
    private void reset() {
        startNode = null;
        endNode = null;
        state = State.SET_S;
    }

    /**
     * Fills the signs of the nodes to show alphabets
     */
    public void autoSigns() {
        char signChar = 'A';
        for (Node n : graph.getNodes()) {
            n.setSign("" + signChar);
            signChar++;
        }
        startNode.setSign("START");
        endNode.setSign("END");
    }

    /**
     * Initializes the Dijkstra algorithm
     */
    private void init() {
        if (startNode == null || endNode == null) {
            System.out.println("Error: Start and End nodes must be set before initializing!");
        }
        autoSigns();
        for (Node n : graph.getNodes()) {
            dist.put(n, Integer.MAX_VALUE);
            previous.put(n, null);
            NodeHeap.add(n);
            n.setLabel("prev: - dist: INF");
        }

        dist.put(startNode, 0);
        NodeHeap.remove(startNode);
        NodeHeap.add(startNode);
        startNode.setLabel("prev: - dist: 0");

        System.out.println("Initialization: dist(Node n) = 0 for start node, INF for other nodes");
        System.out.println("All nodes added to heap with their respective dist values as keys");

        //Set state to initialized and create the stepping thread
        state = State.INITIALIZED;
        stepThread = new StepThread();
        stepThread.start();
    }
    StepThread stepThread;

    /**
     * Steps the algorithm forward
     */
    private void step() {
        if (state != State.INITIALIZED) {
            System.out.println("Error: Please initialize the algorithm before stepping.");
        }
        stepThread.resumeThread();
    }

    /**
     * A separate thread for the algorithm that pauses and waits for user to
     * resume it
     */
    private class StepThread extends Thread {

        private volatile boolean running = false;

        /**
         * Wakes up the thread
         */
        public void resumeThread() {
            running = true;
        }

        private void pause() {
            running = false;
            while (!running) {
            }
        }

        @Override
        public void run() {
            while (!NodeHeap.isEmpty()) {
                Node popped = NodeHeap.poll();
                System.out.println("======================================================================================");
                System.out.println("Popped node " + popped.getSign() + " from the heap. It has a distance value of " + dist.get(popped) + ". ");
                System.out.println("======================================================================================");
                if (dist.get(popped) == Integer.MAX_VALUE) {
                    System.out.println("Rest of the nodes unreachable.");
                    break;
                }
                for (Edge edge : graph.getEdges().get(popped)) {
                    pause();

                    Node neighbour = edge.getNode2();
                    System.out.println("Node " + popped.getSign() + " is connected to neighbour " + neighbour.getSign() + " with distance " + edge.getWeight() + ". ");

                    pause();

                    int alt = dist.get(popped) + edge.getWeight();

                    System.out.println("\tThe distance to node " + popped.getSign() + " plus the distance from " + popped.getSign() + " to " + neighbour.getSign()
                            + " = " + dist.get(popped) + " + " + edge.getWeight() + " = " + alt + ".");

                    pause();

                    if (alt < dist.get(neighbour)) {
                        System.out.println("\tIt is smaller than the current dist value for " + neighbour.getSign() + ", " + dist.get(neighbour) + ". ");

                        dist.put(neighbour, alt);
                        previous.put(neighbour, popped);
                        neighbour.setLabel("prev: " + previous.get(neighbour).getSign() + " dist: " + alt);
                        //TODO Decrease key implementation
                        NodeHeap.remove(neighbour);
                        NodeHeap.add(neighbour);

                        pause();

                        System.out.println("\t -> The new distance value for " + neighbour.getSign() + " was changed to " + alt + " and it's previous node was set to "
                                + popped.getSign() + ".");
                        System.out.println("\t    It was readded to the heap with value the new dist value. ");
                    } else {
                        System.out.println("\tIt is not smaller than the current dist " + dist.get(neighbour) + ". ");
                    }
                }
            }
            System.out.println("Algorithm finished!");
            state = DijkstraTool.State.FINISHED;
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
        startNode.setColor(Color.yellow);
    }

    public void setEndNode(Node endNode) {
        this.endNode = endNode;
        endNode.setColor(Color.red);
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
