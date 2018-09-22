package ru.job4j.pool;

import org.junit.Test;

public class ThreadPoolTest {

    ThreadPool pool = new ThreadPool();

    @Test
    public void whenUpdateBase() {
        for (int i = 0; i < 1000; i++) {
            pool.work(() -> {
                System.out.println(Thread.currentThread().getName() + "Working");
            });
        }
        pool.shutdown();
    }
}