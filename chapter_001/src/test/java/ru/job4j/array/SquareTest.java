package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Aleksandr Vysotskiiy (Aleksandr.vysotskiiy@gmail.com)
 * @version 1.0
 * @since 0.1
 */

public class SquareTest {
    @Test
    public void equals) {
        Square number = new Square();
        int result = number.calculate(10);
        assertThat(result, is(10));
    }
}
