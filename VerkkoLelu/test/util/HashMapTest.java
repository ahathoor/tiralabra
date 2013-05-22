/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import verkkolelu.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ahathoor
 */
public class HashMapTest {

    public HashMapTest() {
    }
    HashMap<Integer, String> test;

    @Before
    public void setUp() {
        test = new HashMap<>();
    }

    @Test
    public void testPutOneGetOne() {
        test.put(1, "1");
        assertEquals("1", test.get(1));
    }

    @Test
    public void testPut100Get100() {
        for (int i = 0; i < 100; i++) {
            test.put(i, "" + i);
        }
        for (int i = 0; i < 100; i++) {
            assertEquals("" + i, test.get(i));
        }
    }

    @Test
    public void testRemove() {
        test.put(10, "test");
        test.remove(10);
        assertNull(test.get(10));
    }

    @Test
    public void addingTwoWithSameKeyB() {
        HashMap<String,String> test = new HashMap<>();
        test.put("a", "A");
        test.put("a", "B");
        assertEquals("B", test.get("a"));
    }

    @Test
    public void addingTwoWithSameKey() {
        test.put(10, "A");
        test.put(10, "B");
        assertEquals("B", test.get(10));
    }

    @Test
    public void adding100DifferentOnlyLastRemains() {
        for (int i = 0; i < 100; i++) {
            test.put(0, "" + i);
        }
        assertEquals("99", test.get(0));
    }

    @Test
    public void add100remove99() {
        for (int i = 0; i < 100; i++) {
            test.put(i, "" + i);
        }
        for (int i = 0; i < 99; i++) {
            test.remove(i);
        }
        for (int i = 0; i < 100; i++) {
            if (i != 99) {
                assertNull(test.get(i));
            } else {
                assertEquals("99", test.get(i));
            }
        }
    }
}