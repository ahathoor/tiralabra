/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools;

import java.awt.event.MouseListener;
import verkkolelu.view.DrawPanel;

/**
 * Interface for the tools for the VerkkoLelu program
 * @author Mikko Tamminen
 */
public interface Tool{
    public String getName();
    public void select(DrawPanel p);
    public void deselect(DrawPanel p);
}
