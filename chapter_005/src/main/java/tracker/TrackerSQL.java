package tracker;

import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.List;

/**
 * @version 1.0.
 * @since 24.11.2018.
 */
public class TrackerSQL implements ITracker, Closeable {
    /**
     * Connection with data base.
     */
    private Connection connection;
    /**
     * Set to data base.
     */
    private Properties config;

    /**
     * Construction.
     *
     * @param config set to data base.
     */
    public TrackerSQL(Properties config) {
        this.config = config;
    }

    /**
     * Метод устанавливает соединение с БД.
     *
     * @return удалось/не удалось.
     */
    public boolean init() {
        try {
            this.connection = DriverManager.getConnection(
                    this.config.getProperty("url"),
                    this.config.getProperty("username"),
                    this.config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            checkItemsTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Method checks if this table is and correct, if isn't create it.
     *
     * @return true/false.
     */
    private void checkItemsTable() throws SQLException {
        boolean tableExistAndCorrect = isItemsTableExist();
        if (tableExistAndCorrect) {
            tableExistAndCorrect = itemsTableCorrect();
            if (!tableExistAndCorrect) {
                renameExistingTable();
            }
        }
        if (!tableExistAndCorrect) {
            createItemsTable();
        }
    }

    /**
     * Method check is table.
     *
     * @return true/false.
     * @throws SQLException
     */
    private boolean isItemsTableExist() throws SQLException {
        boolean result = false;
        String query = "SELECT COUNT(t.table_name) FROM information_schema.tables t WHERE t.table_schema='public' AND t.table_name = 'items'";
        PreparedStatement ps = this.connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            result = rs.getInt("count") == 1;
        }
        rs.close();
        ps.close();
        return result;
    }

    /**
     * Метод проверяет корректность колонок: наличие колонок с нужными именами и типами и отсутствие других колонок с обязательным заполнением.
     * Method check correct columns: availability with correct name, type and lack other columns;
     *
     * @return true/false.
     * @throws SQLException
     */
    private boolean itemsTableCorrect() throws SQLException {
        boolean result = false;
        String query = String.format("SELECT SUM(nessesaryColumnCount) nessesaryColumnCount,SUM(unnesesarryColumnCount) unnesesarryColumnCount \nFROM (SELECT SUM(VT.count) nessesaryColumnCount, 0 unnesesarryColumnCount \n\t  from (SELECT COUNT(c.column_name) count FROM information_schema.columns c \n\t\t\tWHERE c.table_schema='public' AND c.table_name = 'items' \n\t\t\tAND c.column_name = 'id' AND c.data_type = 'integer' \n\t\t\tUNION ALL SELECT COUNT(c.column_name) FROM information_schema.columns c \n\t\t\tWHERE c.table_schema='public' AND c.table_name = 'items' AND c.column_name = 'name' \n\t\t\tAND c.data_type = 'character varying' UNION ALL SELECT COUNT(c.column_name) \n\t\t\tFROM information_schema.columns c WHERE c.table_schema='public' AND c.table_name = 'items' \n\t\t\tAND c.column_name = 'description' AND c.data_type = 'character varying' \n\t\t\tUNION ALL SELECT COUNT(c.column_name) FROM information_schema.columns c \n\t\t\tWHERE c.table_schema='public' AND c.table_name = 'items' AND c.column_name = 'created' \n\t\t\tAND c.data_type = 'bigint') VT UNION ALL SELECT 0, COUNT  (c.column_name) FROM information_schema.columns c \n\t  WHERE c.table_schema='public' AND c.table_name = 'items' AND NOT c.column_name IN ('id','name','description','created') \n\t  AND c.is_nullable = 'NO') VT");
        PreparedStatement ps = this.connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            result = rs.getInt("nessesaryColumnCount") == 4 && rs.getInt("unnesesarryColumnCount") == 0;
        }
        rs.close();
        ps.close();
        return result;
    }

    /**
     * Method make rename don't correct table.
     *
     * @throws SQLException
     */
    private void renameExistingTable() throws SQLException {
        String query = "ALTER TABLE items DROP CONSTRAINT \"PK_items_id\";\n"
                + "ALTER TABLE items RENAME TO items_old";
        PreparedStatement ps = this.connection.prepareStatement(query);
        ps.execute();
    }

    /**
     * Method creat table.
     *
     * @throws SQLException
     */
    private void createItemsTable() throws SQLException {
        String query = String.format("CREATE TABLE public.items\n(\n  id serial,\n  name character varying,\n  description character varying,\n  created bigint,\n  CONSTRAINT \"PK_items_id\" PRIMARY KEY (id)\n)");
        PreparedStatement ps = this.connection.prepareStatement(query);
        ps.execute();
    }

    /**
     * Method creates item.
     *
     * @param item
     * @return
     */
    @Override
    public Item add(Item item) {
        String query = "INSERT INTO public.items(name, description, created) VALUES (?,?,?);";
        try {
            PreparedStatement ps = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getName());
            ps.setString(2, item.getDescription());
            ps.setLong(3, item.getCreate());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                item.setId(rs.getString(1));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Method changes item.
     *
     * @param id
     * @param item
     */
    @Override
    public void replace(String id, Item item) {
        String query = String.format("UPDATE public.items SET name = ?,description = ?,created = ?WHERE id = ?");
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, item.getName());
            ps.setString(2, item.getDescription());
            ps.setLong(3, item.getCreate());
            ps.setInt(4, Integer.valueOf(id));
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method deletes one item.
     *
     * @param id
     */
    @Override
    public void delete(String id) {
        String query = "DELETE FROM public.items where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method deletes all item.
     */
    public void deleteAll() {
        String query = "DELETE FROM public.items";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method return all items
     */
    @Override
    public ArrayList<Item> findAll() {
        ArrayList<Item> result = new ArrayList<>();
        String query = "Select id, name, description, created FROM public.items";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new Item(rs.getString("id"), rs.getString("name"),
                        rs.getString("description"), rs.getLong("created")));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method return item be name.
     *
     * @param key
     * @return
     */
    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        String query = "Select id, name, description, created FROM public.items WHERE name = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new Item(rs.getString("id"), rs.getString("name"),
                        rs.getString("description"), rs.getLong("created")));

            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Item findById(String id) {
        Item result = null;
        String query = "select * from items where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, (Integer.parseInt(id)));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = new Item(rs.getString("id"), rs.getString("name"),
                        rs.getString("description"), rs.getLong("created"));
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