package ru.job4j.convert;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Converter class
 *
 * @author Aleksandr Vysotskiy
 * @verion 1.0
 * @since 18.05.18
 */

public class Converter {
    /**
     * This is a method for runs through iterators which contain elements of type Integer.
     *
     * @param it - Iterator containing iterators.
     * @return
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Integer> first;
            Iterator<Integer> second;

            private void check() {
                if (first != null && first.hasNext()) {
                    return;
                }
                first = null;
                while (it.hasNext()) {
                    second = it.next();
                    if (second.hasNext()) {
                        first = second;
                        break;
                    }
                }
            }

            @Override
            public boolean hasNext() {
                check();
                return first != null;
            }

            @Override
            public Integer next() {
                check();
                if (first == null) {
                    throw new NoSuchElementException();
                }
                return first.next();
            }
        };
    }
}