package ru.job4j.tree;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TreeTest {
    Tree<String> auto = new Tree<>();

    @Before
    public void addElementInCollection() {
        auto.add("GM", "Ford");
        auto.add("GM", "Opel");
        auto.add("GM", "Chevrolet");
        auto.add("Opel", "Astra");
        auto.add("Astra", "Model G");
    }

    @Test
    public void whenFindElementModelGThenTrue() {
        assertThat(auto.findBy("Model G").isPresent(), is(true));
    }

    @Test
    public void whenFindElementModelHThenFalse() {
        assertThat(auto.findBy("Model H").isPresent(), is(false));
    }

    @Test
    public void iterator() {
        Iterator iterator = auto.iterator();
        assertThat(iterator.hasNext(), Is.is(true));
        assertThat(iterator.next(), Is.is("GM"));
        assertThat(iterator.hasNext(), Is.is(true));
        assertThat(iterator.next(), Is.is("Ford"));
        assertThat(iterator.hasNext(), Is.is(true));
        assertThat(iterator.next(), Is.is("Opel"));
        assertThat(iterator.hasNext(), Is.is(true));
        assertThat(iterator.next(), Is.is("Chevrolet"));
        assertThat(iterator.hasNext(), Is.is(true));
        assertThat(iterator.next(), Is.is("Astra"));
        assertThat(iterator.hasNext(), Is.is(true));
        assertThat(iterator.next(), Is.is("Model G"));
        assertThat(iterator.hasNext(), Is.is(false));
    }
}