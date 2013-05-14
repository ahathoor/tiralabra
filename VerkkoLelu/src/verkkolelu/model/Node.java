/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.model;

import java.awt.Color;
import java.awt.Point;


/**
 *
 * @author ahathoor
 */
public class Node {
    private Color color;
    private String label;
    private Point point;

    public Node(Point point) {
        label = "";
        color = Color.black;
        this.point = point;
    }

    public void setPoint(Point point) {
        this.point = point;
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
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    
    
    
}
