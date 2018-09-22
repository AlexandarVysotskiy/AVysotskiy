package ru.job4j.pool;

import ru.job4j.blocking.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * This is class realizations pool of threads.
 */
public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int index = 0; index < size; index++) {
            Thread thread = new Thread(
                    () -> {
                        while (!Thread.currentThread().isInterrupted()) {
                            synchronized (tasks) {
                                while (tasks.isEmpty()) {
                                    try {
                                        System.out.println(Thread.currentThread().getName() + "Waiting");
                                        tasks.wait();
                                    } catch (InterruptedException ie) {
                                        ie.printStackTrace();
                                    }
                                }
                            }
                            try {
                                tasks.poll().run();
                            } catch (InterruptedException ie) {
                                ie.printStackTrace();
                            }
                        }
                    });
            threads.add(thread);
            thread.start();
        }
    }


    /**
     * This is method to add a task in the queue.
     *
     * @param job
     */
    public void work(Runnable job) {
        synchronized (tasks) {
            tasks.offer(job);
            tasks.notify();
        }
    }

    /**
     * This is method to stop the pool of threads.
     */
    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
