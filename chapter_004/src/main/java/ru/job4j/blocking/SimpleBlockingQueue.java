package ru.job4j.blocking;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Класс состоит их двух методов:
 * Первый добавляет элементы в коллекцию если очередь не полна.
 * Второй извлекает элементы из коллекции если очередь не полна.
 */
@ThreadSafe
public class SimpleBlockingQueue<E> {

    @GuardedBy("this")
    private Queue<E> queue = new LinkedList<>();

    @GuardedBy("this")
    private E size;

    public SimpleBlockingQueue(E size) {
        this.size = size;
    }

    public synchronized void offer(E value) {
        if (queue.size() > (int) size) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Исключения типа InterruptedException перехвачено");
            }
        } else {
            queue.offer(value);
            notify();
        }
    }

    public synchronized E poll() {
        E result = null;
        if (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Исключения типа InterruptedException перехвачено");
            }
        } else {
            result = queue.poll();
            notify();
        }
        return result;
    }

    public synchronized int getSizeQueue() {
        return queue.size();
    }
}