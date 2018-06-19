package ru.job4j.linked;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MyLinkedListTest {
    MyLinkedList<Integer> myLinkedList = new MyLinkedList();

    @Before
    public void addElementsInMyLinkedList() {
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        myLinkedList.add(4);
        myLinkedList.add(5);
    }

    @Test
    public void whenGetItemByIndex() {
        assertThat(myLinkedList.get(4), is(5));
    }

    @Test
    public void testForIterator() {
        Iterator<Integer> iterator = myLinkedList.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(5));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenModificationCollection() {
        Iterator<Integer> iterator = myLinkedList.iterator();
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.next(), is(5));
        myLinkedList.add(6);
        assertThat(iterator.next(), is(6));
    }
}