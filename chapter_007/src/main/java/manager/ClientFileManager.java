package manager;

import socketbot.ClientSocketBot;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;

/**
 * Class ClientFileManager
 *
 * @author Alexandar Vysotskiy
 * @version 1.0 demo
 * @since
 */

public class ClientFileManager {
    public static void main(String[] args) {
        Properties config = new Properties();
        try (FileInputStream in = new FileInputStream("chapter_007\\src\\main\\resources\\manager.properties")) {
            config.load(in);
            Socket socket = new Socket(InetAddress.getByName(config.getProperty("host")), Integer.valueOf(config.getProperty("port")));
            ClientSocketBot clientSocketBot = new ClientSocketBot(socket);
            clientSocketBot.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
