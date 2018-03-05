package ru.job4j.tracker;

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
        Tracker tracker = new Tracker();
        MenuTracker menu = new MenuTracker(this.input, tracker);
        menu.fillActions();
        do {
            menu.show();
            int key = Integer.valueOf(input.ask("Выберети пункт меню:"));
            menu.select(key);
        } while (!"y".equals(this.input.ask("Для выхода из программы нажмите (y):")));
    }

    /**
     * Запускт программы.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}