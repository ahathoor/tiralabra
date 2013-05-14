/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import verkkolelu.model.Graph;
import verkkolelu.model.Node;
import verkkolelu.view.DrawPanel;

/**
 *
 * @author ahathoor
 */
public class LinkTool implements MouseListener {

    private DrawPanel panel;
    private Graph graph;
    Node node1;

    public LinkTool(DrawPanel i) {
        this.panel = i;
        graph = i.getGraph();
        reset();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    private void reset() {
        if (node1 != null) {
            node1.setLabel("");
            node1 = null;
            panel.repaint();
        }
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
        Node pressedNode = graph.nodeNearPoint(e.getPoint());
        if (pressedNode == null) {
            reset();
            return;
        }
        if (node1 == null) {
            node1 = pressedNode;
            node1.setLabel("link");
        } else {
            panel.linkNodes(node1, pressedNode, askWeight());
            reset();
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
