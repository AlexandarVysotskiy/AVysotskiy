package socketbot;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerSocketBotTest {

    private static final String LS = System.getProperty("line.separator");

    public void serverSocketBotTest(String input, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        ServerSocketBot server = new ServerSocketBot(socket);
        server.start();
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenUserWriteExitThenBotIsStops() throws IOException {
        serverSocketBotTest("Exit", Joiner.on(LS).join("Goodbye my friend, see you later.", "", ""));
    }

    @Test
    public void whenUserWriteHelloTheBotGiveAnswer() throws IOException {
        serverSocketBotTest(Joiner.on(LS).join("Hello", "Exit"),
                Joiner.on(LS).join("Hello, dear friend, I'm a oracle.",
                        "", "Goodbye my friend, see you later.", "", ""));
    }

    @Test
    public void whenUserWriteUnknownQuestionThenBotGiveAnswer() throws IOException {
        serverSocketBotTest(Joiner.on(LS).join("Something is unknown for bot", "Exit"),
                Joiner.on(LS).join("I don't know what answer.", "", "Goodbye my friend, see you later.", "", ""));
    }
}