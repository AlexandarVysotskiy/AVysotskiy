package ru.job4j.hash;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleHashSetTest {
    SimpleHashSet<Integer> hashSet = new SimpleHashSet(10);

    @Before
    public void addElements() {
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
        hashSet.add(4);
        hashSet.add(5);
        hashSet.add(6);
    }

    /**
     * Test number one.
     */

    @Test
    public void findTest() {
        assertThat(hashSet.find(1), is(true));
        assertThat(hashSet.find(2), is(true));
        assertThat(hashSet.find(3), is(true));
        assertThat(hashSet.find(4), is(true));
        assertThat(hashSet.find(5), is(true));
        assertThat(hashSet.find(6), is(true));
        assertThat(hashSet.find(7), is(false));
    }

    /**
     * Test number two.
     */

    @Test
    public void removeTest() {
        hashSet.remove(2);
        assertThat(hashSet.find(2), is(false));
        hashSet.remove(6);
        assertThat(hashSet.find(6), is(false));
        hashSet.remove(3);
        assertThat(hashSet.find(3), is(false));
    }

    /**
     * Test number three.
     */

    @Test
    public void growTest() {
        hashSet.add(7);
        hashSet.add(8);
        hashSet.add(9);
        hashSet.add(10);
        assertThat(hashSet.find(10), is(true));
    }

    /**
     * Test number four.
     */

    @Test
    public void whenAddDuplicateElementAndExpectedFalse() {
        assertThat(hashSet.add(2), is(false));
    }
}