/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

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

    public ToolSwap(DrawPanel p) {
        this.p = p;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        MouseListener[] listeners = p.getMouseListeners();
        for (int i = 0; i < listeners.length; i++) {
            p.removeMouseListener(listeners[i]);
        }
        if (e.getKeyChar() == 'd') {
            System.out.println("Delete tool selected");
            p.addMouseListener(new DeleteTool(p));
        }
        if (e.getKeyChar() == 'i') {
            System.out.println("Dijkstra tool selected");
            p.addMouseListener(new DijkstraTool(p));
        }
        if (e.getKeyChar() == 'c') {
            System.out.println("Create tool selected");
            p.addMouseListener(new CreateTool(p));
        }
        if (e.getKeyChar() == 'l') {
            System.out.println("Link tool selected");
            p.addMouseListener(new LinkTool(p));
        }
        if (e.getKeyChar() == 'm') {
            System.out.println("Move tool selected");
            p.addMouseListener(new MoveTool(p));
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
