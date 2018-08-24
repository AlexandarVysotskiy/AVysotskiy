package ru.job4j.dynamic;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Динамический список на базе массива.
 *
 * @param <E> - тип элементов.
 */

@ThreadSafe
public class DynamicArray<E> implements Iterable<E> {

    @GuardedBy("this")
    private int index = 0;

    @GuardedBy("this")
    private int modCont = 0;

    @GuardedBy("this")
    private int size = 10;

    @GuardedBy("this")
    private int count = 0;

    @GuardedBy("this")
    private Object[] container = new Object[size];

    /**
     * Метод добавляет новые элементы в массив.
     *
     * @param value - добавляемое значение.
     */
    public synchronized void add(E value) {
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
    public synchronized E get(int getIndex) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        return (E) this.container[getIndex];
    }

    /**
     * Вспомогательный метод, служит для расширения массива.
     *
     * @param newSize - размер нового массива.
     */
    private void reSize(int newSize) {
        this.modCont++;
        this.container = Arrays.copyOf(this.container, newSize);
    }

    public synchronized boolean isEmpty() {
        return index < 1;
    }

    @Override
    public synchronized Iterator<E> iterator() {
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