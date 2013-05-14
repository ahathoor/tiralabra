/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools.dijkstra;

import java.awt.Point;
import java.util.HashMap;
import java.util.PriorityQueue;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import verkkolelu.model.Graph;
import verkkolelu.model.Node;

/**
 *
 * @author ahathoor
 */
public class NodeComparatorTest {
    private class MockNode extends Node {

        public MockNode() {
            super(new Point(0,0), new Graph());
        }
    
    }
    public NodeComparatorTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testCompare() {   
        HashMap<Node, Integer> dist = new HashMap<>();
        PriorityQueue<Node> Q = new PriorityQueue<>(2, new NodeComparator(dist));
        
        Node n = new MockNode();
        n.setLabel("eka");
        Node nn = new MockNode();
        nn.setLabel("toka");
        dist.put(n, 10);
        dist.put(nn, 100);
        Q.add(n);
        Q.add(nn);
        assertEquals("eka", Q.poll().getLabel());
        assertEquals("toka", Q.poll().getLabel());
    }
}