package ru.job4j.dynamic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;

public class DynamicArrayTest {
    DynamicArray<Integer> container = new DynamicArray<>();

    @Before
    public void setUp() {
        container.add(1);
        container.add(2);
        container.add(3);
        container.add(4);
        container.add(5);
        container.add(6);
        container.add(7);
        container.add(8);
        container.add(9);
    }

    @Test
    public void whenAddTeenAndElevenElement() {
        container.add(10);
        container.add(11);
        assertThat(container.get(10), is(11));
    }

    @Test
    public void whenGetIndex() {
        assertThat(container.get(0), is(1));
        assertThat(container.get(8), is(9));
        assertThat(container.get(5), is(6));
    }

    @Test
    public void whenHasNextIsTrueAndNextHaveNextElements() {
        assertThat(container.iterator().hasNext(), is(true));
        assertThat(container.iterator().next(), is(1));
        assertThat(container.iterator().hasNext(), is(true));
        assertThat(container.iterator().next(), is(2));
        assertThat(container.iterator().hasNext(), is(true));
        assertThat(container.iterator().next(), is(3));
        assertThat(container.iterator().hasNext(), is(true));
        assertThat(container.iterator().next(), is(4));
        assertThat(container.iterator().hasNext(), is(true));
        assertThat(container.iterator().next(), is(5));
        assertThat(container.iterator().hasNext(), is(true));
        assertThat(container.iterator().next(), is(6));
        assertThat(container.iterator().hasNext(), is(true));
        assertThat(container.iterator().next(), is(7));
        assertThat(container.iterator().hasNext(), is(true));
        assertThat(container.iterator().next(), is(8));
        assertThat(container.iterator().hasNext(), is(true));
        assertThat(container.iterator().next(), is(9));
        assertThat(container.iterator().hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorThrowNoSuchElementException() {
        assertThat(container.iterator().next(), is(1));
        assertThat(container.iterator().next(), is(2));
        assertThat(container.iterator().next(), is(3));
        assertThat(container.iterator().next(), is(4));
        assertThat(container.iterator().next(), is(5));
        assertThat(container.iterator().next(), is(6));
        assertThat(container.iterator().next(), is(7));
        assertThat(container.iterator().next(), is(8));
        assertThat(container.iterator().next(), is(9));
        assertThat(container.iterator().next(), is(10));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorThrowConcurrentModificationException() {
        assertThat(container.iterator().next(), is(1));
        assertThat(container.iterator().next(), is(2));
        assertThat(container.iterator().next(), is(3));
        assertThat(container.iterator().next(), is(4));
        assertThat(container.iterator().next(), is(5));
        assertThat(container.iterator().next(), is(6));
        assertThat(container.iterator().next(), is(7));
        assertThat(container.iterator().next(), is(8));
        assertThat(container.iterator().next(), is(9));
        container.add(10);
        assertThat(container.iterator().next(), is(10));
    }
}