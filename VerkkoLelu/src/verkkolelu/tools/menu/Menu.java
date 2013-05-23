/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools.menu;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import verkkolelu.model.Graph;
import verkkolelu.tools.CreateTool;
import verkkolelu.tools.DeleteTool;
import verkkolelu.tools.LinkTool;
import verkkolelu.tools.MoveTool;
import verkkolelu.tools.Tool;
import verkkolelu.tools.dijkstra.DijkstraTool;
import verkkolelu.view.DrawPanel;

/**
 *
 * @author ahathoor
 */
public class Menu implements KeyListener {

    DijkstraTool dijkstraTool;

    void command(MenuCommand command) {
        if (command == MenuCommand.CREATE) {
            selectTool(new CreateTool(p.getGraph()));
        }
        if (command == MenuCommand.LINK) {
            selectTool(new LinkTool(p.getGraph()));
        }
        if (command == MenuCommand.MOVE) {
            selectTool(new MoveTool(p.getGraph()));
        }
        if (command == MenuCommand.DELETE) {
            selectTool(new DeleteTool(p.getGraph()));
        }
        if (command == MenuCommand.SAVE) {
            System.out.println(p.getGraph().saveToString());
        }
        if (command == MenuCommand.LOAD) {
            selectTool(new CreateTool(p.getGraph()));
            loadDialog();
        }
        if (command == MenuCommand.CLEAR) {
            selectTool(new CreateTool(p.getGraph()));
            p.getGraph().empty();
        }
        if (command == MenuCommand.DIJKSTRA) {
            selectTool(dijkstraTool);
        }
        if (command == MenuCommand.QUIT) {
            System.exit(0);
        }
    }

    private void loadDialog() {
        String saveString = JOptionPane.showInputDialog("Paste the save string here");
        try {
            Graph loadedGraph = new Graph();
            loadedGraph.loadFromString(saveString);
            p.setGraph(loadedGraph);
        } catch (NumberFormatException ex) {
            System.out.println("Loading of the input string caused an error: " + ex.getLocalizedMessage());
        } catch (NullPointerException npe) {
            //cancel
        }
    }

    protected enum MenuCommand {

        CREATE("Create", 'c'),
        LINK("Link", 'l'),
        MOVE("Move", 'm'),
        DELETE("Delete", 'x'),
        SAVE("Save to string", 's'),
        LOAD("Load from string", 'l'),
        CLEAR("Clear graph"),
        DIJKSTRA("Dijkstra tool", 'd'),
        QUIT("Quit");
        private String name;
        private Character hotkey;

        private MenuCommand(String name, Character hotkey) {
            this(name);
            this.hotkey = hotkey;
        }

        private MenuCommand(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public Character getHotkey() {
            return hotkey;
        }
    }
    private DrawPanel p;
    private Tool selectedTool;

    public Menu(DrawPanel p) {
        this.p = p;
        selectTool(new CreateTool(p.getGraph()));
        MenuWindow mw = new MenuWindow(this);
        dijkstraTool = new DijkstraTool(p.getGraph());
    }

    private void selectTool(Tool tool) {
        deselectCurrentTool();
        selectedTool = tool;
        tool.select(p);
        System.out.println(tool.getName() + " tool selected");
    }

    private void deselectCurrentTool() {
        if (selectedTool != null) {
            selectedTool.deselect(p);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        for (MenuCommand menuCommand : MenuCommand.values()) {
            if (menuCommand.getHotkey() != null && menuCommand.getHotkey() == e.getKeyChar()) {
                command(menuCommand);
                return;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
