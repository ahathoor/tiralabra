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
import verkkolelu.view.MainFrame;

/**
 *
 * @author ahathoor
 */
public class Menu implements KeyListener {

    DijkstraTool dijkstraTool;
    MenuWindow mw;

    void command(MenuCommand command) {
        if (command == MenuCommand.CREATE) {
            selectTool(new CreateTool(graph));
        }
        if (command == MenuCommand.LINK) {
            selectTool(new LinkTool(graph));
        }
        if (command == MenuCommand.MOVE) {
            selectTool(new MoveTool(graph));
        }
        if (command == MenuCommand.DELETE) {
            selectTool(new DeleteTool(graph));
        }
        if (command == MenuCommand.SAVE) {
            System.out.println(graph.saveToString());
        }
        if (command == MenuCommand.LOAD) {
            selectTool(new CreateTool(graph));
            loadDialog();
        }
        if (command == MenuCommand.CLEAR) {
            selectTool(new CreateTool(graph));
            graph.empty();
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
            graph.loadFromString(saveString);
        } catch (NumberFormatException ex) {
            System.out.println("Loading of the input string caused an error: " + ex.getLocalizedMessage());
        } catch (NullPointerException npe) {
            //cancel
        }
    }

    void openWindow() {
        mw = new MenuWindow(this, mainFrame);
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
    private Graph graph;
    private MainFrame mainFrame;
    private Tool selectedTool;

    public Menu(Graph graph, MainFrame mainFrame) {
        this.graph = graph;
        this.mainFrame = mainFrame;
        selectTool(new CreateTool(graph));
        mw = new MenuWindow(this, mainFrame);
        dijkstraTool = new DijkstraTool(graph);
    }

    private void selectTool(Tool tool) {
        deselectCurrentTool();
        selectedTool = tool;
        tool.select(mainFrame);
    }

    private void deselectCurrentTool() {
        if (selectedTool != null) {
            selectedTool.deselect(mainFrame);
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
