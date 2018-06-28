package ru.job4j.set;

import ru.job4j.linked.MyLinkedList;

import java.util.*;

/**
 * Set реализованный на связном списке.
 *
 * @param <E> тип элементов.
 */
public class SimpleSetOfLinkedList<E> implements Iterable<E> {
    private MyLinkedList<E> list = new MyLinkedList<>();

    public boolean add(E element) {
        boolean result = true;
        for (int index = 0; index < this.list.size(); index++) {
            if (element == null ? this.list.get(index) == null : element.equals(this.list.get(index))) {
                result = false;
                break;
            }
        }
        if (result) {
            this.list.add(element);
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return this.list.iterator();
    }
}