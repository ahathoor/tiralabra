/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools.astar;

import verkkolelu.model.Graph;
import verkkolelu.tools.commandable.CommandableTool;
import verkkolelu.tools.commandable.ToolCommand;
import verkkolelu.util.ArrayList;
import verkkolelu.view.MainFrame;

/**
 *
 * @author mikko
 */
public class AStarTool implements CommandableTool {

    private Graph graph;
    private AStarWindow aw;

    public AStarTool(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void command(ToolCommand tc) {
    }

    @Override
    public ToolCommand[] getCommands() {
        return AStarCommand.values();
    }

    protected enum AStarCommand implements ToolCommand {

        SELECT_START("Select Start node");
        private String name;

        private AStarCommand(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }
    }

    protected enum AStarState {
        SELECTING_STARTNODE;
    }

    private void step() {
    }

    @Override
    public String getName() {
        return "A Star";
    }

    @Override
    public void select(MainFrame mf) {
        if (aw == null) {
            aw = new AStarWindow(mf, this);
        }
        aw.setVisible(true);
    }

    @Override
    public void deselect(MainFrame mf) {
        aw.setVisible(false);
    }
}
