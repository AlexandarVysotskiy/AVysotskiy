package manager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;


/**
 * Class ServerFileManager
 *
 * @author Alexandar Vysotskiy
 * @version 1.0 demo
 * @since 08.02.19
 */

public class ServerFileManager {

    private Socket socket;

    public ServerFileManager(Socket socket) {
        this.socket = socket;
    }

    private File path;

    /**
     * Что бы подняться в родительский каталог введите 0.
     * для перемещения файла в существующую директории нажмите "d" или "D" и укажите полный путь к файлу.
     *
     * @param startPath - каталог с каторого запускается программа.
     */
    public void start(String startPath) throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        File fileList = new File(startPath);
        int n = 1;
        for (File f : fileList.listFiles()) {
            out.println(n++ + " " + f.toString());
        }
        out.println();
        boolean isStop = false;
        int i = 1;
        String userRequest;
        do {
            userRequest = in.readLine();
            if (!(userRequest.contains(".")) & !(userRequest.equals("stop"))) {
                fileList = getListFileInDirectory(Integer.valueOf(userRequest), fileList);
                this.path = fileList;
                if (fileList.isDirectory()) {
                    for (File f : fileList.listFiles()) {
                        out.println(i++ + " " + f.toString());
                    }
                    out.println();
                    i = 1;
                } else {
                    out.println(fileList.toString());
                    out.println();
                }
            } else if (userRequest.contains(".")) {
                File fileCopyPath = new File(userRequest);
                String newFileCopyPath = fileCopyPath.toString().substring(fileCopyPath.toString().lastIndexOf("\\"));
                System.out.println(newFileCopyPath);
                File dest = new File(this.path + newFileCopyPath);
                download(fileCopyPath, dest);
                out.println();
            } else if (userRequest.equals("stop")) {
                isStop = true;
            }
        } while (!isStop);
    }


    public File getListFileInDirectory(int number, File file) {
        File result;
        File[] f = file.listFiles();
        if (number == 0) {
            result = file.getParentFile();
        } else {
            result = f[number - 1];
        }
        return result;
    }


    /**
     * В случае если файл существует он перезаписывается.
     *
     * @param src
     * @param dest
     * @return
     */
    public String download(File src, File dest) {
        try {
            Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }


    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("chapter_007\\src\\main\\resources\\manager.properties")) {
            Properties config = new Properties();
            config.load(in);
            ServerSocket serverSocket = new ServerSocket(Integer.valueOf(config.getProperty("port")));
            Socket socket = serverSocket.accept();
            ServerFileManager server = new ServerFileManager(socket);
            server.start(config.getProperty("root"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}