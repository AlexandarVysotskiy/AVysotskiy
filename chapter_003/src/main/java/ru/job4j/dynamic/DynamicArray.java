package ru.job4j.dynamic;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Динамический список на базе массива.
 *
 * @param <E> - тип элементов.
 */
public class DynamicArray<E> implements Iterable<E> {
    private int index = 0;
    private int modCont = 0;
    private int size = 10;
    private int count = 0;
    private Object[] container = new Object[size];

    /**
     * Метод добавляет новые элементы в массив.
     *
     * @param value - добавляемое значение.
     */
    public void add(E value) {
        if (index == container.length - 1) {
            reSize((this.container.length * 3) / 2 + 1);
        }
        this.container[index++] = value;
    }

    /**
     * Метод возращает значение по индеку.
     *
     * @param getIndex - индекс.
     * @return - значение индека.
     * @throws IllegalArgumentException
     * @throws ArrayIndexOutOfBoundsException
     */
    public E get(int getIndex) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        return (E) this.container[getIndex];
    }

    /**
     * Вспомогательный метод, служит для рсширения массива.
     *
     * @param newSize - размер нового массива.
     */
    private void reSize(int newSize) {
        this.modCont++;
        this.container = Arrays.copyOf(this.container, newSize);
    }

    public boolean isEmpty() {
        return index < 1;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            @Override
            public boolean hasNext() {
                return index > count;
            }

            @Override
            public E next() {
                if (modCont != 0) {
                    throw new ConcurrentModificationException("this collection has undergone a change");
                }
                if (!hasNext()) {
                    throw new NoSuchElementException("no such element");
                }
                return (E) container[count++];
            }
        };
    }
}