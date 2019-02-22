package paterns.factorymethod;

import paterns.factorymethod.Auto.Auto;
import paterns.factorymethod.factory.Factory;
import paterns.factorymethod.factory.GermanyAuto;
import paterns.factorymethod.factory.RussianAuto;

/**
 * Class Client
 *
 * Factory method is a creational design pattern which solves the problem
 * of creating product objects without specifying their concrete classes.
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public class Client {
    public static void main(String[] args) {
        Factory russianAuto = new RussianAuto();
        Factory germanyAuto = new GermanyAuto();

        Auto auto = russianAuto.orderAuto("Lada");
        System.out.println("Client A bought" + auto.getBrand());

        auto = germanyAuto.orderAuto("Audi");
        System.out.println("Client B bought" + auto.getBrand());
    }
}
