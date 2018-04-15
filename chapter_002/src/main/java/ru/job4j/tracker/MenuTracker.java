package ru.job4j.tracker;

import java.util.ArrayList;

/**
 * Класс реализует редактирование заявок в хранилище.
 */
class EditItem extends BaseAction {
    public EditItem(int key, String name) {
        super(key, name);
    }

    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Редактирование заявки --------------");
        String question = input.ask("Введите ID редактируемой заявки :");
        Item item = tracker.findById(question);
        if (item != null) {
            String name = input.ask("Введите новое имя заявки :");
            String description = input.ask("Введите новое описание заявки :");
            long time = 0;
            Item itemEdit = new Item(name, description, time);
            tracker.replace(question, itemEdit);
        } else {
            System.out.println("Заявка не найдена, выберите другой ID");
        }
    }
}

/**
 * @version $Id$
 * @since 0.1
 */
public class MenuTracker {
    private Input input;
    private Tracker tracker;
    public ArrayList<UserAction> actions = new ArrayList<>();

    MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        String nameAddItem = "Добавление новой заявки";
        this.actions.add(new MenuTracker.AddItem(0, nameAddItem));
        String nameShowItems = "Показать все заявки";
        this.actions.add(new MenuTracker.ShowItems(1, nameShowItems));
        String nameEditItem = "Редактирование заявки";
        this.actions.add(new EditItem(2, nameEditItem));
        String nameDeleteItem = "Удаление заявки";
        this.actions.add(new DeleteItem(3, nameDeleteItem));
        String nameSearchById = "Поиск заявки по ID";
        this.actions.add(new SearchById(4, nameSearchById));
        String nameSearchByName = "Поиск заявки по имени";
        this.actions.add(new SearchByName(5, nameSearchByName));
    }

    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Класс реализует добавленяи новый заявки в хранилище.
     */
    private class AddItem extends BaseAction {
        public AddItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки :");
            String description = input.ask("Введите описание заявки :");
            long time = 123L;
            Item item = new Item(name, description, time);
            tracker.add(item);
            System.out.println("------------ Добавленна новая заявка с ID: " + item.getId() + "-----------");

        }
    }

    /**
     * Класс показывает список всех заявок.
     */
    private static class ShowItems extends BaseAction {
        public ShowItems(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            ArrayList<Item> items = tracker.findAll();
            System.out.println("Список всех заявок:" + items.size());
            for (Item item : items) {
                System.out.println("Имя: " + item.getName() + " ID: " + item.getId());
            }
        }
    }

    /**
     * Класс удаляет заявку в хранилище.
     */
    private class DeleteItem extends BaseAction {
        public DeleteItem(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Удаление заявки --------------");
            String question = input.ask("Введите ID удаяемой заявки :");
            Item item = tracker.findById(question);
            if (item != null) {
                tracker.delete(question);
            } else {
                System.out.println("Заявка не найдена, выберите другой ID");
            }
        }
    }

    /**
     * Класс исчет заявку по ID.
     */
    private class SearchById extends BaseAction {
        public SearchById(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявки по ID --------------");
            String question = input.ask("Введите ID заявки :");
            Item item = tracker.findById(question);
            if (item != null) {
                ArrayList<Item> items = new ArrayList<>();
                items.add(tracker.findById(question));
                System.out.println("Найдено заявок: " + items.size());
                for (Item index : items) {
                    System.out.println("Имя: " + index.getName() + " ID: " + index.getId());
                }
                tracker.findById(question);
            } else {
                System.out.println("Заявка не найдена, выберите другой ID");
            }
        }
    }

    /**
     * Класс исчет заявку по имени.
     */
    private class SearchByName extends BaseAction {
        public SearchByName(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявки по имени --------------");
            String question = input.ask("Введите имя заявки :");
            ArrayList<Item> items = new ArrayList<>();
            items.add((Item) tracker.findByName(question));
            System.out.println("Найдено заявок: " + items.size());
            for (Item item : items) {
                System.out.println("Имя: " + item.getName() + " ID: " + item.getId());
            }
            tracker.findByName(question);
        }
    }
}