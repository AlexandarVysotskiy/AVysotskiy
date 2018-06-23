package ru.job4j.linked;

public class MyQueue<E> extends MyLinkedList {
    public void push(E element) {
        super.add(element);
    }

    public E poll() {
        return (E) super.deleteFirst();
    }
}
