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
        Node n1 = graph.addNode(new Point(300, 100));
        Node n2 = graph.addNode(new Point(200, 400));
        Node n3 = graph.addNode(new Point(140, 200));
        graph.linkNodes(n1, n2, 10);
        graph.linkNodes(n2, n3, 102);
        graph.linkNodes(n1, n3, 132);
        graph.loadFromString("0<nodeInternal>300<nodeInternal>100<node>1<nodeInternal>200<nodeInternal>400<node>2<nodeInternal>140<nodeInternal>200<node>3<nodeInternal>361<nodeInternal>176<node>4<nodeInternal>306<nodeInternal>322<node><graphInternal>0<edgeInternal>1<edgeInternal>10<edge>0<edgeInternal>2<edgeInternal>132<edge>0<edgeInternal>1<edgeInternal>10<edge>0<edgeInternal>2<edgeInternal>132<edge>0<edgeInternal>3<edgeInternal>1<edge>4<edgeInternal>3<edgeInternal>2<edge>4<edgeInternal>1<edgeInternal>9000<edge>1<edgeInternal>0<edgeInternal>10<edge>1<edgeInternal>2<edgeInternal>102<edge>1<edgeInternal>2<edgeInternal>102<edge>1<edgeInternal>0<edgeInternal>10<edge>1<edgeInternal>4<edgeInternal>9000<edge>3<edgeInternal>0<edgeInternal>1<edge>3<edgeInternal>4<edgeInternal>2<edge>2<edgeInternal>1<edgeInternal>102<edge>2<edgeInternal>1<edgeInternal>102<edge>2<edgeInternal>0<edgeInternal>132<edge>2<edgeInternal>0<edgeInternal>132<edge>");
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
