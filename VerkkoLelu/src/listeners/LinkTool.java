/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import verkkolelu.model.Node;
import verkkolelu.view.DrawPanel;

/**
 *
 * @author ahathoor
 */
public class LinkTool implements MouseListener {

    private DrawPanel ikkuna;
    Node node1;

    public LinkTool(DrawPanel i) {
        this.ikkuna = i;
        reset();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    private void reset() {
        ikkuna.setLabel(node1, "");
        node1 = null;
        ikkuna.repaint();
    }

    private int askWeight() {
        while (true) {
                String weightS = JOptionPane.showInputDialog("Insert weight");
                try {
                    int weight = Integer.parseInt(weightS);
                    return weight;
                } catch (NumberFormatException ex) {
                    System.out.println("Input a proper number");
                }
            }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Node pressedNode = ikkuna.nodeNearPoint(e.getPoint());
        if (pressedNode == null) {
            reset();
            return;
        }
        if (node1 == null) {
            node1 = pressedNode;
            ikkuna.setLabel(node1, "link");
        } else {
            ikkuna.linkNodes(node1, pressedNode, askWeight());
            reset();
        }
        ikkuna.repaint();
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
