/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools.dijkstra;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.PriorityQueue;
import verkkolelu.model.Graph;
import verkkolelu.model.Node;
import verkkolelu.tools.Tool;
import verkkolelu.view.DrawPanel;

/**
 *
 * @author ahathoor
 */
public class DijkstraTool implements MouseListener, Tool {

    private DrawPanel panel;
    private Graph graph;
    Dijkstrawindow dw;
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
        Q = new PriorityQueue<>(graph.nodeCount(), new NodeComparator(dist));
        dw = new Dijkstrawindow(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    public void reset() {
        if (start != null) {
            start.setLabel("");
        }
        if (end != null) {
            end.setLabel("");
        }
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

    @Override
    public void select() {
        System.out.println("Dijkstra tool selected");
    }

    @Override
    public void deselect() {
        reset();
        dw.close();
    }
}
