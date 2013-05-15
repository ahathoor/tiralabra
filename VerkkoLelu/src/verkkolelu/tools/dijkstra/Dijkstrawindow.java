/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools.dijkstra;

import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author ahathoor
 */
public class Dijkstrawindow extends JFrame {

    private DijkstraTool d;

    public Dijkstrawindow(DijkstraTool d) throws HeadlessException {
        super("DijkstraTool");
        setFocusableWindowState(false);
        this.d = d;
        DijkstraPanel p = new DijkstraPanel(d);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(p);
        pack();
        setVisible(true);
        setFocusableWindowState(true);
    }

    public void close() {
        setVisible(false);
//        WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
//        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
    }

    void open() {
        setVisible(true);
    }
}
