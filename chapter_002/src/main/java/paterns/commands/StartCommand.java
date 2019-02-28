package paterns.commands;

/**
 * Class StartCommand is concrete command;
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public class StartCommand extends Command {
    public StartCommand(TV tv) {
        super(tv);
    }

    @Override
    void execute() {
        tv.start();
    }
}
