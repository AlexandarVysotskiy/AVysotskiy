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

    @Test
    public void deleteTest() {
        assertThat(nonBlockingCash.get(1), is(first));
        nonBlockingCash.delete(1);
        assertNull(nonBlockingCash.get(1));
    }

    @Test
    public void updateTest() {
        Model newModel = new Model("2", "Second");
        try {
            nonBlockingCash.update(1, newModel);
        } catch (OptimisticException e) {
            e.printStackTrace();
        }
        assertThat(nonBlockingCash.get(1), is(newModel));
        assertThat(nonBlockingCash.get(1).getVersion(), is(1));
    }

    @Test
    public void whenExceptionOptimisticException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread threadOne = new Thread(
                () -> {
                    Model model = nonBlockingCash.get(1);
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
                    Model model = nonBlockingCash.get(1);
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