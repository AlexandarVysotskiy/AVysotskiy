package ru.job4j.email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void emailTo(User user) {
        this.pool.execute(() -> {
            String subject = "Notification to email" + user.getName() + user.getEmail();
            String body = "Add a new event for" + user.getName();
            String email = user.getEmail();
            send(subject, body, email);
        });
    }

    public void close() {
        this.pool.shutdown();
    }

    public void send(String subject, String body, String email) {
    }
}