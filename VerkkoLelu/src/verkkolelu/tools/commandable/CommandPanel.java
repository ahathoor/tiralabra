/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools.commandable;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author mikko
 */
public class CommandPanel extends JPanel {

    private CommandableTool commandableTool;
    private ToolCommand[] commands;

    public CommandPanel(CommandableTool ct) {
        this.commandableTool = ct;
        commands = ct.getCommands();
        this.setLayout(new GridLayout(commands.length, 1));
        for (ToolCommand toolCommand : commands) {
            addButtonForCommand(toolCommand);
        }
    }

    private class DijkstraToolListener implements ActionListener {

        ToolCommand command;
        CommandableTool ct;

        public DijkstraToolListener(ToolCommand command, CommandableTool ct) {
            this.command = command;
            this.ct = ct;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            ct.command(command);
        }
    }

    private void addButtonForCommand(ToolCommand toolCommand) {
        Button b = new Button(toolCommand.getName());
        b.addActionListener(new DijkstraToolListener(toolCommand, commandableTool));
        add(b);
    }
}
