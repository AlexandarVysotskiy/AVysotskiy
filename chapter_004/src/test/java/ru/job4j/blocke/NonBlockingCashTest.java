package ru.job4j.blocke;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.blocking.Model;
import ru.job4j.blocking.NonBlockingCash;
import ru.job4j.blocking.OptimisticException;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNull;
import static org.hamcrest.core.Is.is;

public class NonBlockingCashTest {
    Model first = new Model("1", "First");
    Model second = new Model("2", "Second");
    NonBlockingCash nonBlockingCash;

    @Before
    public void addElement() {
        nonBlockingCash = new NonBlockingCash();
        nonBlockingCash.add(1, first);
        nonBlockingCash.add(2, second);
    }

    @Test(expected = NullPointerException.class)
    public void deleteTest() throws CloneNotSupportedException {
        assertThat(nonBlockingCash.get(1).getName(), is(first.getName()));
        nonBlockingCash.delete(1);
        assertNull(nonBlockingCash.get(1));
    }

    @Test
    public void updateTest() throws CloneNotSupportedException {
        try {
            nonBlockingCash.update(1, second);
        } catch (OptimisticException e) {
            e.printStackTrace();
        }
        assertThat(nonBlockingCash.get(1).getVersion(), is(0));
        assertThat(nonBlockingCash.get(1).getName(), is(second.getName()));
        second.change("Вася");
        assertThat(nonBlockingCash.get(1).getVersion(), is(1));
    }

    @Test
    public void whenExceptionOptimisticException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread threadOne = new Thread(
                () -> {
                    Model model = null;
                    try {
                        model = nonBlockingCash.get(2);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                    model.change("Вася");
                    try {
                        Thread.sleep(200);
                        nonBlockingCash.update(1, model);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
        );
        Thread threadTwo = new Thread(
                () -> {
                    Model model = null;
                    try {
                        model = nonBlockingCash.get(2);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                    model.change("Илон");
                    try {
                        Thread.sleep(1000);
                        nonBlockingCash.update(1, model);
                    } catch (OptimisticException | InterruptedException ie) {
                        ex.set(ie);
                    }
                }
        );
        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadTwo.join();
        assertThat(ex.get().getMessage(), is("OptimisticException"));
    }
}