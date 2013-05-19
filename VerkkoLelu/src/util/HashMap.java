/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
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

    private void fillWithLists() {
        for (int i = 0; i < values.length; i++) {
            values[i] = new ArrayList<>();
        }
    }

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

//    private boolean containsKey(T0 key) {
//        int hash = hash(key);
//        for (int i = 0; i < values[hash].size(); i++) {
//            Entry<T0, T1> entry = values[hash].get(i);
//            if (entry.key == key) {
//                return true;
//            }
//        }
//        return false;
//    }

    public void put(T0 key, T1 value) {
        int hash = hash(key);
        
        remove(key);
        values[hash].add(new Entry(key, value));
        entryCount++;
        if ((double) tableSize / (double) entryCount < EXPANSION_F) {
            expand();
        }
    }

    private int hash(T0 key) {
        return key.hashCode() % tableSize;
    }

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
