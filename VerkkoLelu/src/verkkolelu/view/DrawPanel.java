/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.view;

import java.awt.Color;
import verkkolelu.model.GraphChangeListener;
import verkkolelu.tools.toolswapper.ToolSwap;
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
public class DrawPanel extends JPanel implements GraphChangeListener {
    
    Graph graph;
    
    public DrawPanel() {
        setGraph(new Graph());
        this.addKeyListener(new ToolSwap(this));
        this.setFocusable(true);
        this.requestFocus();
        test();
    }

    public void test() {
        
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
        graph.addListener(this);
        repaint();
    }
    
    public Graph getGraph() {
        return graph;
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Node node : graph.getNodes()) {
            Point p1 = node.getPoint();
            g.setColor(node.getColor());
            g.drawOval(p1.x - 10, p1.y - 10, 20, 20);
            g.fillOval(p1.x - 10, p1.y - 10, 20, 20);
            
            for (Edge edge : graph.getEdges().get(node)) {
                g.setColor(edge.getColor());
                Point p2 = edge.getNode2().getPoint();
                int weight = edge.getWeight();
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
                g.setColor(Color.black);
                g.drawChars(("" + weight).toCharArray(), 0, ("" + weight).length(), (p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
            }
            
            char[] label = node.getLabel().toCharArray();
            g.drawChars(label, 0, label.length, p1.x, p1.y - 15);
            
            g.setColor(Color.cyan);
            char[] sign = node.getSign().toCharArray();
            g.drawChars(sign, 0, sign.length, p1.x-3, p1.y+3);
        }
    }
    
    @Override
    public void graphChanged() {
        this.repaint();
    }
}
