/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import verkkolelu.model.Graph;
import verkkolelu.model.Node;
import verkkolelu.view.DrawPanel;

/**
 *
 * @author ahathoor
 */
public class DijkstraTool implements MouseListener {

    private class NodeComparator implements Comparator<Node> {

        private HashMap<Node, Integer> dist;

        public NodeComparator(HashMap<Node, Integer> dist) {
            this.dist = dist;
        }
        
        @Override
        public int compare(Node o1, Node o2) {
            return dist.get(o2) - dist.get(o1);
        }
    }
    
    private DrawPanel panel;
    private Graph graph;
    Node start;
    Node end;
    HashMap<Node, Integer> dist;
    HashMap<Node, Node> previous;
    PriorityQueue<Node> Q;

    public DijkstraTool(DrawPanel i) {
        this.panel = i;
        graph = i.getGraph();
        dist = new HashMap<>();
        previous = new HashMap<>();
        Q = new PriorityQueue<>(100, new NodeComparator(dist));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    public void reset() {
        start.setLabel("");
        end.setLabel("");
        start = null;
        end = null;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Node pressedN = graph.nodeNearPoint(e.getPoint());
        if (pressedN == null) {
            return;
        }
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (start == null) {
                start = pressedN;
                start.setLabel("Start");
            } else if (end == null) {
                end = pressedN;
                end.setLabel("End");
            } else {
            }
        }
        panel.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
