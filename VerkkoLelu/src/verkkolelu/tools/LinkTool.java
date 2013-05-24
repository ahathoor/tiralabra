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
import verkkolelu.view.MainFrame;

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
            if (weightS == null) {
                return -1;
            }
            if (weightS.equals("")) {
                return 1;
            }
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
            int weight = askWeight();
            if (weight == -1)
                return;
            graph.crossLinkNodes(node1, pressedNode, weight);
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
    public void select(MainFrame mf) {
        mf.addMouseListener(this);
        reset();
    }

    @Override
    public void deselect(MainFrame mf) {
        mf.removeMouseListener(this);
    }
    
    @Override
    public String getName() {
        return "Link";
    }
}
