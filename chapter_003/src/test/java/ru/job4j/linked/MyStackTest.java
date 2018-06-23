package ru.job4j.linked;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MyStackTest {

    @Test(expected = NoSuchElementException.class)
    public void poll() {
        MyStack<String> myStack = new MyStack();
        myStack.push("First");
        myStack.push("Second");
        myStack.push("Third");
        assertThat(myStack.poll(), is("Third"));
        assertThat(myStack.poll(), is("Second"));
        assertThat(myStack.poll(), is("First"));
        myStack.poll();
    }
}