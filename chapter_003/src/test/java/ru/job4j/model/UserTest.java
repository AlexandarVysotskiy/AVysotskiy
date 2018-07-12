package ru.job4j.model;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {

    Map<String, User> map = new HashMap<>();

    @Test
    public void whenDoNotOverrideEqualsAndHashCode() {
        User first = new User("Vasya", 5, new GregorianCalendar(20, 9, 1996));
        User second = new User("Vasya", 5, new GregorianCalendar(20, 9, 1996));
        map.put("First", first);
        map.put("Second", second);
        System.out.println(map);
    }
}