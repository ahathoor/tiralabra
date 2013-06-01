/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools.dijkstra;

import verkkolelu.tools.commandable.ToolCommand;

/**
 * Possible commands for the Dijkstra tool
 * @author ahathoor
 */
public enum DijkstraCommand implements ToolCommand{
    
    /**
     * Initialize the Dijkstra
     */
    INIT("Initialize"),
    /**
     * Step the algorithm forward
     */
    STEP("Step forward"),
    /**
     * Select the start node
     */
    SELECT_START("Select Startnode"),
    /**
     * Reset the algorithm
     */
    RESET("Reset");
    
    private final String name;

    private DijkstraCommand(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
