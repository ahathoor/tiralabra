/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.view;

import listeners.ToolSwap;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
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

    public DrawPanel() {
        graph = new Graph();
        this.addKeyListener(new ToolSwap(this));
        this.setFocusable(true);
        this.requestFocus();
        test();
    }
    public void test() {
        Node n1 = graph.addNode(new Point(300, 100));
        Node n2 = graph.addNode(new Point(200, 400));
        Node n3 = graph.addNode(new Point(140, 200));
        graph.linkNodes(n1, n2, 10);
        graph.linkNodes(n2, n3, 102);
        graph.linkNodes(n1, n3, 132);
    }

    public Graph getGraph() {
        return graph;
    }

    public void linkNodes(Node n1, Node n2, int weight) {
        graph.linkNodes(n1, n2, weight);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Node node : graph.getNodes()) {
            g.setColor(node.getColor());
            Point p1 = node.getPoint();
            g.drawOval(p1.x - 10, p1.y - 10, 20, 20);
            char[] label = node.getLabel().toCharArray();
            g.drawChars(label,0,label.length,p1.x,p1.y-15);
            for (Edge edge : graph.getEdges().get(node)) {
                g.setColor(edge.getColor());
                Point p2 = edge.getNode2().getPoint();
                int weight = edge.getWeight();
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
                g.setColor(Color.black);
                g.drawChars(("" + weight).toCharArray(), 0, ("" + weight).length(), (p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
            }
        }
    }
}
