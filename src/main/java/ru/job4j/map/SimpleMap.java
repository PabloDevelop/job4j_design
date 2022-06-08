package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        boolean rsl = false;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            modCount++;
            count++;
            rsl = true;
        }
        return rsl;
    }

    private int hash(int hashCode) {
       return (hashCode == 0) ? 0 : (hashCode ^ (hashCode >>> 16));
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTab = new MapEntry[capacity];
        for (MapEntry<K, V> map : table) {
            if (map != null) {
                int index = indexFor(hash(map.key.hashCode()));
                newTab[index] = new MapEntry<>(map.key, map.value);
            }
        }
        table = newTab;
    }

    /**Метод get() в случае отсутствия значения должен возвращать null,
     * в противном случае само значение.
     * @param key
     * @return value
     */
    @Override
    public V get(K key) {
        V rsl = null;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null && table[index].key.hashCode() == key.hashCode()
                && table[index].key.equals(key)) {
            rsl = table[index].value;
        }
        return rsl;
    }

    /**Метод remove() в случае успешного удаления должен возвращать true,
     * в противном случае false.
     * @param key
     * @return
     */
    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null && table[index].key.hashCode() == key.hashCode()
                && table[index].key.equals(key)) {
            table[index] = null;
            modCount++;
            count--;
            rsl = true;
        }
        return rsl;
    }

    /**Итератор должен обладать fail-fast поведением
     * @return
     */
    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int cursor;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (cursor < capacity && table[cursor] == null) {
                    cursor++;
                }
                return cursor < capacity && table[cursor] != null;
            }

            @Override
            public K next() {
                return table[cursor++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}