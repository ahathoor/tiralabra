/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.util;

import java.util.Iterator;

/**
 *
 * @author ahathoor
 */
public class ArrayList<T0> implements Iterable<T0> {

    @Override
    public Iterator<T0> iterator() {
        return new ArrayListIterator();
    }

    /**
     * Copies the elements to the given array and returns the array.
     *
     * @param array
     * @return
     */
    public T0[] toArray(T0[] array) {
        for (int i = 0; i < array.length && i < elements.length; i++) {
            array[i] = elements[i];
        }
        return array;
    }

    /**
     * Returns the index of the given node.
     *
     * @param searched the node whose index is to be returned.
     * @return The index of the given node. -1 if not found.
     */
    public int indexOf(T0 searched) {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == searched) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Iterator for the Array List.
     */
    private class ArrayListIterator implements Iterator<T0> {

        private int cursor;

        public ArrayListIterator() {
            this.cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return this.cursor != ArrayList.this.count;
        }

        @Override
        public T0 next() {
            T0 ret = ArrayList.this.elements[cursor];
            cursor++;
            return ret;
        }

        @Override
        public void remove() {
            ArrayList.this.removeIndex(cursor);
        }
    }
    private T0[] elements;
    private int size;
    private int count;

    public ArrayList() {
        size = 1;
        count = 0;
        elements = (T0[]) new Object[size];
    }

    /**
     * returns the number of elements in this list.
     *
     * @return
     */
    public int size() {
        return count;
    }

    /**
     * Adds an element to the list.
     *
     * @param element
     */
    public void add(T0 element) {
        if (count + 1 > size) {
            T0[] ecopy = (T0[]) new Object[size * 2];
            System.arraycopy(elements, 0, ecopy, 0, elements.length);
            size *= 2;
            elements = ecopy;
        }
        elements[count] = element;
        count++;
    }

    /**
     * Returns the element with the given index.
     *
     * @param index
     * @return
     */
    public T0 get(int index) {
        return elements[index];
    }

    /**
     * Returns true if there are zero elements.
     *
     * @return
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Removes the element at the given index from the list.
     *
     * @param index
     */
    public void removeIndex(int index) {
        if (index >= elements.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = index; i < elements.length - 1; i++) {
            elements[i] = elements[i + 1];
        }
        count--;
    }

    /**
     * Removes the given element from the list.
     *
     * @param element
     */
    public void remove(T0 element) {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null && elements[i].equals(element)) {
                removeIndex(i);
                return;
            }
        }
    }
}
