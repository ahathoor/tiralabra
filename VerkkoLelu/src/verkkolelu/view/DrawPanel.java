/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import verkkolelu.model.GraphChangeListener;
import verkkolelu.tools.menu.Menu;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import verkkolelu.model.Edge;
import verkkolelu.model.Graph;
import verkkolelu.model.Node;

/**
 *
 * @author ahathoor
 */
public class DrawPanel extends JPanel implements GraphChangeListener {

    private Graph graph;

    public DrawPanel(Graph graph) {
        this.graph = graph;
        graph.addListener(this);
        test();
    }

    public void test() {
        graph.loadFromString("0<nodeInternal>108<nodeInternal>135<nodeInternal>a<nodeInternal>-4144960<node>1<nodeInternal>105<nodeInternal>349<nodeInternal>b<nodeInternal>-4144960<node>2<nodeInternal>290<nodeInternal>394<nodeInternal>c<nodeInternal>-4144960<node>3<nodeInternal>278<nodeInternal>89<nodeInternal>d<nodeInternal>-4144960<node>4<nodeInternal>393<nodeInternal>243<nodeInternal>e<nodeInternal>-4144960<node><graphInternal>4<edgeInternal>3<edgeInternal>2<edge>4<edgeInternal>2<edgeInternal>3<edge>4<edgeInternal>1<edgeInternal>9<edge>4<edgeInternal>0<edgeInternal>10<edge>2<edgeInternal>4<edgeInternal>3<edge>2<edgeInternal>1<edgeInternal>4<edge>2<edgeInternal>0<edgeInternal>6<edge>2<edgeInternal>3<edgeInternal>7<edge>1<edgeInternal>2<edgeInternal>4<edge>1<edgeInternal>0<edgeInternal>5<edge>1<edgeInternal>3<edgeInternal>8<edge>1<edgeInternal>4<edgeInternal>9<edge>3<edgeInternal>0<edgeInternal>1<edge>3<edgeInternal>4<edgeInternal>2<edge>3<edgeInternal>2<edgeInternal>7<edge>3<edgeInternal>1<edgeInternal>8<edge>0<edgeInternal>3<edgeInternal>1<edge>0<edgeInternal>1<edgeInternal>5<edge>0<edgeInternal>2<edgeInternal>6<edge>0<edgeInternal>4<edgeInternal>10<edge>"); 
    }

    /**
     * Paints the graph to the panel.
     * @param g 
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        BufferedImage nodeLayer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics nodeLayerG = nodeLayer.getGraphics();

        BufferedImage edgeLayer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D edgeLayerG = (Graphics2D) edgeLayer.getGraphics();
        edgeLayerG.setStroke(new BasicStroke(2));

        BufferedImage textLayer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics textLayerG = textLayer.getGraphics();
        textLayerG.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));

        //For each node
        for (Node node : graph.getNodes()) {
            Point p1 = node.getPoint();
            //Draw the node
            nodeLayerG.setColor(Color.black);
            nodeLayerG.fillOval(p1.x - 12, p1.y - 12, 24, 24);
            nodeLayerG.setColor(node.getColor());
            nodeLayerG.fillOval(p1.x - 10, p1.y - 10, 20, 20);
            //Draw the edges
            for (Edge edge : graph.getEdgesFrom(node)) {
                edgeLayerG.setColor(edge.getColor());
                Point p2 = edge.getNode2().getPoint();
                int weight = edge.getWeight();
                edgeLayerG.drawLine(p1.x, p1.y, p2.x, p2.y);
                //Paint the weight numbers of the edge on the text layer
                textLayerG.setColor(Color.black);
                textLayerG.setFont(textLayerG.getFont().deriveFont(12f));
                char[] weightChars = ("" + weight).toCharArray();
                textLayerG.drawChars(weightChars, 0, weightChars.length, (p1.x + p2.x) / 2 - 3 * weightChars.length, (p1.y + p2.y) / 2);
            }
            //Paint the label on the text layer
            textLayerG.setFont(textLayerG.getFont().deriveFont(12f));
            textLayerG.setColor(Color.black);
            char[] label = node.getLabel().toCharArray();
            textLayerG.drawChars(label, 0, label.length, p1.x - label.length * 3, p1.y - 15);
            //Paint the sign in a little box
            textLayerG.setFont(textLayerG.getFont().deriveFont(16f));
            char[] sign = node.getSign().toCharArray();
            int signx = p1.x - 10 * sign.length / 2;
            int signy = p1.y + 5 + 24;
            textLayerG.setColor(Color.BLACK);
            textLayerG.fillRoundRect(signx + 1, signy - 14 + 1, 10 * sign.length, 16, 3, 3);
            textLayerG.setColor(Color.WHITE);
            textLayerG.fillRoundRect(signx - 1, signy - 14 - 1, 10 * sign.length, 16, 3, 3);
            textLayerG.setColor(new Color(0xFFFDE8));
            textLayerG.fillRoundRect(signx, signy - 14, 10 * sign.length, 16, 3, 3);
            textLayerG.setColor(Color.black);
            textLayerG.drawChars(sign, 0, sign.length, signx, signy);
        }
        g.drawImage(edgeLayer, 0, 0, this);
        g.drawImage(nodeLayer, 0, 0, this);
        g.drawImage(textLayer, 0, 0, this);
    }

    /**
     * The graph uses this to notify the panel of changes
     */
    @Override
    public void graphChanged() {
        this.repaint();
    }
}
