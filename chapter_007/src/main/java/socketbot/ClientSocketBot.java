package socketbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Class ClientSocketBot
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 * @since 27.01.19
 */

public class ClientSocketBot {

    private Socket socket;

    public ClientSocketBot(Socket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner console = new Scanner(System.in)) {
            String ask;
            String answer;
            boolean working = true;
            do {
                ask = console.nextLine();
                out.println(ask);
                answer = in.readLine();
                while (!answer.isEmpty()) {
                    System.out.println(answer);
                    answer = in.readLine();
                }
                if (ask.equals("exit")) {
                    working = false;
                }
            } while (working);
        }
    }

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 8080)) {
            ClientSocketBot clientSocketBot = new ClientSocketBot(socket);
            clientSocketBot.start();
        }
    }
}