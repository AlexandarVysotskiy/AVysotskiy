package paterns.adapter;

/**
 * Class AmericanCharger
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public class AmericanCharger implements AmericanSocket {
    @Override
    public void insert() {
        System.out.println("American charger is being inserted");
    }

    @Override
    public void charge() {
        System.out.println("American charger is charging");
    }
}
