package ru.job4j.set;

import ru.job4j.dynamic.DynamicArray;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleSet<E> implements Iterable {
    private DynamicArray<E> list = new DynamicArray();
    private int index = 0;
    private int modCount = 0;

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
                break;
            }
        }
        return result;
    }

    public void add(E element) {
        if (list.isEmpty() || findDuplicates(element)) {
            this.modCount++;
            list.add(element);
        }
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return modCount > index;
            }

            @Override
            public E next() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException("this collection has undergone a change");
                }
                if (!hasNext()) {
                    throw new NoSuchElementException("no such element");
                }
                return list.get(index++);
            }
        };
    }
}