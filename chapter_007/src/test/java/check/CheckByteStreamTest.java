package check;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CheckByteStreamTest {
    CheckByteStream c = new CheckByteStream();

    @Test
    public void whenInEvenThenResultTrue() throws IOException {
        String two = "2";
        String four = "4";
        String six = "6";
        InputStream[] is = {new ByteArrayInputStream(two.getBytes()), new ByteArrayInputStream(four.getBytes()),
                new ByteArrayInputStream(six.getBytes())};
        for (InputStream i : is) {
            assertThat(c.isNumber(i), is(true));
        }
    }

    @Test
    public void whenEvenNumberIsNotExists() throws IOException {
        String one = "1";
        String three = "3";
        String five = "5";
        InputStream oneInStr = new ByteArrayInputStream(one.getBytes());
        InputStream threeInStr = new ByteArrayInputStream(three.getBytes());
        InputStream fiveInStr = new ByteArrayInputStream(five.getBytes());
        assertThat(c.isNumber(oneInStr), is(false));
        assertThat(c.isNumber(threeInStr), is(false));
        assertThat(c.isNumber(fiveInStr), is(false));
    }
}