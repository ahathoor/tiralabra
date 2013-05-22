/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.view;

import javax.swing.JFrame;

/**
 *
 * @author ahathoor
 */
public class MainWindow extends JFrame {

    DrawPanel p;
    
    public MainWindow() {
        super("VerkkoLelu");
        p = new DrawPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(p);
        pack();
        setSize(500,500);
        setVisible(true);
    }
}
