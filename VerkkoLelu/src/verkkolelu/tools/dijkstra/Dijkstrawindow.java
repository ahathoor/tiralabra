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

    public Dijkstrawindow(DijkstraTool d, MainFrame mf) throws HeadlessException {
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
        setFocusableWindowState(true);
    }

    public void close() {
        setVisible(false);
    }

    void open() {
        setVisible(true);
    }
}
