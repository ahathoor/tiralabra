/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools.dijkstra;

import java.util.Comparator;
import java.util.HashMap;
import verkkolelu.model.Node;

/**
 * Compares the two nodes using the provided HashMap for key values
 * @author ahathoor
 */
public class NodeComparator implements Comparator<Node> {

    private HashMap<Node, Integer> dist;

    public NodeComparator(HashMap<Node, Integer> dist) {
        this.dist = dist;
    }

    @Override
    public int compare(Node o1, Node o2) {
        return dist.get(o1) - dist.get(o2);
    }
}
