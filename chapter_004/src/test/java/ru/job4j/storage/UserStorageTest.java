package ru.job4j.storage;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStorageTest {
    private UserStorage storage = new UserStorage();

    @Test
    public void whenAddSixUserInTwoOtherThread() {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                storage.add(new User(111, 100));
                storage.add(new User(222, 200));
                storage.add(new User(333, 300));
            }
        });
        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                storage.add(new User(444, 400));
                storage.add(new User(555, 500));
                storage.add(new User(666, 600));
            }
        });
        threadOne.start();
        threadTwo.start();
        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(storage.getStorage().get(0).getId(), is(111));
        assertThat(storage.getStorage().get(1).getId(), is(222));
        assertThat(storage.getStorage().get(2).getId(), is(333));
        assertThat(storage.getStorage().get(3).getId(), is(444));
        assertThat(storage.getStorage().get(4).getId(), is(555));
        assertThat(storage.getStorage().get(5).getId(), is(666));
    }

    @Test
    public void transferTest() {
        User first = new User(123, 1000);
        User second = new User(124, 2000);
        storage.add(first);
        storage.add(second);
        assertThat(storage.transfer(first.getId(), second.getId(), 500), is(true));
        assertThat(storage.getStorage().get(0).getAmount(), is(500));
        assertThat(storage.getStorage().get(1).getAmount(), is(2500));
    }
}