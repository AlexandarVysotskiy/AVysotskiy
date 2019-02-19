package paterns.decorator;

/**
 * Авто марки BMW
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public class BMW extends Auto {

    public BMW() {
        description = " BMW";
    }

    @Override
    public double cost() {
        return 30000;
    }
}
