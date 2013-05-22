/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools.dijkstra;

/**
 * Possible commands for the Dijkstra tool
 * @author ahathoor
 */
public enum DijkstraCommand {
    
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
     * Select the end node
     */
    SELECT_END("Select Endnode"),
    /**
     * Reset the algorithm
     */
    RESET("Reset");
    
    public final String NAME;

    private DijkstraCommand(String name) {
        this.NAME = name;
    }
}
