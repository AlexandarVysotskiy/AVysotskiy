package paterns.adapter;

/**
 * Class Client
 * <p>
 * Test tests American charger in Euro socket;
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public class Client {
    public static void main(String[] args) {
        AmericanCharger americanCharger = new AmericanCharger();
        EuroCharger euroCharger = new EuroCharger();

        EuroAdapter euroAdapter = new EuroAdapter(americanCharger);

        euroChargerTest(euroCharger);
        euroChargerTest(euroAdapter);

    }

    static void euroChargerTest(EuroSocket socket) {
        socket.insert();
        socket.charge();
    }
}
