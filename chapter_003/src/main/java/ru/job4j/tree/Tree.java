package ru.job4j.tree;

import java.util.*;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    private Node<E> root;

    private int modCount;

    private int size;

    public int getSize() {
        return size;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> searchParent;
        Optional<Node<E>> searchChild;
        if (root == null) {
            root = new Node<>(parent);
            root.add(new Node<>(child));
            modCount++;
            size = 1;
            result = true;
        } else {
            searchParent = this.findBy(parent);
            searchChild = this.findBy(child);
            if (searchParent.isPresent() && !searchChild.isPresent()) {
                searchParent.get().add(new Node<>(child));
                modCount++;
                size++;
                result = true;
            }
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Queue<Node<E>> data = new LinkedList<>();
            int expectedCount = modCount;
            boolean r = data.offer(Tree.this.root);

            @Override
            public boolean hasNext() {
                return !data.isEmpty();
            }

            @Override
            public E next() {
                checkForModification();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> result = data.poll();
                for (Node<E> element : result.leaves()) {
                    data.offer(element);
                }
                return result.getValue();
            }

            final void checkForModification() {
                if (modCount != expectedCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
