/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools;

import java.awt.event.MouseListener;

/**
 * Interface for the tools for the VerkkoLelu program
 * @author Mikko Tamminen
 */
public interface Tool extends MouseListener {
    public void select();
    public void deselect();
}
