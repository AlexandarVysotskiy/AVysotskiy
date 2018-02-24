package ru.job4j.Tracker;

/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";

    /**
     * Константа меню для вывода списка всех заявок.
     */
    private static final String SHOW = "1";

    /**
     * Константа меню для редактирования заявок
     */
    private static final String EDIT = "2";

    /**
     * Константа меню для удаления заявок
     */
    private static final String DELETE = "3";

    /**
     * Константа меню для поиска по ID
     */
    private static final String FIND_BY_ID = "4";

    /**
     * Константа меню для поиска по имени
     */
    private static final String FIND_BY_NAME = "5";

    /**
     * Константа меню для выхода из цикла.
     */
    private static final String EXIT = "6";
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     *
     * @param input   ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW.equals(answer)) {
                this.findAllItems();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FIND_BY_ID.equals(answer)) {
                this.searchById();
            } else if (FIND_BY_NAME.equals(answer)) {
                this.searchByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String description = this.input.ask("Введите описание заявки :");
        long time = 123l;
        Item item = new Item(name, description, time);
        this.tracker.add(item);
        System.out.println("------------ Добавленна новая заявка с ID: " + item.getId() + "-----------");

    }

    /**
     * Метод реализует список всех заявок.
     */
    private void findAllItems() {
        Item[] items = this.tracker.findAll();
        System.out.println("Найдено заявок " + items.length);
        for (Item item : items) {
            System.out.println("Имя: " + item.getName() + " ID: " + item.getId());
        }
    }

    /**
     * Метод реализует редактирование заявок в хранилище.
     */
    private void editItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String question = this.input.ask("Введите ID редактируемой заявки :");
        Item item = this.tracker.findById(question);
        if (item != null) {
            String name = this.input.ask("Введите новое имя заявки :");
            String description = this.input.ask("Введите новое описание заявки :");
            long time = 0;
            Item itemEdit = new Item(name, description, time);
            this.tracker.replace(question, itemEdit);
        } else {
            System.out.println("Заявка не найдена, выберите другой ID");
        }
    }

    /**
     * Метод удаляет заявку в хранилище.
     */
    private void deleteItem() {
        System.out.println("------------ Удаление заявки --------------");
        String question = this.input.ask("Введите ID удаяемой заявки :");
        Item item = this.tracker.findById(question);
        if (item != null) {
            this.tracker.delete(question);
        } else {
            System.out.println("Заявка не найдена, выберите другой ID");
        }
    }

    /**
     * Метод исчет заявку по ID.
     */
    private void searchById() {
        System.out.println("------------ Поиск заявки по ID --------------");
        String question = this.input.ask("Введите ID заявки :");
        Item item = this.tracker.findById(question);
        if (item != null) {
            Item[] items = {this.tracker.findById(question)};
            System.out.println("Найдено заявок: " + items.length);
            for (Item index : items) {
                System.out.println("Имя: " + index.getName() + " ID: " + index.getId());
            }
            this.tracker.findById(question);
        } else {
            System.out.println("Заявка не найдена, выберите другой ID");
        }
    }

    /**
     * Метод исчет заявку по имени.
     */
    private void searchByName() {
        System.out.println("------------ Поиск заявки по имени --------------");
        String question = this.input.ask("Введите имя заявки :");
        Item[] items = this.tracker.findByName(question);
        System.out.println("Найдено заявок: " + items.length);
        for (Item item : items) {
            System.out.println("Имя: " + item.getId() + " ID: " + item.getId());
        }
        this.tracker.findByName(question);

    }

    private void showMenu() {
        System.out.println("Выберети пункт меню:");
        System.out.println("0. Добавление новой заявки");
        System.out.println("1. Показать все заявки");
        System.out.println("2. Редактирование заявки");
        System.out.println("3. Удаление заявки");
        System.out.println("4. Поиск заявки по ID");
        System.out.println("5. Поиск заявки по имени");
        System.out.println("6. Выход из программы");
    }

    /**
     * Запускт программы.
     *
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}