/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools.dijkstra;

import verkkolelu.util.ArrayList;
import verkkolelu.model.Graph;
import verkkolelu.model.Node;
import verkkolelu.tools.Tool;
import verkkolelu.view.MainFrame;

/**
 * The dijkstratool can perform the dijkstra's algorithm on the graph given
 * in the constructor. The method to set the start node must be called first. 
 * After that the method Initialize must be called, and after that the step -method
 * for as many times as it takes to finish the algorithm.
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
     * Initializes the Dijkstra algorithm. Creates a new stepthread that is then run step by step with step();
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

    /**
     * Sets the start node for the Dijkstra's algorithm.
     * @param startNode 
     */
    public void setStartNode(Node startNode) {
        this.startNode = startNode;
        loadSigns();
        if (startNode != null) {
            startNode.setSign("Start");
        }
    }

    /**
     * Saves the signs of the graph.
     */
    private void saveSigns() {
        Node[] nodes = graph.getNodes().toArray(new Node[graph.getNodes().size()]);
        for (int i = 0; i < nodes.length; i++) {
            signSave.add(nodes[i].getSign());
        }
    }

    /**
     * Loads the signs in the graph to the same state as they were when they were saved.
     */
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

    /**
     * Attaches the needed MouseListener to the DrawPanel of the 
     * main frame. Saves the state of the graph.
     * @param mf 
     */
    @Override
    public void select(MainFrame mf) {
        if (dw == null) {
            dw = new Dijkstrawindow(this, mf);
        }
        saveGraph();
        saveSigns();
        reset();
        mf.getDrawPanel().addMouseListener(listener);
        dw.open();
    }

    /**
     * Unattaches the listener and restores the graph to the same state
     * it was in when the tool was selected.
     * @param mf 
     */
    @Override
    public void deselect(MainFrame mf) {
        loadSigns();
        mf.getDrawPanel().removeMouseListener(listener);
        dw.close();
        loadGraph();
    }

    @Override
    public String getName() {
        return "Dijkstra";
    }
}
