/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.model;

import java.awt.Color;
import java.awt.Point;

/**
 * Node class
 *
 * @author Mikko Tamminen
 */
public class Node {

    private Color color;
    private String label;
    private String sign;
    private Point point;
    private Graph graph;

    /**
     * Creates a new node at the given point and in the given graph
     *
     * @param point
     * @param graph
     */
    public Node(Point point, Graph graph) {
        label = "";
        sign = "";
        color = Color.lightGray;
        this.point = point;
        this.graph = graph;
    }

    public void setPoint(Point point) {
        this.point = point;
        graph.notifyListeners();
    }
    
    public void setSign(String sign) {
        this.sign = sign;
        graph.notifyListeners();
    }

    public String getSign() {
        return sign;
    }

    public Point getPoint() {
        return point;
    }

    public Color getColor() {
        return color;
    }

    public String getLabel() {
        return label;
    }

    public void setColor(Color color) {
        this.color = color;
        graph.notifyListeners();
    }

    public void setLabel(String label) {
        this.label = label;
        graph.notifyListeners();
    }
}
