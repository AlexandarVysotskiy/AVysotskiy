package ru.job4j.Tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertArrayEquals;

/**
 * @author Aleksandr Vysotskiiy (Aleksandr.vysotskiiy@gmail.com)
 * @version 1.0
 * @since 0.1
 */

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.getAll()[0], is(item));
    }

    @Test
    public void whenDelete() {
        Item itemFirst = new Item("test1", "testDescription1", 121L);
        Item itemSecond = new Item("test2", "testDescription2", 122L);
        Item itemThird = new Item("test3", "testDescription3", 123L);
        Item itemFourth = new Item("test4", "testDescription4", 124L);
        Tracker tracker = new Tracker();
        tracker.add(itemFirst);
        tracker.add(itemSecond);
        tracker.add(itemThird);
        tracker.add(itemFourth);
        Item[] expected = {itemFirst, itemSecond, itemFourth};
        tracker.delete(itemThird.getId());
        assertArrayEquals(tracker.findAll(), expected);
    }

    @Test
    public void testMethodFindAll() {
        Item itemFirst = new Item("test1", "testDescription1", 121L);
        Item itemSecond = new Item("test2", "testDescription2", 122L);
        Item itemThird = new Item("test3", "testDescription3", 123L);
        Item itemFourth = new Item("test4", "testDescription4", 124L);
        Tracker tracker = new Tracker();
        tracker.add(itemFirst);
        tracker.add(itemSecond);
        tracker.add(itemThird);
        tracker.add(itemFourth);
        Item[] expected = {itemFirst, itemSecond, itemThird, itemFourth};
        assertArrayEquals(expected, tracker.findAll());
    }

    @Test
    public void testMethodFindByName() {
        Tracker tracker = new Tracker();
        String key = "test1";
        Item itemFirst = new Item("test1", "testDescription1", 121L);
        Item itemSecond = new Item("test1", "testDescription2", 122L);
        tracker.add(itemFirst);
        tracker.add(itemSecond);
        Item[] array = {itemFirst, itemSecond};
        assertThat(tracker.findByName(key), is(array));
    }

    @Test
    public void testMethodFindById(){
        Tracker tracker = new Tracker();
        Item itemFirst = new Item("test1", "testDescription1", 121L);
        tracker.add(itemFirst);
        String id = itemFirst.getId();
        assertThat(tracker.findById(id), is (itemFirst));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2", "testDescription2", 1234L);
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.replace(previous.getId(), next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }
}
