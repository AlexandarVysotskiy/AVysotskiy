package paterns.abstractfactory.factory;

import paterns.abstractfactory.details.Body;
import paterns.abstractfactory.details.Engine;
import paterns.abstractfactory.details.Transmission;

public interface Volkswagen {
    Body createBody();

    Engine createEngine();

    Transmission createTransmission();
}