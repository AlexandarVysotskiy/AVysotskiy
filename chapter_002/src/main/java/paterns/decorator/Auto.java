package paterns.decorator;

/**
 * Исходный класс авто без бренда и цены.
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public abstract class Auto {
    String description = "Unknown auto";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
