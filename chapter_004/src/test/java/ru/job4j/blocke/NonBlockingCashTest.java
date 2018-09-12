package ru.job4j.blocke;

import org.junit.Test;
import ru.job4j.blocking.Model;
import ru.job4j.blocking.NonBlockingCash;
import ru.job4j.blocking.OptimisticException;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNull;
import static org.hamcrest.core.Is.is;

public class NonBlockingCashTest {
    Model first = new Model("1", "First");
    NonBlockingCash nonBlockingCash = new NonBlockingCash();

    @Test
    public void deleteTest() {
        nonBlockingCash.add(1, first);
        assertThat(nonBlockingCash.get(1), is(first));
        nonBlockingCash.delete(1);
        assertNull(nonBlockingCash.get(1));
    }

    @Test
    public void updateTest() {
        nonBlockingCash.add(1, first);
        Model newModel = new Model("2", "Second");
        try {
            nonBlockingCash.update(1, newModel);
        } catch (OptimisticException e) {
            e.printStackTrace();
        }
        assertThat(nonBlockingCash.get(1), is(newModel));
        assertThat(nonBlockingCash.get(1).getVersion(), is(1));
    }
}