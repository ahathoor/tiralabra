/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import verkkolelu.model.Graph;
import verkkolelu.view.DrawPanel;

/**
 *
 * @author ahathoor
 */
public class CreateTool implements MouseListener, Tool {

    private Graph graph;

    public CreateTool(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        graph.addNode(e.getPoint());
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
    public void select(DrawPanel p) {
        p.addMouseListener(this);
    }

    @Override
    public void deselect(DrawPanel p) {
        p.removeMouseListener(this);
    }

    @Override
    public String getName() {
        return "Create";
    }
    
}
