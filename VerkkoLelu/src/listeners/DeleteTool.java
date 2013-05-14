/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import verkkolelu.model.Graph;
import verkkolelu.model.Node;
import verkkolelu.view.DrawPanel;

/**
 *
 * @author ahathoor
 */
public class DeleteTool implements MouseListener{

    private DrawPanel panel;
    private Graph graph;

    public DeleteTool(DrawPanel i) {
        this.panel = i;
        graph = i.getGraph();
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
