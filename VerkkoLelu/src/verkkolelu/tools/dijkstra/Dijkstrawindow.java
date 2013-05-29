/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools.dijkstra;

import java.awt.HeadlessException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import verkkolelu.view.MainFrame;

/**
 *
 * @author ahathoor
 */
public class Dijkstrawindow extends JDialog {

    private DijkstraTool d;

    public Dijkstrawindow(DijkstraTool d, MainFrame mf){
        super(mf, "DijkstraTool");
        mf.addDialog(this);
        setFocusableWindowState(false);
        this.d = d;
        DijkstraPanel p = new DijkstraPanel(d);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        add(p);
        pack();
        setSize(200,220);
        setVisible(false);
        this.setLocation(mf.getBounds().x - this.getBounds().width - 10, mf.getBounds().y + this.getBounds().height + 10);
        setFocusableWindowState(true);
    }

    public void close() {
        setVisible(false);
    }

    void open() {
        setVisible(true);
    }
}
