/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.model;

/**
 * A listener for Graph changes
 * @author Mikko Tamminen
 */
public interface GraphChangeListener {
    /**
     * Graph calls this on all it's listeners when it changes.
     */
    public void graphChanged();
}
