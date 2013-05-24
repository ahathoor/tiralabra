/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools;

import verkkolelu.view.MainFrame;

/**
 * Interface for the tools for the VerkkoLelu program
 * @author Mikko Tamminen
 */
public interface Tool{
    public String getName();
    public void select(MainFrame mf);
    public void deselect(MainFrame mf);
}
