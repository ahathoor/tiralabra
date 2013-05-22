/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools.dijkstra;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import verkkolelu.model.Node;

/**
 *
 * @author mikko
 */
public class DijkstraListener implements MouseListener {

    private DijkstraTool dt;

    public DijkstraListener(DijkstraTool dt) {
        this.dt = dt;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Node pressedNode = dt.getGraph().nodeNearPoint(e.getPoint());
        if (pressedNode == null) {
            return;
        }
        if (dt.state == DijkstraTool.State.SET_S) {
            dt.setStartNode(pressedNode);
        } 
        else if (dt.state == DijkstraTool.State.SET_T) {
            dt.setEndNode(pressedNode);
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
}
