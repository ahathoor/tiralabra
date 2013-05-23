/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.util;

import verkkolelu.util.ArrayList;
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

    @Test
    public void testIterator() {
        for (int i = 0; i < 10; i++) {
            test.add(i);
        }
        int i = 0;
        for (Integer integer : test) {
            assertEquals(i,(int) integer);
            i++;
        }
    }

    @Test
    public void testToArray() {
        for (int i = 0; i < 10; i++) {
            test.add(i);
        }
        Integer[] array = test.toArray(new Integer[10]);

        for (int i = 0; i < array.length; i++) {
            assertEquals(i, (int) array[i]);
        }
    }

    @Test
    public void testToArraySmallerCopy() {
        for (int i = 5; i < 15; i++) {
            test.add(i);
        }
        Integer[] array = test.toArray(new Integer[5]);

        for (int i = 0; i < array.length; i++) {
            assertEquals(i + 5, (int) array[i]);
        }
    }

    @Test
    public void testToArrayBiggerCopy() {
        for (int i = 0; i < 15; i++) {
            test.add(i);
        }
        Integer[] array = test.toArray(new Integer[15]);

        for (int i = 0; i < array.length; i++) {
            if (i < test.size()) {
                assertEquals(i, (int) array[i]);
            } else {
                assertNull(array[i]);
            }
        }
    }

    @Test
    public void testIndexOf() {
        test.add(20);
        test.add(30);
        test.add(40);
        assertEquals(2, test.indexOf(40));
    }

    @Test
    public void testSizeNoAdd() {
        assertEquals(0, test.size());
    }

    @Test
    public void testManyAdd() {
        for (int i = 0; i < 100; i++) {
            assertEquals(i, test.size());
            test.add(i);
        }
    }

    @Test
    public void testSizeAddRemove() {
        for (int i = 0; i < 10; i++) {
            test.add(i);
        }
        for (int i = 0; i < 10; i++) {
            test.remove(i);
        }
        assertEquals(0, test.size());
    }
}