package paterns.decorator;

/**
 * За счет инкопсуляции и наследования используемую в этом паттерне, клиент может докапликтовать
 * нужные ему опции к его авто и не платить за не нужные.
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public class Client {
    public static void main(String[] args) {
        Auto bmw = new BMW();
        bmw = new SkinSalon(bmw);
        bmw = new Condition(bmw);
        System.out.println(bmw.getDescription() + bmw.cost() + "$");

        Auto opel = new Opel();
        opel = new Condition(opel);
        opel = new AutomaticTransmission(opel);
        System.out.println(opel.getDescription() + opel.cost());
    }
}
