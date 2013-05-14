/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.model;

import java.awt.Color;

/**
 *
 * @author ahathoor
 */
public class Edge {
    private Node node2;
    private int weight;
    private Color color;

    public Edge(Node node2, int weight) {
        this.node2 = node2;
        this.weight = weight;
        this.color = Color.red;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
    
    

    public Node getNode2() {
        return node2;
    }

    public int getWeight() {
        return weight;
    }
}
