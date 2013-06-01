/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools.astar;

import javax.swing.JDialog;
import javax.swing.JFrame;
import verkkolelu.tools.commandable.CommandPanel;
import verkkolelu.view.MainFrame;

/**
 *
 * @author mikko
 */
public class AStarWindow extends JDialog {

    public AStarWindow(MainFrame mf, AStarTool astarTool) {
        super(mf);
        mf.addDialog(this);
        setFocusableWindowState(false);
        CommandPanel commandPanel = new CommandPanel(astarTool);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        add(commandPanel);
        pack();
        setSize(200,220);
        setVisible(false);
        this.setLocation(mf.getBounds().x - this.getBounds().width - 10, mf.getBounds().y + this.getBounds().height + 10);
        setFocusableWindowState(true);
    }
    
}
