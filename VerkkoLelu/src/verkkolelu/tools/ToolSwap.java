/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import verkkolelu.model.Graph;
import verkkolelu.tools.dijkstra.DijkstraTool;
import verkkolelu.view.DrawPanel;

/**
 *
 * @author ahathoor
 */
public class ToolSwap implements KeyListener {
    
    private DrawPanel p;
    private Tool selectedTool;
    
    public ToolSwap(DrawPanel p) {
        this.p = p;
        selectTool(new CreateTool(p.getGraph()));
    }
    
    private void selectTool(Tool tool) {
        deselectCurrentTool();
        selectedTool = tool;
        p.addMouseListener(tool);
        tool.select();
    }
    
    private void deselectCurrentTool() {
        if (selectedTool != null) {
            p.removeMouseListener(selectedTool);
            selectedTool.deselect();
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'd') {
            selectTool(new DeleteTool(p.getGraph()));
        }
        if (e.getKeyChar() == 'i') {
            selectTool(new DijkstraTool(p.getGraph()));
        }
        if (e.getKeyChar() == 'c') {
            selectTool(new CreateTool(p.getGraph()));
        }
        if (e.getKeyChar() == 'm') {
            selectTool(new MoveTool(p.getGraph()));
        }
        if (e.getKeyChar() == 'l') {
            selectTool(new LinkTool(p.getGraph()));
        }
        if (e.getKeyChar() == 'o') {
            System.out.println(p.getGraph().saveToString());
        }
        if (e.getKeyChar() == 'p') {
            String saveString = JOptionPane.showInputDialog("Paste the save string here");
            try {
                Graph loadedGraph = new Graph();
                loadedGraph.loadFromString(saveString);
                p.setGraph(loadedGraph);
            } catch (NumberFormatException ex) {
                System.out.println("Loading of the input string caused an error: " + ex.getLocalizedMessage());
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
