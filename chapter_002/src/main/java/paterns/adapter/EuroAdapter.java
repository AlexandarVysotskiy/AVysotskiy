package paterns.adapter;

/**
 * Class EuroAdapter
 *
 * This adapter give opportunity charging by American charger euro devices;
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public class EuroAdapter implements EuroSocket {
    AmericanCharger americanCharger;

    public EuroAdapter(AmericanCharger americanCharger) {
        this.americanCharger = americanCharger;
    }

    @Override
    public void insert() {
        americanCharger.insert();
    }

    @Override
    public void charge() {
        americanCharger.charge();
    }
}
