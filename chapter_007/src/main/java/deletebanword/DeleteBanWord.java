package deletebanword;

import java.io.*;

/**
 * Class DeleteBanWordTest
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 * @since 14.01.2019
 */

public class DeleteBanWord {

    /**
     * This method deletes word from stream.
     *
     * @param in    stream with abuse word.
     * @param out   stream without abuse word.
     * @param abuse list of abuse words.
     */
    void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));
             BufferedWriter write = new BufferedWriter(new OutputStreamWriter(out))) {
            String readBufferLine;
            while ((readBufferLine = reader.readLine()) != null) {
                for (String s : abuse) {
                    readBufferLine = readBufferLine.replaceAll(s, "");
                }
                write.write(readBufferLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}