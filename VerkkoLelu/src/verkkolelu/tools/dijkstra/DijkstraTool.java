/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools.dijkstra;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import verkkolelu.model.Edge;
import verkkolelu.model.Graph;
import verkkolelu.model.Node;
import verkkolelu.tools.Tool;
import verkkolelu.view.DrawPanel;

/**
 *
 * @author ahathoor
 */
public class DijkstraTool implements MouseListener, Tool {

    private Graph graph;
    Dijkstrawindow dw;
    Node start;
    Node end;
    HashMap<Node, Integer> dist;
    HashMap<Node, Node> previous;
    PriorityQueue<Node> Q;

    public DijkstraTool(Graph i) {
        graph = i;
        dist = new HashMap<>();
        previous = new HashMap<>();
        Q = new PriorityQueue<>(graph.nodeCount(), new NodeComparator(dist));
        dw = new Dijkstrawindow(this);
    }

    private void init() {
        for (Node n : graph.getNodes()) {
            dist.put(n, Integer.MAX_VALUE);
            previous.put(n, null);
            Q.add(n);
            n.setLabel("INF");
        }

        dist.put(start, 0);
        Q.remove(start);
        Q.add(start);
        start.setLabel("0");
    }

    private void step() {
        if (Q.isEmpty()) {
            return;
        }
        Node u = Q.poll();
        if (dist.get(u) == Integer.MAX_VALUE) {
            System.out.println("IMPOSSIBLE");
            return;
        }
        for (Edge edge : graph.getEdges().get(u)) {
            Node v = edge.getNode2();
            int alt = dist.get(u) + edge.getWeight();
            if (alt < dist.get(v)) {
                dist.put(v, alt);
                previous.put(v, u);
                v.setLabel("dist:" + alt);
                //TODO Decrease key implementation
                Q.remove(v);
                Q.add(v);
            }
        }
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
        } else {
            init();
        }
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
        dw.open();
    }

    @Override
    public void deselect() {
        reset();
        dw.close();
    }

    void command(DijkstraCommand command) {
        if (command == DijkstraCommand.INIT) {
            init();
        }
        if (command == DijkstraCommand.STEP) {
            step();
        }
    }
}
