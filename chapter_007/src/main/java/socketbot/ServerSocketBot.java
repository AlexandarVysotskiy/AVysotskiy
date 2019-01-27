package socketbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class ServerSocketBot
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 * @since 27.01.19
 */

public class ServerSocketBot {

    private Socket socket;

    public ServerSocketBot(Socket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String ask;
        boolean isExit = false;
        do {
            System.out.println("wait command ...");
            ask = in.readLine();
            System.out.println(ask);
            if ("Hello".equals(ask)) {
                out.println("Hello, dear friend, I'm a oracle.");
                out.println();
            } else if (!"Exit".equals(ask)) {
                out.println("I don't know what answer.");
                out.println();
            } else {
                out.println("Goodbye my friend, see you later.");
                out.println();
                isExit = true;
            }
        } while (!isExit);
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        ServerSocketBot serverSocketBot = new ServerSocketBot(socket);
        serverSocketBot.start();
    }
}