package xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс создает таблицу в БД и генерирует в нее данные.
 */
public class StoreSQL implements AutoCloseable, Closeable {
    private static final Logger LOG = LoggerFactory.getLogger(StoreSQL.class);
    private Config config;
    private Connection connection;

    /**
     * Конструктор проверяет соединение с БД.
     */
    public StoreSQL(Config config) throws SQLException {
        this.config = config;
        if (!init()) {
            throw new SQLException("Connection is not created!");
        }
        connection.setAutoCommit(false);
    }

    /**
     * Метод создает соединение с БД.
     */
    private void createConnection() throws SQLException {
        connection = DriverManager.getConnection(
                config.get("url"),
                config.get("username"),
                config.get("password")
        );
    }

    /**
     * Метод устанавливает соединение с БД и если не существует создает таблицу с столбцом field типа int.
     *
     * @return удалось/не удалось
     */
    private boolean init() throws SQLException {
        String url = config.get("url");
        try (Connection connection = DriverManager.getConnection(url)) {
            try (Statement statement = connection.createStatement()) {
                statement.execute("CREATE TABLE IF NOT EXISTS entry (field INTEGER)");
            }
        }
        createConnection();
        return this.connection != null;
    }

    /**
     * Метод удаляет все записи с таблицы и добавляет новые.
     *
     * @param n - диапазон записией с n до 0.
     * @throws SQLException
     */
    public void generate(int n) throws SQLException {
        clearTable();
        addValue(n);
    }

    /**
     * @return Список со всеми значениями из БД.
     */
    public List<Entry> getListEntry() throws SQLException {
        List<Entry> list = new LinkedList<>();
        try (Statement st = connection.createStatement()) {
            try (ResultSet rs = st.executeQuery("SELECT * FROM entry")) {
                while (rs.next()) {
                    int value = rs.getInt("field");
                    list.add(new Entry(value));
                }
            }
        }
        return list;
    }

    /**
     * Метод генерирует записи в столбец field;
     */
    private void addValue(int n) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO entry(field) VALUES (?)")) {
            for (int i = 1; i <= n; i++) {
                statement.setInt(1, i);
                statement.addBatch();
                if (i % 100 == 0) {
                    statement.executeBatch();
                }
            }
        } catch (SQLException e) {
            connection.rollback();
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Метод удаляет все записи в БД.
     */
    private void clearTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM entry");
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Метод разъединяет соединение с БД.
     */
    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }
}