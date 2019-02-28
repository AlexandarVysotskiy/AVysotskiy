package paterns.commands;

/**
 * Class StopCommand is concrete command;
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public class StopCommand extends Command {
    public StopCommand(TV tv) {
        super(tv);
    }

    @Override
    void execute() {
        tv.stop();
    }
}
