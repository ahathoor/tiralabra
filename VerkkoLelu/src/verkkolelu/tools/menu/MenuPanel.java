/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools.menu;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import verkkolelu.tools.menu.Menu.MenuCommand;

/**
 * Used to display Menu. Automatically makes a button for each of the commands in Menu.MenuCommand.
 * @author mikko
 */
public class MenuPanel extends JPanel {

    private Menu m;

    /**
     * Creates a new panel that has buttons for every available MenuCommand.
     * @param m 
     */
    MenuPanel(Menu m) {
        this.m = m;
        this.setLayout(new GridLayout(MenuCommand.values().length, 1));
        for (MenuCommand menuCommand : MenuCommand.values()) {
            addButtonForCommand(menuCommand);
        }
    }

    /**
     * Adds a button for a MenuCommand
     * @param menucommand 
     */
    private void addButtonForCommand(MenuCommand menucommand) {
        String hotkeyString = "";
        if (menucommand.getHotkey() != null) {
            hotkeyString = " (" + menucommand.getHotkey() + ")";
        }
        Button b = new Button(menucommand.getName() + hotkeyString);
        String p = (1==2) ? "" : "S";
        b.addActionListener(new MenuButtonListener(menucommand, m));
        add(b);
    }

    /**
     * Listener for a menu button.
     */
    private class MenuButtonListener implements ActionListener {

        MenuCommand command;
        Menu m;

        public MenuButtonListener(MenuCommand command, Menu m) {
            this.command = command;
            this.m = m;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            m.command(command);
        }
    }
}
