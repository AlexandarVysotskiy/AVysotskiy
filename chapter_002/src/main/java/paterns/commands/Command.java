package paterns.commands;

/**
 * Class Command is common class for all concrete command.
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public abstract class Command {
    TV tv;

    public Command(TV tv) {
        this.tv = tv;
    }

    abstract void execute();
}
