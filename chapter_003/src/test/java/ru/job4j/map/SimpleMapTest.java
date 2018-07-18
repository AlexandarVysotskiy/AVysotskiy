package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleMapTest {
    SimpleMap<String, String> fruit = new SimpleMap();

    @Before
    public void addElementToArray() {
        fruit.insert("First", "Apple");
        fruit.insert("Second", "Orange");
        fruit.insert("Third", "Watermelon");
        fruit.insert("Fourth", "Pear");
        fruit.insert("Fifth", "Strawberry");
    }

    /**
     * Не пройдет т.к. hash кода для четвертого и пятого элемента равны, следовательно пятый элемент не добавится.
     * Коллизии в этом задании разрешать не надо метод insert просто выдает false.
     * assertThat(fruit.get("Fifth"), is("Strawberry"));
     */

    @Test
    public void get() {
        assertThat(fruit.get("First"), is("Apple"));
        assertThat(fruit.get("Second"), is("Orange"));
        assertThat(fruit.get("Third"), is("Watermelon"));
        assertThat(fruit.get("Fourth"), is("Pear"));
    }


    @Test
    public void delete() {
        assertThat(fruit.delete("Third"), is(true));
        assertThat(fruit.delete("Third"), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void iterator() {
        assertThat(fruit.iterator().hasNext(), is(true));
        assertThat(fruit.iterator().next().value, is("Apple"));
        assertThat(fruit.iterator().hasNext(), is(true));
        assertThat(fruit.iterator().next().value, is("Orange"));
        assertThat(fruit.iterator().hasNext(), is(true));
        assertThat(fruit.iterator().next().value, is("Watermelon"));
        assertThat(fruit.iterator().hasNext(), is(true));
        assertThat(fruit.iterator().next().value, is("Pear"));
        assertThat(fruit.iterator().hasNext(), is(false));
        assertThat(fruit.iterator().next().value, is("Unchecked"));
    }
}