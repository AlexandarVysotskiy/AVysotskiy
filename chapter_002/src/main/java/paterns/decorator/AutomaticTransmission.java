package paterns.decorator;

/**
 * Дополнительная опция коробка передач
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public class AutomaticTransmission extends Option {
    Auto auto;

    public AutomaticTransmission(Auto auto) {
        this.auto = auto;
    }

    @Override
    public String getDescription() {
        return auto.getDescription() + " Automatic transmission ";
    }

    @Override
    public double cost() {
        return auto.cost() + 1000;
    }
}
