package paterns.commands;

/**
 * Class Controller saves commands and give request for implementation;
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public class Controller {
    private Command start;
    private Command stop;
    private Command changeChannel;

    public Controller(Command start, Command stop, Command changeChannel) {
        this.start = start;
        this.stop = stop;
        this.changeChannel = changeChannel;
    }

    void startTV() {
        start.execute();
    }

    void stopTV() {
        stop.execute();
    }

    void changeChannelTV() {
        changeChannel.execute();
    }
}
