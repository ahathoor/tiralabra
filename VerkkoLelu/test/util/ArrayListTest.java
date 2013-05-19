/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ahathoor
 */
public class ArrayListTest {

    public ArrayListTest() {
    }

    @Before
    public void setUp() {
        test = new ArrayList<>();
    }
    ArrayList<Integer> test;

    @Test
    public void addingOneMakesSizeOne() {
        test.add(10);
        assertEquals(1, test.size());
    }
    
    @Test
    public void addRemoveAdd() {
        test.add(10);
        test.remove(new Integer(10));
        test.add(10);
        assertEquals(10, (int) test.get(0));
    }

    @Test
    public void addingTwoMakesSizeTwo() {
        test.add(10);
        test.add(10);
        assertEquals(2, test.size());
    }

    @Test
    public void addingNMakesSizeN() {
        for (int i = 0; i < 100; i++) {
            test.add(10);
            assertEquals(i + 1, test.size());
        }
    }

    @Test
    public void testGet() {
        for (int i = 0; i < 10; i++) {
            test.add(i);
        }
        for (int i = 0; i < 10; i++) {
            assertEquals(i, (int) test.get(i));
        }
    }

    @Test
    public void testIsEmpty() {
        assertTrue(test.isEmpty());
        test.add(0);
        assertFalse(test.isEmpty());
    }

    @Test
    public void removeIndexDecreasesSize() {
        test.add(10);
        test.removeIndex(0);
        assertEquals(0, test.size());
    }
    
    @Test
    public void removeDecreasesSize() {
        test.add(10);
        test.remove(10);
        assertEquals(0, test.size());
    }
    @Test
    public void add1000remove1000() {
        for (int i = 0; i < 1000; i++) {
            test.add(i);
        }
        for (int i = 0; i < 1000; i++) {
            test.remove(i);
        }
        assertEquals(0, test.size());
    }

    @Test
    public void removePullsIndicesBack() {
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        test.remove(2);
        assertEquals(4, (int) test.get(3));
    }
    
    @Test
    public void removeUnexistingKeyDoesNothing() {
        test.remove(new Integer(20));
        test.remove(new Integer(20));
        test.remove(new Integer(20));
        test.remove(new Integer(20));
        test.remove(new Integer(20));
        test.remove(new Integer(20));
        test.add(100);
        test.remove(new Integer(20));
        test.remove(new Integer(20));
        test.remove(new Integer(20));
        test.remove(new Integer(20));
        test.remove(new Integer(20));
        test.remove(new Integer(20));
        assertEquals(1, test.size());
    }   
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void removeUnexistingIndexThrowsException() {
        test.add(100);
        test.removeIndex(44);
    }
}