package paterns.decorator;

/**
 * Дополнительная опция кондиционер.
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public class Condition extends Option {
    Auto auto;

    public Condition(Auto auto) {
        this.auto = auto;
    }

    @Override
    public String getDescription() {
        return auto.getDescription() + ", Condition ";
    }

    @Override
    public double cost() {
        return auto.cost() + 100;
    }
}
