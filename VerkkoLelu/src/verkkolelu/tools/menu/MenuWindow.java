/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools.menu;

import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import verkkolelu.view.MainFrame;

/**
 * Used with MenuPanel to display the menu
 * @author mikko
 */
public class MenuWindow extends JDialog {

    private Menu m;

    public MenuWindow(Menu m, MainFrame mf){
        super(mf, "Menu");
        mf.addDialog(this);
        setFocusableWindowState(false);
        this.m = m;
        MenuPanel mp = new MenuPanel(m);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        add(mp);
        pack();
        setSize(200, 220);
        setVisible(true);
        setFocusableWindowState(true);
    }
}
