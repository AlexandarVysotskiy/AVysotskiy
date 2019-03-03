package ru.job4j.blocking;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {
    SimpleBlockingQueue<Integer> blocking = new SimpleBlockingQueue<>(8);
    private Thread producer;
    private Thread consumer;

    @Before
    public void when() {
        producer = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i < 6) {
                    blocking.offer(i);
                    i++;
                }
            }
        };

        consumer = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i < 6) {
                    assertThat(blocking.getSizeQueue(), is(6 - i));
                    try {
                        assertThat(blocking.poll(), is(i));
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                    assertThat(blocking.getSizeQueue(), is(6 - 1 - i));
                    i++;
                }
            }
        };
    }

    @Test
    public void start() throws InterruptedException {
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}