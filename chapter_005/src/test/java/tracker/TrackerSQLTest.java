package tracker;

import org.hamcrest.Matchers;
import org.junit.Test;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Test TrackerSQL.
 *
 * @version 1.0.
 * @since 24.11.2018.
 */
public class TrackerSQLTest {

    @Test
    public void checkConnection() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            try (TrackerSQL sql = new TrackerSQL(config)) {
                assertThat(sql.init(), is(true));
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void whenAddItem() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            try (TrackerSQL trackerSQL = new TrackerSQL(config)) {
                trackerSQL.init();
                Item item = new Item("Printer", "Fixed the printer", new Date().getTime());
                trackerSQL.add(item);
                assertThat(item.getName(), is("Printer"));
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void whenDeleteItem() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            try (TrackerSQL trackerSQL = new TrackerSQL(config)) {
                trackerSQL.init();
                trackerSQL.deleteAll();
                Item item = new Item("For delete", "This is item for delete test", new Date().getTime());
                trackerSQL.add(item);
                trackerSQL.delete(item.getId());
                assertNull(trackerSQL.findById(item.getId()));
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void whenFindById() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            try (TrackerSQL trackerSQL = new TrackerSQL(config)) {
                trackerSQL.init();
                trackerSQL.deleteAll();
                Item item = new Item("Name", "Description of find by id test", new Date().getTime());
                item = trackerSQL.add(item);
                Item result = trackerSQL.findById((item.getId()));
                assertThat(result, notNullValue());
                assertThat(result.equals(item), is(true));
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void whenFindByName() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            try (TrackerSQL trackerSQL = new TrackerSQL(config)) {
                trackerSQL.init();
                trackerSQL.deleteAll();
                trackerSQL.add(new Item("Имя1", "Описание1", new Date().getTime()));
                trackerSQL.add(new Item("Имя2", "Описание2", new Date().getTime()));
                trackerSQL.add(new Item("Имя2", "Описание3", new Date().getTime()));
                List<Item> list = trackerSQL.findByName("Имя2");
                assertThat(list.size(), is(2));
                assertThat(list.get(0).getDescription(), Matchers.isOneOf("Описание2", "Описание3"));
                assertThat(list.get(1).getDescription(), Matchers.isOneOf("Описание2", "Описание3"));
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void whenReplaceByName() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            try (TrackerSQL trackerSQL = new TrackerSQL(config)) {
                trackerSQL.init();
                trackerSQL.deleteAll();
                Item one = new Item("Name 1", "Description 1", new Date().getTime());
                Item two = new Item("Name 2", "Description 2", new Date().getTime());
                trackerSQL.add(one);
                trackerSQL.replace(one.getId(), two);
                assertThat(trackerSQL.findByName("Name 2"), is(notNullValue()));
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void findByAllTest() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            try (TrackerSQL trackerSQL = new TrackerSQL(config)) {
                trackerSQL.init();
                trackerSQL.deleteAll();
                Item one = new Item("Name 1", "Description 1", new Date().getTime());
                Item two = new Item("Name 2", "Description 2", new Date().getTime());
                trackerSQL.add(one);
                trackerSQL.add(two);
                assertThat("Name 1", is(trackerSQL.findAll().get(0).getName()));
                assertThat("Name 2", is(trackerSQL.findAll().get(1).getName()));
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}