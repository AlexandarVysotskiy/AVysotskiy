package paterns.commands;

/**
 * Class Client
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public class Client {
    public static void main(String[] args) {
        TV tv = new TV();
        Controller c = new Controller(new StartCommand(tv), new StopCommand(tv), new ChangeChannel(tv));

        c.startTV();
        c.changeChannelTV();
        c.stopTV();
    }
}
