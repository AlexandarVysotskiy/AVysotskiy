package paterns.abstractfactory.factory;

import paterns.abstractfactory.details.*;

/**
 * Class SkodaScala
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public class SkodaScala implements Volkswagen {
    public Body createBody() {
        return new Universal();
    }

    public Engine createEngine() {
        return new Diesel();
    }

    public Transmission createTransmission() {
        return new Manual();
    }
}
