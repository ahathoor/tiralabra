/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools.dijkstra;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

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

    public DijkstraPanel(DijkstraTool d) {
        this.d = d;
        Button init = new Button("init");
        init.addActionListener(new DijkstraToolListener(DijkstraCommand.INIT, d));
        add(init);
        Button step = new Button("step");
        step.addActionListener(new DijkstraToolListener(DijkstraCommand.STEP, d));add(init);
        add(step);
    }
    
}
