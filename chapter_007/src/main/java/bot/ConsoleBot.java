package bot;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Logger;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Class ConsoleBot;
 *
 * Программа консольный чат.
 * Пользователь вводит слово-фразу, программа берет случайную фразу из текстового файла и выводит в ответ.
 * Программа замолкает если пользователь вводит слово «стоп» при этом пользователь может продолжать отправлять сообщения в чат.
 * Если пользователь вводит слово «продолжить», программа снова начинает отвечать.
 * При вводе слова «закончить» программа прекращает работу.
 * Запись диалога включая, слова-команды стоп/продолжить/закончить записать в текстовый лог.
 *
 * @author Alexandar Vysotskiy;
 * @version 1.0;
 * @since 23.01.19;
 */

public class ConsoleBot {

    private static Logger log = Logger.getLogger(ConsoleBot.class.getName());

    private void chat(File fileWithPhrase) {
        Scanner in = new Scanner(System.in);
        boolean isFinish = false;
        boolean isPause = false;
        String userInput;
        String botAnswer;
        while (!isFinish) {
            userInput = in.nextLine();
            botAnswer = getRandomStringFromFile(fileWithPhrase);
            log.info(userInput);
            log.info(botAnswer);
            if (userInput.equals("закончить")) {
                isFinish = true;
            } else if (userInput.equals("стоп")) {
                isPause = true;
            } else if (userInput.equals("продолжить")) {
                isPause = false;
            } else if (!isPause) {
                System.out.println(botAnswer);
            }
        }
    }


    /**
     * @return случайную строку из файла.
     */
    private String getRandomStringFromFile(File fileWithPhrase) {
        String result = null;
        try {
            RandomAccessFile file = new RandomAccessFile(fileWithPhrase, "r");
            long randomLocation = (long) ((Math.random() * fileWithPhrase.length()));
            file.seek(randomLocation);
            result = new String(file.readLine().getBytes(StandardCharsets.ISO_8859_1), UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.substring(result.lastIndexOf("\\") + 1);
    }

    public static void main(String[] args) {
        ConsoleBot consoleBot = new ConsoleBot();
        File input = new File(ConsoleBot.class.getClassLoader().getResource("answer_database.txt").getPath());
        consoleBot.chat(input);
    }
}