/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools.dijkstra;

import java.awt.event.MouseEvent;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import verkkolelu.model.Node;

/**
 *
 * @author ahathoor
 */
public class DijkstraToolTest {
    
    public DijkstraToolTest() {
    }
    DijkstraTool dt;
    @Before
    public void setUp() {
        dt = new DijkstraTool(null);
    }
}