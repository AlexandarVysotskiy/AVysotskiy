package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Iterable<SimpleMap.Note<K, V>> {
    private Note<K, V>[] table;

    private final float loadFactor = 0.75f;

    private int threshold = 12;

    private int size = 0;

    private int modCount = 0;

    /**
     * Эта переменная нужна для метода Next.
     */
    private int countForNext = 0;

    /**
     * Эта переменная нужна для HashNext.
     */
    private int countHash = 0;

    public SimpleMap() {
        this.table = new Note[16];
    }

    public boolean insert(K key, V value) {
        boolean rst = false;
        if (key != null) {
            int index = getIndex(key);
            if (table[index] == null) {
                this.table[index] = new Note<>(key, value);
                this.modCount++;
                rst = true;
                if (++this.size > this.threshold) {
                    resize(this.table);
                }
            }
        } else if (this.table[0] == null) {
            this.table[0] = new Note<>(key, value);
            this.modCount++;
            rst = true;
            if (++this.size > this.threshold) {
                resize(this.table);
            }
        }
        return rst;
    }

    private void resize(Note<K, V>[] array) {
        Note<K, V>[] oldArray = array;
        for (Note<K, V> index : oldArray) {
            insert(index.key, index.value);
        }
        this.threshold = (int) (array.length * loadFactor);
    }

    private int getIndex(K key) {
        return indexFor(key.hashCode(), this.table.length);
    }

    private int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    public V get(K key) {
        V value = null;
        if (key == null & this.table[0] != null) {
            value = this.table[0].value;
        } else {
            int index = this.getIndex(key);
            if (this.table[index].key.equals(key)) {
                value = this.table[index].value;
            }
        }
        return value;
    }

    public boolean delete(K key) {
        boolean result = false;
        int index = getIndex(key);
        if (this.table[index] != null) {
            if (key == null & this.table[index] != null) {
                this.table[0] = null;
                this.modCount++;
                this.size--;
                result = true;
            } else {
                if (table[index].key.equals(key)) {
                    this.table[index] = null;
                    modCount--;
                    size--;
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    public Iterator<SimpleMap.Note<K, V>> iterator() {
        return new Iterator<SimpleMap.Note<K, V>>() {
            int expModCount = modCount;

            @Override
            public boolean hasNext() {
                return size > countHash;
            }

            @Override
            public SimpleMap.Note<K, V> next() {
                countHash++;
                if (expModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Note<K, V> result = null;
                for (; countForNext <= table.length; countForNext++) {
                    if (table[countForNext] != null) {
                        result = table[countForNext++];
                        break;
                    }
                }
                return result;
            }
        };
    }

    static class Note<K, V> {
        final K key;
        V value;

        public Note(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
