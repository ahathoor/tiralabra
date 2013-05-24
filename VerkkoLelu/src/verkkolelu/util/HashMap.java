/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.util;

/**
 * A hashmap.
 * @author ahathoor
 */
public class HashMap<T0, T1> {

    private class Entry<T0, T1> {

        private T0 key;
        private T1 value;

        public Entry(T0 key, T1 value) {
            this.key = key;
            this.value = value;
        }

        public T0 getKey() {
            return key;
        }

        public T1 getValue() {
            return value;
        }

        public void setKey(T0 key) {
            this.key = key;
        }

        public void setValue(T1 value) {
            this.value = value;
        }
    }
    
    ArrayList<Entry<T0, T1>>[] values;
    double LOADFACTOR = 0.7;
    double EXPANSION_F = 2;
    final int DSIZE = 50;
    int tableSize;
    int entryCount;

    public HashMap() {
        tableSize = DSIZE;
        entryCount = 0;
        values = (ArrayList<Entry<T0, T1>>[]) new ArrayList[tableSize];
        fillWithLists();
    }

    /**
     * Creates empty lists for all slots in the map
     */
    private void fillWithLists() {
        for (int i = 0; i < values.length; i++) {
            values[i] = new ArrayList<>();
        }
    }

    /**
     * Expands the map to fit more values.
     */
    private void expand() {
        tableSize *= EXPANSION_F;
        ArrayList<Entry<T0, T1>>[] oldValues = values;
        values = (ArrayList<Entry<T0, T1>>[]) new ArrayList[tableSize];
        fillWithLists();

        //Iterate throught the old entries and readd them into the new table
        for (int i = 0; i < oldValues.length; i++) {
            ArrayList<Entry<T0, T1>> entries = oldValues[i];
            for (int j = 0; j < entries.size(); j++) {
                Entry<T0, T1> entry = entries.get(j);
                this.put(entry.key, entry.value);
            }

        }
    }

    /**
     * Puts a new value to the map. O(1) time complexity.
     * @param key
     * @param value 
     */
    public void put(T0 key, T1 value) {
        int hash = hash(key);
        
        remove(key);
        values[hash].add(new Entry(key, value));
        entryCount++;
        if ((double) tableSize / (double) entryCount < EXPANSION_F) {
            expand();
        }
    }

    /**
     * Hash value used for the map.
     * @param key
     * @return 
     */
    private int hash(T0 key) {
        return key.hashCode() % tableSize;
    }

    /**
     * Removes the entry for the given key. O(1) time.
     * @param keyToRemove 
     */
    public void remove(T0 keyToRemove) {
        int hash = hash(keyToRemove);
        ArrayList<Entry<T0, T1>> entriesForKey = values[hash];
        for (int i = 0; i < entriesForKey.size(); i++) {
            Entry<T0, T1> entry = entriesForKey.get(i);
            if (entry.key == keyToRemove) {
                entriesForKey.remove(entry);
            }
        }
    }

    /**
     * Returns the entry for the given key.
     * @param key
     * @return Value for key, null if not found.
     */
    public T1 get(T0 key) {
        int hash = hash(key);
        for (int i = 0; i < values[hash].size(); i++) {
            Entry<T0, T1> entry = values[hash].get(i);
            if (entry.key == key) {
                return entry.value;
            }
        }
        return null;
    }
}
