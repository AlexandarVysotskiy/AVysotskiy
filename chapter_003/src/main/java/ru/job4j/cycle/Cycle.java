package ru.job4j.cycle;

public class Cycle<E> {
    /**
     * Метод проверяет имеет ли спиоок цикличность.
     * @param first - элемент с которого идет проверка на цикличность.
     * @return true если список замыкается.
     */
    public boolean findCycle(Node<E> first) {
        boolean result = false;
        if (first != null) {
            Node<E> slow = first;
            Node<E> fast = first;
            while (!result & slow != null & fast != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (slow.equals(fast)) {
                    result = true;
                }
            }
        }
        return result;
    }

    static class Node<E> {
        E item;
        Node<E> next;

        Node(E item) {
            this.item = item;
        }
    }
}
