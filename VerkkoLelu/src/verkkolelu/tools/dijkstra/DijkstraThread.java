
package verkkolelu.tools.dijkstra;

import java.awt.Color;
import verkkolelu.model.Edge;
import verkkolelu.model.Graph;
import verkkolelu.model.Node;
import verkkolelu.tools.commandable.StepThread;
import verkkolelu.tools.dijkstra.DijkstraTool;
import verkkolelu.util.ArrayList;
import verkkolelu.util.HashMap;

/**
 * A separate thread for the Dijkstra's algorithm. It extends StepThread,
 * so it pauses itself during execution and waits for a call to resumeThread().
 */
public class DijkstraThread extends StepThread {

    private boolean isDone;
    Graph graph;
    Node startNode;
    private HashMap<Node, Integer> dist;
    private HashMap<Node, Node> previous;
    private ArrayList<Node> unprocessedNodes;

    public DijkstraThread(Graph graph, Node startNode) {
        this.graph = graph;
        this.startNode = startNode;
        isDone = false;
        init();
    }

    /**
     * Returns the node that has the smallest dist -value.
     * @return 
     */
    private Node popNodeWithLowestDist() {
        int min = Integer.MAX_VALUE;
        int minIndex = 0;
        for (int i = 0; i < unprocessedNodes.size(); i++) {
            Node node = unprocessedNodes.get(i);
            if (dist.get(node) < min) {
                min = dist.get(node);
                minIndex = i;
            }
        }
        Node ret = unprocessedNodes.get(minIndex);
        unprocessedNodes.removeIndex(minIndex);
        return ret;
    }

    /**
     * Initialized the algorithm.
     */
    public void init() {

        dist = new HashMap<>();
        previous = new HashMap<>();
        unprocessedNodes = new ArrayList<>();

        for (Node n : graph.getNodes()) {
            dist.put(n, Integer.MAX_VALUE);
            previous.put(n, null);
            unprocessedNodes.add(n);
            n.setLabel("prev: - dist: " + dist.get(n));
        }

        dist.put(startNode, 0);

        startNode.setLabel("prev: - dist: 0");

        System.out.println("Initialization: dist(Node n) = 0 for start node, INF for other nodes");
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Runs the algorithm, pausing every step of the way.
     */
    @Override
    public void run() {
        pause();
        while (!unprocessedNodes.isEmpty()) {
            Node popped = popNodeWithLowestDist();
            System.out.println("");
            System.out.println("Node " + popped.getSign() + " taken from the list of unprocessed nodes. It has a distance value of " + dist.get(popped) + ". ");
            System.out.println("");
            popped.setColor(Color.BLUE);

            if (dist.get(popped) == Integer.MAX_VALUE) {
                System.out.println("Rest of the nodes unreachable.");
                break;
            }
            for (Edge edge : graph.getEdgesFrom(popped)) {
                pause();

                Node neighbour = edge.getNode2();
                System.out.println("Node " + popped.getSign() + " is connected to neighbour " + neighbour.getSign() + " with distance " + edge.getWeight() + ". ");
                neighbour.setColor(Color.orange);
                pause();

                int alt = dist.get(popped) + edge.getWeight();

                System.out.println("\tThe distance to node " + popped.getSign() + " plus the distance from " + popped.getSign() + " to " + neighbour.getSign()
                        + " = " + dist.get(popped) + " + " + edge.getWeight() + " = " + alt + ".");
                String oldLabel = neighbour.getLabel();
                neighbour.setLabel(dist.get(popped) + " + " + edge.getWeight() + " < " + dist.get(neighbour) + " ?");

                pause();

                if (alt < dist.get(neighbour)) {
                    System.out.println("\tIt is smaller than the current dist value for " + neighbour.getSign() + ", " + dist.get(neighbour) + ". ");

                    dist.put(neighbour, alt);
                    previous.put(neighbour, popped);
                    neighbour.setLabel("prev: " + previous.get(neighbour).getSign() + " dist: " + alt);

                    neighbour.setColor(Color.green);

                    pause();

                    System.out.println("\t -> The new distance value for " + neighbour.getSign() + " was changed to " + alt + " and it's previous node was set to "
                            + popped.getSign() + ".");
                    System.out.println("\t    It was readded to the heap with value the new dist value. ");
                } else {
                    System.out.println("\tIt is not smaller than the current dist " + dist.get(neighbour) + ". ");
                    neighbour.setColor(Color.red);
                    neighbour.setLabel(oldLabel);
                    pause();
                }
                neighbour.setColor(Color.lightGray);
            }
            popped.setColor(Color.lightGray);
            pause();
        }
        isDone = true;
    }
}