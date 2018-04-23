package ru.job4j.user;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.*;

public class UserTest {
    @Test
    public void sortTest() {
        List<User> list = Arrays.asList(
                new User(63, "Bill Gates"),
                new User(34, "Mark Zuckerberg"),
                new User(47, "Elon Mask"));
        SortUser sortUser = new SortUser();
        Set<User> result = sortUser.sort(list);
        assertThat(result.iterator().next().getAge(), is(34));
    }
}
