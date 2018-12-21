package crud;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbStore implements Store {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static DbStore instance = new DbStore();
    private static Logger log = Logger.getLogger(DbStore.class);

    public DbStore() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/DBStore");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public static DbStore getInstance() {
        return instance;
    }

    /**
     * Создает таблицу если она не существует
     */
    private void createTable() {
        String query = new StringBuilder().append(" create table IF NOT EXISTS databasestore\n").append(" (\n").append("   id serial,\n").append("   login   varchar(50),\n").append("  email   varchar(50),\n").append("   name    varchar(50),\n").append("   dateadd varchar(50)\n").append(" );").toString();
        try (Connection connection = this.SOURCE.getConnection()) {
            try (Statement st = connection.createStatement()) {
                st.executeUpdate(query);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @Override
    public User add(User user) {
        createTable();
        String query = "INSERT INTO databaseStore(login, email, name, dateAdd) VALUES (?,?,?,?);";
        String dateAdd = user.getCreateDate().toString();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)
        ) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getName());
            ps.setString(4, dateAdd);
            ps.executeUpdate();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        String query = "select login,email,name  from databaseStore;";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new User(rs.getString("name"), rs.getString("login"), rs.getString("email")));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int getId(User user) {
        Integer result = null;
        String login = user.getLogin();
        String query = "select id from databasestore where login = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt("id");
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void update(int id, User user) {
        String query = String.format("UPDATE databasestore SET login = ?,email = ?,name = ?WHERE id = ?");
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getName());
            ps.setInt(4, Integer.valueOf(id));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String query = "DELETE FROM databaseStore where id = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findById(Integer id) {
        User result = null;
        String query = "select * from databaseStore where id = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = new User(rs.getString("name"), rs.getString("login"), rs.getString("email"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
