package parser;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

/**
 * ЗАДАНИЕ:
 * 1. Реализовать модуль сборки и анализа данных с https://rabota.by/jobs-it/.
 * 2. Система должна использовать Jsoup для парсинка страниц.
 * 3. Система должна запускаться раз в день. Предусмотреть изменения частоты запуска.
 * 4. Система должна собирать данные только про вакансии java. Учесть что JavaScript не подходит, как и Java Script.
 * 5. Данные должны храниться в базе данных.
 * 6. Учесть дубликаты.
 * 7. Учитывать время последнего запуска. Если это первый запуск, то нужно собрать все объявления с начало года.
 * 8. В системе не должно быть вывода либо ввода информации. Все настройки должны быть в файле.
 * 9. Для вывода нужной информации использовать логгер log4j.
 */
public class SqlRuParser {
    private static final Logger LOG = Logger.getLogger(SqlRuParser.class);

    public static void main(String[] args) {

        try (InputStream in = Parser.class.getClassLoader().getResourceAsStream("parsing.properties")) {
            Properties config = new Properties();
            config.load(in);
            try (DataBase db = new DataBase(config)) {
                Properties properties = db.getProperties();

                //заданный параметр количества запусков в сутки
                int launches = Integer.valueOf(properties.getProperty("launchesPerDay"));
                int period = (24 * 60 * 60 * 1000) / launches;
                TimerTask parser = new Parser(properties);
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(parser, 0, period);
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
