package paterns.facade;

/**
 * Class Client
 * <p>
 * This pattern uses when there is important to make many action only one method.
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public class Client {
    public static void main(String[] args) {
        Party party = new Party();

        party.doParty();
        System.out.println();
        System.out.println("When policeman is knocking to door");
        System.out.println();
        party.dontParty();
    }
}
