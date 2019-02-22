package paterns.factorymethod.factory;

import paterns.factorymethod.Auto.Auto;
import paterns.factorymethod.Auto.Lada;
import paterns.factorymethod.Auto.UAZ;

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
