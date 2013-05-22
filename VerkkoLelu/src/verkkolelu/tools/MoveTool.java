/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import verkkolelu.model.Graph;
import verkkolelu.model.Node;
import verkkolelu.view.DrawPanel;

/**
 *
 * @author ahathoor
 */
public class MoveTool implements MouseListener, Tool, MouseMotionListener {

    private Graph graph;
    Node selected;

    public MoveTool(Graph i) {
        graph = i;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Node nodeNearClick = graph.nodeNearPoint(e.getPoint());
        if (nodeNearClick != null) {
            selected = nodeNearClick;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        selected = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void select(DrawPanel p) {
        p.addMouseListener(this);
        p.addMouseMotionListener(this);
    }

    @Override
    public void deselect(DrawPanel p) {
        p.removeMouseListener(this);
        p.removeMouseMotionListener(this);
    }

    @Override
    public String getName() {
        return "Move";
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (selected != null) {
            selected.setPoint(e.getPoint());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
