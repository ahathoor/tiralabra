/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools;

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
public class LinkTool implements MouseListener, Tool {

    private Graph graph;
    private Node node1;
    private String labelSave;
    
    public LinkTool(Graph graph) {
        this.graph = graph;
        labelSave = "";
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    private void reset() {
        if (node1 != null) {
            node1.setLabel(labelSave);
            node1 = null;
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
            labelSave = node1.getLabel();
            node1.setLabel("link");
        } else {
            graph.linkNodes(node1, pressedNode, askWeight());
            reset();
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
    public void select(DrawPanel p) {
        p.addMouseListener(this);
        reset();
    }

    @Override
    public void deselect(DrawPanel p) {
        p.removeMouseListener(this);
    }
    
    @Override
    public String getName() {
        return "Link";
    }
}
