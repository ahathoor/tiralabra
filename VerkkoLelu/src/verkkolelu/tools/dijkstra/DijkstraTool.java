/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools.dijkstra;

import java.awt.Color;
import verkkolelu.util.ArrayList;
import verkkolelu.util.HashMap;
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

    protected enum State {

        SET_S, INITIALIZED, FINISHED;
    }
    private Graph graph;
    private DijkstraListener listener;
    private Dijkstrawindow dw;
    private Node startNode;
    StepThread stepThread;
    protected State state;
    private String graphSave;
    private ArrayList<String> signSave;

    public DijkstraTool(Graph i) {
        graph = i;
        dw = new Dijkstrawindow(this);
        listener = new DijkstraListener(this);
        signSave = new ArrayList<>();
    }

    /**
     * Resets the tool.
     */
    private void reset() {
        loadGraph();
        startNode = null;
        state = State.SET_S;
    }

    /**
     * Initializes the Dijkstra algorithm.
     */
    private void init() {
        if (startNode == null) {
            System.out.println("Error: Start node must be set before initializing!");
            return;
        }
        //Set state to initialized and create the stepping thread
        state = State.INITIALIZED;
        stepThread = new StepThread(graph, startNode);
        stepThread.start();
    }

    /**
     * Steps the algorithm forward.
     */
    private void step() {
        if (state != State.INITIALIZED) {
            System.out.println("Error: Please initialize the algorithm before stepping.");
            return;
        }
        if (stepThread.isDone()) {
            System.out.println("Algorithm finished!");
            state = State.FINISHED;
            return;
        }
        stepThread.resumeThread();
    }

    /**
     * Processes a input DijkstraCommand and executes them.
     *
     * @param command input command
     */
    void command(DijkstraCommand command) {
        if (command == DijkstraCommand.SELECT_START) {
            if (state == State.INITIALIZED) {
                System.out.println("Error: algorithm running, can't set start node");
                return;
            }
            state = State.SET_S;
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

    protected Graph getGraph() {
        return graph;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
        loadSigns();
        if (startNode != null) {
            startNode.setSign("Start");
        }
    }

    private void saveSigns() {
        Node[] nodes = graph.getNodes().toArray(new Node[graph.getNodes().size()]);
        for (int i = 0; i < nodes.length; i++) {
            signSave.add(nodes[i].getSign());
        }
    }

    private void loadSigns() {
        Node[] nodes = graph.getNodes().toArray(new Node[graph.getNodes().size()]);
        for (int i = 0; i < nodes.length; i++) {
            nodes[i].setSign(signSave.get(i));
        }
    }

    private void saveGraph() {
        graphSave = graph.saveToString();
    }

    private void loadGraph() {
        graph.loadFromString(graphSave);
    }

    @Override
    public void select(DrawPanel p) {
        saveGraph();
        saveSigns();
        reset();
        p.addMouseListener(listener);
        dw.open();
    }

    @Override
    public void deselect(DrawPanel p) {
        loadSigns();
        p.removeMouseListener(listener);
        dw.close();
        loadGraph();
    }

    @Override
    public String getName() {
        return "Dijkstra";
    }
}
