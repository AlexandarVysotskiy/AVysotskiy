package paterns.abstractfactory.factory;

import paterns.abstractfactory.details.*;

/**
 * Class AudiRS8
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public class AudiRS8 implements Volkswagen {

    public Body createBody() {
        return new Coupe();
    }

    public Engine createEngine() {
        return new Petrol();
    }

    public Transmission createTransmission() {
        return new Automatic();
    }

    public String getInfo() {
        return "Model Audi RS8 "
                + "body "
                + createBody().getBody()
                + "engine "
                + createEngine().getEngine()
                + "transmission "
                + createTransmission().getTransmission();
    }
}
