/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools.dijkstra;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author ahathoor
 */
public class Dijkstrawindow extends JFrame {

    private DijkstraTool d;

    public Dijkstrawindow(DijkstraTool d) throws HeadlessException {
        super("DijkstraTool");
        this.d = d;
        DijkstraPanel p = new DijkstraPanel(d);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(p);
        pack();
        setBounds(5, 5, 400, 300);
        setVisible(true);
    }

    public void close() {
        WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
    }
}
