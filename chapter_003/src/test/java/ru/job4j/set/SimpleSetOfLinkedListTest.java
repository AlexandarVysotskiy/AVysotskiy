package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleSetOfLinkedListTest {
    SimpleSetOfLinkedList<String> list = new SimpleSetOfLinkedList();

    @Before
    public void addElement() {
        list.add("One");
        list.add("Two");
        list.add("Three");
        list.add("Four");
        list.add("Five");
    }

    @Test
    public void whenAddDuplicateElementAndResultFalse() {
        assertThat(list.add("Five"), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorTest() {
        assertThat(list.iterator().hasNext(), is(true));
        assertThat(list.iterator().next(), is("One"));
        assertThat(list.iterator().hasNext(), is(true));
        assertThat(list.iterator().next(), is("Two"));
        assertThat(list.iterator().hasNext(), is(true));
        assertThat(list.iterator().next(), is("Three"));
        assertThat(list.iterator().hasNext(), is(true));
        assertThat(list.iterator().next(), is("Four"));
        assertThat(list.iterator().hasNext(), is(true));
        assertThat(list.iterator().next(), is("Five"));
        assertThat(list.iterator().hasNext(), is(false));
        assertThat(list.iterator().next(), is("Six"));
    }
}