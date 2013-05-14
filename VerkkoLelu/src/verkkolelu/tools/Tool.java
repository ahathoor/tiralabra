/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools;

import java.awt.event.MouseListener;

/**
 *
 * @author ahathoor
 */
public interface Tool extends MouseListener {
    //TODO Tools depend on Graph not panel
    public void select();
    public void deselect();
}
