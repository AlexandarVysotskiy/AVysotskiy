package ru.job4j.tracker;

/**
 * Класс реализует редактирование заявок в хранилище.
 */
class EditItem implements UserAction {
    public int key() {
        return 2;
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

    public String info() {
        return String.format("%s. %s", this.key(), "Редактирование заявки");
    }
}

/**
 * @version $Id$
 * @since 0.1
 */
public class MenuTracker {
    private Input input;
    private Tracker tracker;
    public UserAction[] actions = new UserAction[6];

    MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        this.actions[0] = this.new AddItem();
        this.actions[1] = new MenuTracker.ShowItems();
        this.actions[2] = new EditItem();
        this.actions[3] = new DeleteItem();
        this.actions[4] = new SearchById();
        this.actions[5] = new SearchByName();
    }

    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
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
    private class AddItem implements UserAction {
        public int key() {
            return 0;
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки :");
            String description = input.ask("Введите описание заявки :");
            long time = 123L;
            Item item = new Item(name, description, time);
            tracker.add(item);
            System.out.println("------------ Добавленна новая заявка с ID: " + item.getId() + "-----------");

        }

        public String info() {
            return String.format("%s. %s", this.key(), "Добавление новой заявки");
        }
    }

    /**
     * Класс показывает список всех заявок.
     */
    private static class ShowItems implements UserAction {
        public int key() {
            return 1;
        }

        public void execute(Input input, Tracker tracker) {
            Item[] items = tracker.findAll();
            System.out.println("Список всех заявок:" + items.length);
            for (Item item : items) {
                System.out.println("Имя: " + item.getName() + " ID: " + item.getId());
            }
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Показать все заявки");
        }
    }

    /**
     * Класс удаляет заявку в хранилище.
     */
    private class DeleteItem implements UserAction {
        public int key() {
            return 3;
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

        public String info() {
            return String.format("%s. %s", this.key(), "Удаление заявки");
        }
    }

    /**
     * Класс исчет заявку по ID.
     */
    private class SearchById implements UserAction {
        public int key() {
            return 4;
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявки по ID --------------");
            String question = input.ask("Введите ID заявки :");
            Item item = tracker.findById(question);
            if (item != null) {
                Item[] items = {tracker.findById(question)};
                System.out.println("Найдено заявок: " + items.length);
                for (Item index : items) {
                    System.out.println("Имя: " + index.getName() + " ID: " + index.getId());
                }
                tracker.findById(question);
            } else {
                System.out.println("Заявка не найдена, выберите другой ID");
            }
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Поиск заявки по ID");
        }
    }

    /**
     * Класс исчет заявку по имени.
     */
    private class SearchByName implements UserAction {
        public int key() {
            return 5;
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявки по имени --------------");
            String question = input.ask("Введите имя заявки :");
            Item[] items = tracker.findByName(question);
            System.out.println("Найдено заявок: " + items.length);
            for (Item item : items) {
                System.out.println("Имя: " + item.getName() + " ID: " + item.getId());
            }
            tracker.findByName(question);
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Поиск заявки по имени");
        }
    }
}
