/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.view;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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

    private DrawPanel drawPanel;

    public DrawPanel getDrawPanel() {
        return drawPanel;
    }

    public MainFrame() {
        super("VerkkoLelu");
        Graph maingraph = new Graph();
        drawPanel = new DrawPanel(maingraph);
        this.addKeyListener(new Menu(maingraph, this));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(drawPanel);
        pack();
        setSize(500, 500);
        setVisible(true);
        Console c = new Console(this);
        this.setLocation(210, 0);
        
        this.addComponentListener(new ComponentAdapter() {  
            private Rectangle bwas = MainFrame.this.getBounds();

            /**
             * Moves all the added JDialogs if they are not overlapping the main window
             */
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
    private ArrayList<JDialog> dialogs = new ArrayList<>();

    /**
     * Callback handle for JDialogs.
     * @param dialog 
     */
    public void addDialog(JDialog dialog) {
        dialogs.add(dialog);
    }
}
