/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools.commandable;

import verkkolelu.tools.Tool;

/**
 *
 * @author mikko
 */
public interface CommandableTool extends Tool{
    public void command(ToolCommand tc);
    public ToolCommand[] getCommands();
}
