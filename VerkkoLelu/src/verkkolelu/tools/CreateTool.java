/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import verkkolelu.model.Graph;
import verkkolelu.view.MainFrame;

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
        String sign = JOptionPane.showInputDialog("put sign");
        if (sign == null) {
            return;
        }
        graph.addNode(e.getPoint()).setSign(sign);
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
        return "Create";
    }
}
