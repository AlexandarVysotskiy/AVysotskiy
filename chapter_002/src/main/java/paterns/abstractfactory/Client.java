package paterns.abstractfactory;

import paterns.abstractfactory.factory.AudiRS8;
import paterns.abstractfactory.factory.SkodaScala;
import paterns.abstractfactory.factory.Volkswagen;

/**
 * Class Client
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public class Client {

    public static Auto configureApplication(String task) {
        Auto auto;
        Volkswagen volkswagen;

        if (task.equals("RS8")) {
            volkswagen = new AudiRS8();
            auto = new Auto(volkswagen);
        } else if (task.equals("Scala")) {
            volkswagen = new SkodaScala();
            auto = new Auto(volkswagen);
        } else {
            auto = null;
        }
        return auto;
    }

    public static void main(String[] args) {
        System.out.println("Model Audi RS8 " + "body: " + configureApplication("RS8").body.getBody() +
                " engine: " + configureApplication("RS8").engine.getEngine() +
                " transmission: " + configureApplication("RS8").transmission.getTransmission());

        System.out.println("Model Skoda Scala " + "body: " + configureApplication("Scala").body.getBody() +
                " engine: " + configureApplication("Scala").engine.getEngine() +
                " transmission: " + configureApplication("Scala").transmission.getTransmission());
    }
}
