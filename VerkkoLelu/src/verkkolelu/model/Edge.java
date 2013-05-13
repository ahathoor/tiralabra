/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.model;

/**
 *
 * @author ahathoor
 */
public class Edge {
    private Node node2;
    private int weight;

    public Edge(Node node2, int weight) {
        this.node2 = node2;
        this.weight = weight;
    }

    public Node getNode2() {
        return node2;
    }

    public int getWeight() {
        return weight;
    }
}
