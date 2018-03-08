package ru.job4j.tracker;

import java.util.*;

public class Tracker {

    private final Item[] items = new Item[100];

    private int position = 0;

    private static final Random RN = new Random();

    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[position++] = item;
        return item;
    }

    public void replace(String id, Item item) {
        int index = findId(id);
        if (index != -1) {
            this.items[index] = item;
        }
    }

    public void delete(String id) {
        int index = findId(id);
        if (index != -1) {
            System.arraycopy(items, index + 1, items, index, this.position - index - 1);
            this.position--;
            this.items[position] = null;
        }
    }

    public Item[] findAll() {
        Item[] result = new Item[this.position];
        for (int index = 0; index != this.position; index++) {
            result[index] = this.items[index];
        }
        return result;
    }

    public Item[] findByName(String key) {
        int count = 0;
        int[] indexes = new int[this.items.length];
        for (int i = 0; i != this.position; i++) {
            if (items[i] != null && items[i].getName().equals(key)) {
                indexes[count] = i;
                count++;
            }
        }
        Item[] result = new Item[count];
        for (int index = 0; index != count; index++) {
            result[index] = items[indexes[index]];
        }
        return result;
    }

    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    public Item[] getAll() {
        Item[] result = new Item[this.position];
        for (int index = 0; index != this.position; index++) {
            result[index] = this.items[index];
        }
        return result;
    }

    /**
     * Вспомогательный метод. Метод исчет по передаваемому id нужную ячейку в массиве и возвращать ее индекс.
     */
    public int findId(String id) {
        int result = -1;
        for (int i = 0; i != this.position; i++) {
            if (items[i] != null && items[i].getId().equals(id)) {
                result = i;
                break;
            }
        }
        return result;
    }
}
