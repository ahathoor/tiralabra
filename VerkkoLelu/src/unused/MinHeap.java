/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unused;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Minimum heap implementation. Must provide a type that extends Comparator
 *
 * @author mikko
 */
public class MinHeap<T> {

    T[] elements;
    Comparator<T> comp;
    int size = 0;

    /**
     * Creates a new Minimum heap.
     *
     * @param C class of the type of object used by this heap.
     * @throws IllegalArgumentException if C does not extend Comparable.
     */
    public MinHeap(Class C) {
        for (Class c : C.getInterfaces()) {
            if (c.equals(Comparable.class)) {
                break;
            }
            throw new IllegalArgumentException("Class " + C.getCanonicalName() + " does not implement Comparable");
        }

        comp = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return ((Comparable) o1).compareTo(((Comparable) o2));
            }
        };
    }

    /**
     *
     * @param comp comparator for the type of object used in the heap.
     */
    public MinHeap(Comparator<T> comp) {
        this.comp = comp;
    }

    public void add(T element) {
        if (size + 1 >= elements.length) {
            resizeArray();
        }
        size++;
        elements[size] = element;
        shuffleUp();
    }

    public void remove(T element) {
        size--;
    }

    /**
     * Returns and removes the smallest element.
     * @return 
     */
    public T pop() {
        if (size <= 0) {
            throw new IllegalStateException("Heap is empty");
        }
        T ret = elements[1];
        elements[1] = elements[size];
        size--;
        
        shuffleDown();

        return ret;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int parent(int i) {
        return i / 2;
    }

    private int left(int i) {
        return i * 2;
    }

    private int right(int i) {
        return i * 2 + 1;
    }

    private boolean hasParent(int i) {
        return i > 1;
    }

    private boolean hasLeft(int i) {
        return left(i) <= size;
    }

    private boolean hasRight(int i) {
        return right(i) <= size;
    }

    private void swap(int i1, int i2) {
        T swap = elements[i1];
        elements[i1] = elements[i2];
        elements[i2] = swap;
    }

    private void resizeArray() {
        Arrays.copyOf(elements, elements.length * 2);
    }

    private int compare(int i1, int i2) {
        return comp.compare(elements[i1], elements[i2]);
    }

    private void shuffleUp() {
        int index = size;
        while (hasParent(index) && compare(index, parent(index)) > 0) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private void shuffleDown() {
        int index = 1;
        while (hasLeft(index)) {
            int smaller = left(index);

            if (hasRight(index) && compare(left(index), right(index)) > 0) {
                smaller = right(index);
            }

            if (compare(index, smaller) > 0) {
                swap(index, smaller);
            } else {
                break;
            }
            index = smaller;
        }
    }
    
    /**
     * Returns the array of objects stored in the heap, in order.
     * @return 
     */
    public T[] toArray() {
        return elements;
    }
    
}
