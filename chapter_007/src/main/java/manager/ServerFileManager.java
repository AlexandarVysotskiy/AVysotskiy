package manager;

import bot.ConsoleBot;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.logging.Logger;


/**
 * Class ServerFileManager
 *
 * @author Alexandar Vysotskiy
 * @version 1.1
 * @since 08.02.19
 */

public class ServerFileManager implements ServerInt {

    private final Socket socket;

    private static Logger log = Logger.getLogger(ConsoleBot.class.getName());

    public ServerFileManager(Socket socket) {
        this.socket = socket;
    }

    private File path;

    /**
     * If user input 0, it will be up in parent directory;
     * It is for copy file in opened directory, it need input absolute source path to file.
     *
     * @param startPath - it is directory with the program starts.
     */
    public void start(String startPath) {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
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
                    this.path = fileList;
                    fileList = getListFilesInDirectory(Integer.valueOf(userRequest), fileList);
                    if (!fileList.isFile()) {
                        this.path = fileList;
                    }
                    if (fileList.isDirectory()) {
                        for (File f : fileList.listFiles()) {
                            System.out.println(f.toString());
                            out.println(i++ + " " + f.toString());
                            this.path = fileList;
                        }
                        out.println();
                    } else {
                        out.println(i++ + " " + fileList.toString());
                        out.println();
                    }
                    i = 1;
                } else if (userRequest.contains(".")) {
                    File fileCopyPath = new File(userRequest);
                    String newFileCopyPath = fileCopyPath.toString().substring(fileCopyPath.toString().lastIndexOf("\\"));
                    File dest = new File(this.path + newFileCopyPath);
                    download(fileCopyPath, dest);
                    out.println();
                } else if (userRequest.equals("stop")) {
                    isStop = true;
                }
            } while (!isStop);
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }


    public File getListFilesInDirectory(int number, File file) {
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
     * Method copies a file from a path to other a path.
     * <p>
     * If file is exist in this directory, it will be rewritten.
     *
     * @param src  absolute path for file.
     * @param dest absolute new path for file.
     */
    public void download(File src, File dest) {
        try {
            Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
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