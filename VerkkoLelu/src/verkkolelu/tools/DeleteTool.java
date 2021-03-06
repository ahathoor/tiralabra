/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import verkkolelu.model.Graph;
import verkkolelu.model.Node;
import verkkolelu.view.MainFrame;

/**
 *
 * @author ahathoor
 */
public class DeleteTool implements MouseListener, Tool {

    private Graph graph;

    public DeleteTool(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Node nodeNearClick = graph.nodeNearPoint(e.getPoint());
        if (nodeNearClick != null) {
            graph.deleteNode(nodeNearClick);
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
    public void select(MainFrame mf) {
       mf.getDrawPanel().addMouseListener(this);
    }

    @Override
    public void deselect(MainFrame mf) {
        mf.getDrawPanel().removeMouseListener(this);
    }

    @Override
    public String getName() {
        return "Delete";
    }
}
