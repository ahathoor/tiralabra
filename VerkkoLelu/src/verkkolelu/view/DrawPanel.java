/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.view;

import listeners.ToolSwap;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import verkkolelu.model.Edge;
import verkkolelu.model.Graph;
import verkkolelu.model.Node;

/**
 *
 * @author ahathoor
 */
public class DrawPanel extends JPanel {

    Graph graph;
    HashMap<Node, Point> nodeToPoint;
    HashMap<Node, String> nodeToLabel;

    public DrawPanel() {
        graph = new Graph();
        nodeToPoint = new HashMap<>();
        nodeToLabel = new HashMap<>();
        this.addKeyListener(new ToolSwap(this));
        this.setFocusable(true);
        this.requestFocus();
        test();
    }

    public Node nodeNearPoint(Point p) {
        for (Node n : graph.getNodes()) {
            Point nodePoint = nodeToPoint.get(n);
            if (nodePoint.distance(p) <= 10) {
                return n;
            }
        }
        return null;
    }

    public void test() {
        Node n1 = addNode(new Point(300, 100));
        Node n2 = addNode(new Point(200, 400));
        Node n3 = addNode(new Point(140, 200));
        graph.linkNodes(n1, n2, 10);
        graph.linkNodes(n2, n3, 102);
        graph.linkNodes(n1, n3, 132);
    }

    public Node addNode(Point p) {
        Node addedNode = graph.addNode();
        nodeToPoint.put(addedNode, p);
        nodeToLabel.put(addedNode, "");
        return addedNode;
    }
    
    public void setLabel(Node n, String label) {
        nodeToLabel.put(n, label);
    }

    public void linkNodes(Node n1, Node n2, int weight) {
        graph.linkNodes(n1, n2, weight);
    }

    public void deleteNode(Node n) {
        graph.deleteNode(n);
        nodeToPoint.remove(n);
        nodeToLabel.remove(n);
    }

    public void moveNode(Node n, Point p) {
        nodeToPoint.put(n, p);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Node node : graph.getNodes()) {
            g.setColor(Color.blue);
            Point p1 = nodeToPoint.get(node);
            g.drawOval(p1.x - 10, p1.y - 10, 20, 20);
            char[] label = nodeToLabel.get(node).toCharArray();
            g.drawChars(label,0,label.length,p1.x,p1.y-15);
            for (Edge e : graph.getEdges().get(node)) {
                g.setColor(Color.red);
                Point p2 = nodeToPoint.get(e.getNode2());
                int weight = e.getWeight();
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
                g.setColor(Color.black);
                g.drawChars(("" + weight).toCharArray(), 0, ("" + weight).length(), (p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
            }
        }
    }
}
