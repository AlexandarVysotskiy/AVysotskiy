package paterns.commands;

/**
 * Class ChangeChannel is concrete command;
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public class ChangeChannel extends Command {

    public ChangeChannel(TV tv) {
        super(tv);
    }

    @Override
    void execute() {
        tv.changeChannel();
    }
}
