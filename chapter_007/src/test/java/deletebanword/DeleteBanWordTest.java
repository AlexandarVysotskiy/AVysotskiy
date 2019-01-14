package deletebanword;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class DeleteBanWordTest {
    DeleteBanWord deleteBanWord = new DeleteBanWord();

    @Test
    public void dropAbuses() {
        String strInput = "Милый Вова, \n" +
                "Здорово. \n" +
                "У меня не плохая \n" +
                "“Жись”, \n" +
                "Но если ты не женился, \n" +
                "То не женись...";
        //@Author Есенин С.А. 26 июля 1925
        String strOutput = "Вова, Здорово. У меня не плохая “Жись”, Но если ты не женился, То не ...";
        String[] abuses = {"Милый ", "женись"};
        InputStream in = new ByteArrayInputStream(strInput.getBytes());
        OutputStream os = new ByteArrayOutputStream();
        deleteBanWord.dropAbuses(in, os, abuses);
        assertThat(strOutput, is(os.toString()));
    }
}