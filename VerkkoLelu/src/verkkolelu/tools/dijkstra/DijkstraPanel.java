/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools.dijkstra;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicSplitPaneUI;

/**
 *
 * @author ahathoor
 */
public class DijkstraPanel extends JPanel{
    private DijkstraTool d;
    
    private class DijkstraToolListener implements ActionListener {

        DijkstraCommand command;
        DijkstraTool dt;

        public DijkstraToolListener(DijkstraCommand command, DijkstraTool dt) {
            this.command = command;
            this.dt = dt;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            dt.command(command);
        }
        
    }
    
    private void addButtonForCommand(DijkstraCommand dc) {
        Button b = new Button(dc.NAME);
        b.addActionListener(new DijkstraToolListener(dc, d));
        add(b);
    }

    public DijkstraPanel(DijkstraTool d) {
        this.d = d;
        this.setLayout(new GridLayout(DijkstraCommand.values().length, 1));
        for (DijkstraCommand dijkstraCommand : DijkstraCommand.values()) {
            addButtonForCommand(dijkstraCommand);
        }
    }
    
}
