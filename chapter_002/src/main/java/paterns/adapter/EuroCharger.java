package paterns.adapter;

/**
 * Class EuroCharger
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public class EuroCharger implements EuroSocket {
    @Override
    public void insert() {
        System.out.println("Euro charger is being inserted");
    }

    @Override
    public void charge() {
        System.out.println("Euro charger is charging");
    }
}
