/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import verkkolelu.view.DrawPanel;

/**
 *
 * @author ahathoor
 */
public class CreateTool implements MouseListener{

    private DrawPanel ikkuna;

    public CreateTool(DrawPanel i) {
        this.ikkuna = i;
    }
    
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ikkuna.addNode(e.getPoint());
        ikkuna.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
