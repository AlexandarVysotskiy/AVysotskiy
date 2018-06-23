package ru.job4j.linked;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MyQueueTest {

    @Test(expected = NoSuchElementException.class)
    public void push() {
        MyQueue<String> myQueue = new MyQueue();
        myQueue.push("First");
        myQueue.push("Second");
        myQueue.push("Third");
        assertThat(myQueue.poll(), is("First"));
        assertThat(myQueue.poll(), is("Second"));
        assertThat(myQueue.poll(), is("Third"));
        myQueue.poll();
    }
}