package parser;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser extends TimerTask {
    private static final Logger LOG = Logger.getLogger(Parser.class);

    private Properties properties;

    /**
     * Дата, с которой начинается поиск ваканский.
     */
    private String start;

    private DataBase dataBase;

    Parser(Properties properties) {
        this.properties = properties;
        dataBase = new DataBase(properties);
    }

    /**
     * Запуск парсинга вакансий.
     */
    public void run() {
        LOG.info("Парсинг вакансий запущен");
        this.start = this.lastParsingDate();
        this.parsePages();
        LOG.info("Парсинг вакансий закончен");
    }


    /**
     * Метод поиска последней страницы вакансий.
     * PS почему-то "a.nav_link" возращает два одинаковых значения поэтому метод так странно написан.
     *
     * @return номер страницы.
     */
    private Integer getLastPage() {
        int result = 0;
        try {
            Document doc = Jsoup.connect("https://rabota.by/jobs-it/").get();
            Elements elements = doc.select("div.nav_last");
            String[] i = elements.select("a.nav_link").text().split(" ");
            result = Integer.valueOf(i[0]);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод запускает парсинг страниц по номеру страницы, умножается на 20 т.к. 20 ваканций на странице.
     */
    private void parsePages() {
        int last = getLastPage();
        for (int i = 0; i < last; i++) {
            if (!this.parsEachPage(i * 20)) {
                break;
            }
        }
    }

    /**
     * Метод парсит страницу по номеру
     *
     * @param number номер страницы
     * @return когда парсинг окончен
     */
    private boolean parsEachPage(int number) {
        Document document;
        try {
            document = Jsoup.connect("https://rabota.by/jobs-it/" + number + "/20/").get();

            Elements elements = document.select("div.adholder.short_body.short-max");
            for (Element element : elements) {
                String date = element.select("span.italic").text();
                if (isNewDate(date)) {
                    String title = element.select("a.statistics_view_short").text();
                    String link = "https://rabota.by/" + element.select("a.statistics_view_short").select("a").first().attr("href");
                    String description = element.select("div.short-text.vacancy_preview_text").text();
                    /**
                     * Проверка на релевантность и на дубликат. Если есть заявка одинаковыми именем и описанием то false.
                     */
                    if (isRelevance(title)) {
                        dataBase.addVacancy(title, description, link, date);
                    }
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Метод меняет название месяца строки на число
     *
     * @param date
     * @return
     */
    private static String normDate(String date) {
        String result = date;
        String[] month = {"января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        for (int i = 0; i < month.length; i++) {
            if (date.contains(month[i])) {
                result = date.replace(month[i], String.valueOf(i + 1));
                break;
            }
        }
        return result;
    }

    /**
     * Проверка даты вакансии на актуальность.
     *
     * @param date дата вакансии
     * @return true если вакансия новая, false если была размещена до последнего запуска
     */
    private boolean isNewDate(String date) {
        boolean result = true;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy HH:mm");
            Date vacancyDate = format.parse(normDate(date));
            Date startDate = format.parse(this.start);

            if (startDate.after(vacancyDate)) {
                result = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Получение даты последнего объявления.
     *
     * @return дату последнего запуска из параметров или 1 января текущего года
     */
    private String lastParsingDate() {
        String date = normDate(dataBase.getLastData());
        if (date.equals("")) {
            SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy HH:mm");
            date = String.format("01 01 2018 00:00", format.format(new Date()));
        }
        return date;
    }

    /**
     * Проверка на релевантность вакансии.
     *
     * @param text заговолок вакансии.
     * @return true если заговолок содержит java и не содекжит script;
     */
    public boolean isRelevance(String text) {
        boolean result;
        Pattern pattern = Pattern.compile("\\bJava\\b");
        Matcher matcher = pattern.matcher(text);
        result = matcher.find();
        return result;
    }
}
