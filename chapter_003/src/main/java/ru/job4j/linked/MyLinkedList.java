package ru.job4j.linked;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Контейнер на базе связанного списка.
 *
 * @param <E> тип добавляемых елементов.
 */
public class MyLinkedList<E> implements Iterable<E> {
    /**
     * Size - кол-во элементов в коллекии.
     */
    private int size = 0;

    /**
     * Счетчик модификаций коллекции.
     */
    private int modCount = 0;

    /**
     * Первый элемент в коллекции.
     */
    private Node<E> first;

    /**
     * Последний элемент в коллекции.
     */
    private Node<E> last;

    public MyLinkedList() {
    }

    /**
     * Метод служит для добаления элементов в коллекцию.
     *
     * @param element - добавляемый элемент.
     */
    public void add(E element) {
        linkLast(element);
        this.modCount++;
    }

    /**
     * Метод служит для получения элемента коллекции по индексу.
     *
     * @param index - индекс.
     * @return - элемент по индексу.
     * @throws IllegalArgumentException       - недопустимый или не соотвествующий параметр.
     * @throws ArrayIndexOutOfBoundsException - если индекс больше фактичесого.
     */
    public E get(int index) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        return node(index).item;
    }

    /**
     * Возвращает элемент Node по указоному индексу.
     *
     * @param index - индекс.
     * @return возвращаемый элемент.
     */
    private Node<E> node(int index) {
        Node<E> entryNode;
        if (index < (this.size >> 1)) {
            entryNode = this.first;
            for (int i = 0; i < index; i++) {
                entryNode = entryNode.next;
            }
        } else {
            entryNode = this.last;
            for (int i = this.size - 1; i > index; i--) {
                entryNode = entryNode.prev;
            }
        }
        return entryNode;
    }

    private void linkLast(E element) {
        final Node<E> lastNode = this.last;
        final Node<E> newNode = new Node<>(lastNode, element, null);
        this.last = newNode;
        if (lastNode != null) {
            lastNode.next = newNode;
        } else {
            this.first = newNode;
        }
        size++;
    }

    public E deleteFirst() {
        if (this.size < 1) {
            throw new NoSuchElementException();
        }
        Node<E> result = this.first;
        this.first.prev = null;
        this.first = this.first.next;
        this.size--;
        this.modCount++;
        return result.item;
    }

    public E deleteLast() {
        if (this.size < 1) {
            throw new NoSuchElementException();
        }
        Node<E> result = this.last;
        this.last.next = null;
        this.last = this.last.prev;
        this.size--;
        this.modCount++;
        return result.item;
    }

    private static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.item = element;
            this.next = next;
        }
    }

    public int size() {
        return this.size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return first != null;
            }

            @Override
            public E next() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException("this collection has undergone a change");
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E item = first.item;
                first = first.next;
                return item;
            }
        };
    }
}