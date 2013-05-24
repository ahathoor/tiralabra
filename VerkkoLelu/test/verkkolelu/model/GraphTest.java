/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.model;

import java.awt.Color;
import java.awt.Point;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 *
 * @author mikko
 */
public class GraphTest {
    
    public GraphTest() {
    }

    Graph test;
    @Before
    public void setUp(){
        test = new Graph();
    }

    @Test
    public void testEmpty() {
        test.addNode(new Point(0,0));
        test.empty();
        assertEquals(0, test.getNodes().size());
    }

    @Test
    public void testSaveToString() {
        assertEquals("<graphInternal>", test.saveToString());
        Node n1 = test.addNode(new Point(0,0));
        Node n2 = test.addNode(new Point(1,1));
        n1.setSign("A");
        n2.setSign("B");
        n1.setColor(new Color(0x000000));
        n2.setColor(new Color(0xFFFFFF));
        test.linkNodes(n1, n2, 0);
        System.out.println(test.saveToString());
        assertEquals("0<nodeInternal>0<nodeInternal>0<nodeInternal>A<nodeInternal>-16777216<node>1<nodeInternal>1<nodeInternal>1<nodeInternal>B<nodeInternal>-1<node><graphInternal>0<edgeInternal>1<edgeInternal>0<edge>", test.saveToString()); }

    @Test
    public void testLoadFromString() {
        test.loadFromString("0<nodeInternal>0<nodeInternal>0<nodeInternal>A<nodeInternal>-16777216<node>1<nodeInternal>1<nodeInternal>1<nodeInternal>B<nodeInternal>-1<node><graphInternal>0<edgeInternal>1<edgeInternal>0<edge>");
        Node n1 = test.getNodes().get(0);
        Node n2 = test.getNodes().get(1);
        assertEquals(new Color(0x000000), n1.getColor());
        assertEquals(new Color(0xFFFFFF), n2.getColor());
        assertEquals(new Point(0,0), n1.getPoint());
        assertEquals(new Point(1,1), n2.getPoint());
        assertEquals("A", n1.getSign());
        assertEquals("B", n2.getSign());
    }

    @Test
    public void testNodeCount() {
        for (int i = 0; i < 1000; i++) {
            assertEquals(i, test.nodeCount());
            test.addNode(new Point(0,0));
        }
    }

    @Test
    public void testGetNodes() {
        Node[] nodes = new Node[100];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = test.addNode(new Point(i,i));
        }
        for (int i = 0; i < nodes.length; i++) {
            assertEquals(nodes[i], test.getNodes().get(i));
        }
    }
    
    @Test
    public void testNodeNearPoint() {
        Node n1 = test.addNode(new Point(0,0));
        Node n2 = test.addNode(new Point(100,100));
        assertEquals(n1, test.nodeNearPoint(new Point(0,0)));
        assertEquals(n1, test.nodeNearPoint(new Point(5,0)));
        assertEquals(n1, test.nodeNearPoint(new Point(0,5)));
        
        assertEquals(n2, test.nodeNearPoint(new Point(100,100)));
    }

    @Test
    public void testGetEdgesFrom() {
        Node n1 = test.addNode(new Point(0,0));
        Node n2 = test.addNode(new Point(100,100));
        Node n3 = test.addNode(new Point(0,0));
        Node n4 = test.addNode(new Point(100,100));
        test.linkNodes(n1, n2, 1);
        test.linkNodes(n1, n3, 1);
        test.linkNodes(n1, n4, 1);
        assertEquals(n2, test.getEdgesFrom(n1).get(0).getNode2());
        assertEquals(n3, test.getEdgesFrom(n1).get(1).getNode2());
        assertEquals(n4, test.getEdgesFrom(n1).get(2).getNode2());
    }

    @Test
    public void testCrossLinkNodes() {
        Node n1 = test.addNode(new Point(0,0));
        Node n2 = test.addNode(new Point(100,100));
        test.crossLinkNodes(n1, n2, 10);
        assertEquals(n2, test.getEdgesFrom(n1).get(0).getNode2());
        assertEquals(n1, test.getEdgesFrom(n2).get(0).getNode2());
        assertEquals(10, test.getEdgesFrom(n1).get(0).getWeight());
        assertEquals(10, test.getEdgesFrom(n2).get(0).getWeight());
        
    }

    @Test
    public void testLinkNodes() {
        Node n1 = test.addNode(new Point(0,0));
        Node n2 = test.addNode(new Point(100,100));
        test.linkNodes(n1, n2, 10);
        assertEquals(n2, test.getEdgesFrom(n1).get(0).getNode2());
        assertEquals(10, test.getEdgesFrom(n1).get(0).getWeight());
    }

    @Test
    public void testDeleteNode() {
        Node n1 = test.addNode(new Point(0,0));
        Node n2 = test.addNode(new Point(100,100));
        test.deleteNode(n2);
        assertEquals(1,test.nodeCount());
    }

    private class ListenStub implements GraphChangeListener {
        public int calls = 0;

        @Override
        public void graphChanged() {
            calls++;
        }
       
    }
    @Test
    public void testNotifyListeners() {
        ListenStub ls = new ListenStub();
        test.addListener(ls);
        Node n1 = test.addNode(new Point(0,0));
        Node n2 = test.addNode(new Point(0,0));
        test.addNode(new Point(0,0));
        assertEquals(3, ls.calls);
        test.addNode(new Point(0,0)).setColor(Color.yellow);
        test.addNode(new Point(0,0)).setLabel("");
        test.addNode(new Point(0,0)).setSign(null);
        test.addNode(new Point(0,0)).setPoint(new Point(100,100));
        assertEquals(3+8, ls.calls);
        test.deleteNode(test.nodeNearPoint(new Point(100,100)));
        assertEquals(3+8+1, ls.calls);
        test.linkNodes(n1, n2, 1);
        assertEquals(3+8+1+1, ls.calls);
        
        test.removeListener(ls);
        
        test.addNode(new Point(0,0)).setColor(Color.yellow);
        test.addNode(new Point(0,0)).setLabel("");
        test.addNode(new Point(0,0)).setSign(null);
        test.addNode(new Point(0,0)).setPoint(new Point(100,100));
        assertEquals(3+8+1+1, ls.calls);
    }
}