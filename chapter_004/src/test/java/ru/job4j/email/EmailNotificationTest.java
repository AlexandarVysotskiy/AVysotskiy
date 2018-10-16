package ru.job4j.email;

import org.junit.Test;

import java.util.concurrent.RejectedExecutionException;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class EmailNotificationTest {
    EmailNotification emailNotification = new EmailNotification();
    User user = new User("Vasya", "Vasya@mail.ru");

    @Test
    public void emailToTest() throws InterruptedException {
        EmailForTest emailForTest = new EmailForTest();
        emailForTest.emailTo(user);
        Thread.sleep(100);
        assertThat(emailForTest.getValue(), is("Notification to email" + "Vasya" + "Vasya@mail.ru"));
    }

    @Test(expected = RejectedExecutionException.class)
    public void closeTest() {
        emailNotification.close();
        emailNotification.emailTo(user);
    }

    private class EmailForTest extends EmailNotification {
        private String value;

        @Override
        public void send(String subject, String body, String email) {
            this.value = subject;
        }

        public String getValue() {
            return value;
        }
    }
}