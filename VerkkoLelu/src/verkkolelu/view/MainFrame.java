/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.view;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import verkkolelu.model.Graph;
import verkkolelu.tools.menu.Menu;
import verkkolelu.util.ArrayList;

/**
 *
 * @author ahathoor
 */
public class MainFrame extends JFrame {

    DrawPanel p;

    public MainFrame() {
        super("VerkkoLelu");
        Graph maingraph = new Graph();
        p = new DrawPanel(maingraph);
        this.addKeyListener(new Menu(maingraph, this));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(p);
        pack();
        setSize(500, 500);
        setVisible(true);

        this.setLocation(100, 100);
        this.addComponentListener(new ComponentAdapter() {
            
            Rectangle bwas = MainFrame.this.getBounds();

            @Override
            public void componentMoved(ComponentEvent e) {
                Rectangle dbounds = MainFrame.this.getBounds();
                int deltaX = dbounds.x - bwas.x;
                int deltaY = dbounds.y - bwas.y;
                for (JDialog dialog : dialogs) {
                    if (bwas.intersects(dialog.getBounds())) {
                        continue;
                    }
                    Point dloc = dialog.getLocation();
                    dialog.setLocation(dloc.x + deltaX, dloc.y + deltaY);
                }
                bwas = MainFrame.this.getBounds();
            }
        });
    }
    ArrayList<JDialog> dialogs = new ArrayList<>();

    public void addDialog(JDialog dialog) {
        dialogs.add(dialog);
    }
}
