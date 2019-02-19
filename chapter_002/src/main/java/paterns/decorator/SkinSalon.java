package paterns.decorator;

/**
 * Дополнительная кожанный салон.
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public class SkinSalon extends Option {
    private Auto auto;

    public SkinSalon(Auto auto) {
        this.auto = auto;
    }

    @Override
    public String getDescription() {
        return auto.getDescription() + " Skin salon ";
    }

    @Override
    public double cost() {
        return auto.cost() + 500;
    }
}
