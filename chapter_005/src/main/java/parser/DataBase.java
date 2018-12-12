package parser;

import org.apache.log4j.Logger;

import java.io.*;
import java.sql.*;
import java.util.Properties;

/**
 * Класс базы для сохранения вакансий.
 */
public class DataBase implements Closeable {

    private static Logger LOG = Logger.getLogger(DataBase.class);

    private Connection connection;

    private Properties config;

    public DataBase(Properties config) {
        this.config = config;
        makeConnection();
    }

    public DataBase() {
    }

    public Properties getProperties() {
        Properties properties = null;
        try (Reader reader = new InputStreamReader(new FileInputStream("chapter_005\\src\\main\\resources\\parsing.properties"))) {
            properties = new Properties();
            properties.load(reader);
            reader.close();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * Подключениие к БД.
     */
    private void makeConnection() {
        try {
            this.connection = DriverManager.getConnection(
                    this.config.getProperty("url"),
                    this.config.getProperty("username"),
                    this.config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Создает таблицу если она не существует
     */
    private void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS vacancies( \n" +
                "\t--id - первичный ключ\\n\" \n" +
                "\tid SERIAL PRIMARY KEY, --name - имя вакансии\\n \n" +
                "\tname VARCHAR(100), \n" +
                "\t--text - текст вакансии\\\n" +
                "\ttext VARCHAR(1000), \n" +
                "\t--link - текст, ссылка на вакансию \n" +
                "\tlink VARCHAR(200),\n" +
                "\t--date - дата вакансии \n" +
                "\tdateAdd VARCHAR(50)); ";

        try (Statement st = connection.createStatement()) {
            st.executeUpdate(query);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    /**
     * Добавляет заявку в БД
     *
     * @param name - имя
     * @param text - описание
     * @param link - адрес заявки
     */
    public void addVacancy(String name, String text, String link, String date) {
        createTable();
        String query = "\n" +
                "INSERT INTO vacancies(name, text, link, dateAdd) VALUES (?,?,?,?);";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, text);
            ps.setString(3, link);
            ps.setString(4, date);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public String getLastData(){
        String result = null;
        String query = "SELECT MIN(dateAdd) FROM vacancies";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getString("min");
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws IOException {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
