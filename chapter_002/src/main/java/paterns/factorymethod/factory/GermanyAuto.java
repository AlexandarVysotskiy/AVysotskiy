package paterns.factorymethod.factory;

import paterns.factorymethod.auto.Audi;
import paterns.factorymethod.auto.Auto;
import paterns.factorymethod.auto.BMW;
import paterns.factorymethod.auto.Mercedes;

/**
 * Class GermanyAuto.
 * Germany factory will produce Mercedes, AudiRS8 and BMW.
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 * @since
 */

public class GermanyAuto extends Factory {
    Auto createAuto(String brand) {
        if (brand.equals("Mercedes")) {
            newAuto = new Mercedes();
        } else if (brand.equals("AudiRS8")) {
            newAuto = new Audi();
        } else if (brand.equals("BMW")) {
            newAuto = new BMW();
        } else {
            newAuto = null;
        }
        return newAuto;
    }
}
