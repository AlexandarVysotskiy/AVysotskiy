package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {
    SimpleSet<String> simpleSet = new SimpleSet();

    @Before
    public void addElement() {
        simpleSet.add("One");
        simpleSet.add("Two");
        simpleSet.add("Three");
    }

    /**
     * Test number one;
     */

    @Test
    public void iteratorTest() {
        assertThat(simpleSet.iterator().hasNext(), is(true));
        assertThat(simpleSet.iterator().next(), is("One"));
        assertThat(simpleSet.iterator().hasNext(), is(true));
        assertThat(simpleSet.iterator().next(), is("Two"));
        assertThat(simpleSet.iterator().hasNext(), is(true));
    }

    /**
     * Test number two;
     */

    @Test(expected = NoSuchElementException.class)
    public void noSuchElementExceptionTest() {
        assertThat(simpleSet.iterator().hasNext(), is(true));
        assertThat(simpleSet.iterator().next(), is("One"));
        assertThat(simpleSet.iterator().hasNext(), is(true));
        assertThat(simpleSet.iterator().next(), is("Two"));
        assertThat(simpleSet.iterator().hasNext(), is(true));
        assertThat(simpleSet.iterator().next(), is("Three"));
        assertThat(simpleSet.iterator().hasNext(), is(false));
        assertThat(simpleSet.iterator().next(), is("four"));
    }
}