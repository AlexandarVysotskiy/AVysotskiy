package paterns.decorator;

/**
 * Авто марки опель
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public class Opel extends Auto {

    public Opel() {
        description = " Opel";
    }

    @Override
    public double cost() {
        return 15000;
    }
}
