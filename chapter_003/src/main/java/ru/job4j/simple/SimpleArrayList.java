package ru.job4j.simple;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.dynamic.DynamicArray;

import java.awt.*;
import java.util.Iterator;

/**
 * Класс SimpleArrayList.
 */
@ThreadSafe
public class SimpleArrayList<E> implements Iterable<E> {

    @GuardedBy("this")
    private int size;

    @GuardedBy("this")
    private Node<E> first;

    @GuardedBy("this")
    private DynamicArray<E> arrayList;

    /**
     * Метод вставляет в начало списка данные.
     */
    public synchronized void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Метод удаляет первый элемент в списке.
     */
    public synchronized E delete() {
        Node<E> result = this.first;
        this.first = this.first.next;
        size--;
        return result.date;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public synchronized E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Метод получения размера коллекции.
     */
    public synchronized int getSize() {
        return this.size;
    }

    private synchronized DynamicArray<E> copy(DynamicArray<E> dc) {
        DynamicArray<E> result = new DynamicArray<>();
        for (E element : dc) {
            result.add(element);
        }
        return result;
    }

    @Override
    public synchronized Iterator<E> iterator() {
        return this.copy(this.arrayList).iterator();
    }


    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {

        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }
}