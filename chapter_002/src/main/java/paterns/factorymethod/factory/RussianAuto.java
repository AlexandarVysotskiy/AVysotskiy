package paterns.factorymethod.factory;

import paterns.factorymethod.auto.Auto;
import paterns.factorymethod.auto.Lada;
import paterns.factorymethod.auto.UAZ;

/**
 * Class RussianAuto.
 * RussianAuto factory will produce Lada and UAZ.
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 * @since
 */

public class RussianAuto extends Factory {
    Auto createAuto(String brand) {
        if (brand.equals("Lada")) {
            newAuto = new Lada();
        } else if (brand.equals("UAZ")) {
            newAuto = new UAZ();
        } else {
            newAuto = null;
        }
        return newAuto;
    }
}
