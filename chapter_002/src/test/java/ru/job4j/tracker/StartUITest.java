package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Aleksandr Vysotskiiy (Aleksandr.vysotskiiy@gmail.com)
 * @version 1.0
 * @since 0.1
 */

public class StartUITest {
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();     // создаём Tracker
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.getAll()[0].getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявку
        Item item = tracker.add(new Item("name", "decs", 123L));
        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"1", item.getId(), "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findById(item.getId()).getName(), is("name"));
    }

    @Test
    public void whenEditThenTrackerHasEditValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("name", "decs", 123L));
        Input input = new StubInput(new String[]{"2", item.getId(), "edit name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.getAll()[0].getName(), is("edit name"));
    }

    @Test
    public void whenDeleteItemThenTrackerNotHasItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("name", "decs", 123L));
        Input input = new StubInput(new String[]{"3", item.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().length, is(0));
    }

    @Test
    public void whenFindItemThenItemByID() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("name", "decs", 123L));
        Input input = new StubInput(new String[]{"4", item.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()), is(item));
    }

    @Test
    public void whenFindItemNameThenItemByName() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("name", "decs", 123L));
        Input input = new StubInput(new String[]{"5", item.getName(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.getAll()[0].getName(), is("name"));
    }
}
