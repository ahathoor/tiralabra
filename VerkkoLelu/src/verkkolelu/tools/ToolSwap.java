/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools;

import verkkolelu.tools.dijkstra.DijkstraTool;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
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
        selectTool(new CreateTool(p));
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
            selectTool(new DeleteTool(p));
        }
        if (e.getKeyChar() == 'i') {
            selectTool(new DijkstraTool(p));
        }
        if (e.getKeyChar() == 'c') {
            selectTool(new CreateTool(p));
        }
        if (e.getKeyChar() == 'l') {
            selectTool(new LinkTool(p));
        }
        if (e.getKeyChar() == 'm') {
            selectTool(new MoveTool(p));
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
