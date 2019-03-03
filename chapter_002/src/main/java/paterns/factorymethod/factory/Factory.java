package paterns.factorymethod.factory;

import paterns.factorymethod.auto.Auto;

/**
 * Class Factory.
 * Base factory class.
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public abstract class Factory {
    Auto newAuto;

    public Auto orderAuto(String brand) {
        newAuto = createAuto(brand);
        return newAuto;
    }

    abstract Auto createAuto(String brand);
}