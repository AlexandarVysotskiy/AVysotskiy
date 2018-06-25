package ru.job4j.set;

import ru.job4j.dynamic.DynamicArray;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleSet<E> implements Iterable {
    private DynamicArray<E> list = new DynamicArray();
    private int index = 0;
    private int count = 0;

    /**
     * Метод проверяет наличие добавляемого елемента в коллекции.
     *
     * @param element - добавляемый элемент.
     * @return - true если элемента в коллеции нет.
     */
    private boolean findDuplicates(E element) {
        boolean result = true;
        for (Object index : list) {
            if (index.equals(element)) {
                result = false;
            }
        }
        return result;
    }

    public void add(E element) {
        if (list.isEmpty()) {
            count++;
            list.add(element);
        } else {
            if (findDuplicates(element)) {
                count++;
                list.add(element);
            }
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int modCount = count;

            @Override
            public boolean hasNext() {
                return count > index;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("no such element");
                }
                if (modCount != count) {
                    throw new ConcurrentModificationException("this collection has undergone a change");
                }
                return list.get(index++);
            }
        };
    }
}