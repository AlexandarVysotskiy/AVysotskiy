package paterns.abstractfactory.details;

/**
 * Class Diesel
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public class Diesel implements Engine {
    @Override
    public String getEngine() {
        return " Diesel";
    }
}
