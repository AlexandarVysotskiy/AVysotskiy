package ru.job4j.cycle;

import org.junit.Test;
import org.junit.Before;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import ru.job4j.cycle.Cycle.Node;

public class CycleTest {

    private static Cycle<String> cycle;
    private static Node<String> first;
    private static Node<String> two;
    private static Node<String> third;
    private static Node<String> four;
    private static Node<String> five;

    @Before
    public void testUp() {
        cycle = new Cycle<>();
        first = new Node<>("one");
        two = new Node<>("two");
        third = new Node<>("three");
        four = new Node<>("four");
        five = null;
    }

    @Test
    public void testOne() {
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;
        assertThat(cycle.findCycle(first), is(true));
    }

    @Test
    public void testTwo() {
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = two;
        assertThat(cycle.findCycle(first), is(true));
    }

    @Test
    public void testThree() {
        first.next = two;
        two.next = third;
        third.next = four;
        assertThat(cycle.findCycle(first), is(false));
    }

    @Test
    public void testFour() {
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = five;
        assertThat(cycle.findCycle(first), is(false));
    }
}