/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import verkkolelu.model.Graph;
import verkkolelu.model.Node;
import verkkolelu.view.DrawPanel;

/**
 *
 * @author ahathoor
 */
public class MoveTool implements MouseListener, Tool {

    private DrawPanel panel;
    private Graph graph;
    Node selected;

    public MoveTool(DrawPanel i) {
        this.panel = i;
        graph = i.getGraph();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (selected != null) {
            graph.moveNode(selected, e.getPoint());
            selected.setLabel("");
            selected = null;
        } else {
            Node nodeNearClick = graph.nodeNearPoint(e.getPoint());
            if (nodeNearClick != null) {
                selected = nodeNearClick;
                nodeNearClick.setLabel("move");
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
        System.out.println("Move tool selected");
    }

    @Override
    public void deselect() {
    }
}
