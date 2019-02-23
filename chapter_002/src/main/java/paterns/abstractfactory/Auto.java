package paterns.abstractfactory;

import paterns.abstractfactory.details.Body;
import paterns.abstractfactory.details.Engine;
import paterns.abstractfactory.details.Transmission;
import paterns.abstractfactory.factory.Volkswagen;

/**
 * Class Auto
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public class Auto {
    Body body;
    Engine engine;
    Transmission transmission;

    public Auto(Volkswagen volkswagen) {
        this.body = volkswagen.createBody();
        this.engine = volkswagen.createEngine();
        this.transmission = volkswagen.createTransmission();
    }
}
