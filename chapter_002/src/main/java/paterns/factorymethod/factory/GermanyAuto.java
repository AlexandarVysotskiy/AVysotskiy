package paterns.factorymethod.factory;

import paterns.factorymethod.Auto.Audi;
import paterns.factorymethod.Auto.Auto;
import paterns.factorymethod.Auto.BMW;
import paterns.factorymethod.Auto.Mercedes;

/**
 * Class GermanyAuto.
 * Germany factory will produce Mercedes, Audi and BMW.
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 * @since
 */

public class GermanyAuto extends Factory {
    Auto createAuto(String brand) {
        if (brand.equals("Mercedes")) {
            newAuto = new Mercedes();
        } else if (brand.equals("Audi")) {
            newAuto = new Audi();
        } else if (brand.equals("BMW")) {
            newAuto = new BMW();
        } else {
            newAuto = null;
        }
        return newAuto;
    }
}
