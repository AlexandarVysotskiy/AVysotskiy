package cinema;

import crud.DbStore;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CinemaDB implements Db {
    private Account account;
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final CinemaDB INSTANCE = new CinemaDB();
    private static Logger log = Logger.getLogger(DbStore.class);

    public CinemaDB() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/cinemadb");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
    }

    public static CinemaDB getInstance() {
        return INSTANCE;
    }

    @Override
    public Account addNewAccount(Account account) {
        this.account = account;
        String addAccount = "INSERT INTO accounts (phone, fullname, row, blockcolumn) values(?, ?, ?, ?);";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(addAccount)
        ) {
            connection.setAutoCommit(false);
            ps.setString(1, account.getPlace().getRow());
            ps.setString(2, account.getPlace().getColumn());
            ps.setString(3, account.getPhone());
            ps.setString(4, account.getFullName());
            ps.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        addPlace();
        return account;
    }


    private void addPlace() {
        String addHalls = "INSERT INTO halls (row, blockcolumn) values(?,?);";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(addHalls)
        ) {
            connection.setAutoCommit(false);
            ps.setString(1, this.account.getPlace().getRow());
            ps.setString(2, this.account.getPlace().getColumn());
            ps.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @Override
    public List<Place> getPlace() {
        ArrayList result = new ArrayList<Place>();
        String addHalls = "select * from halls;";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(addHalls)
        ) {
            connection.setAutoCommit(false);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new Place(rs.getString("row"), rs.getString("blockcolumn")));
            }
            rs.close();
            connection.commit();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }
}
