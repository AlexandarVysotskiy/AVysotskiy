package manager;

import bot.ConsoleBot;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Class ClientFileManager
 *
 * @author Alexandar Vysotskiy
 * @version 1.1
 * @since 08.02.19
 */

public class ClientFileManager implements ClientInt {

    private static Logger log = Logger.getLogger(ConsoleBot.class.getName());

    private Socket socket;

    public ClientFileManager(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner console = new Scanner(System.in)) {
            String ask;
            String answer;
            boolean working = true;
            do {
                answer = in.readLine();
                while (!answer.isEmpty()) {
                    System.out.println(answer);
                    answer = in.readLine();
                }
                ask = console.nextLine();
                out.println(ask);
                if (ask.equals("stop")) {
                    working = false;
                }
            } while (working);
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Properties config = new Properties();
        try (FileInputStream in = new FileInputStream("chapter_007\\src\\main\\resources\\manager.properties")) {
            config.load(in);
            Socket socket = new Socket(InetAddress.getByName(config.getProperty("host")), Integer.valueOf(config.getProperty("port")));
            ClientFileManager clientFileManager = new ClientFileManager(socket);
            clientFileManager.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}