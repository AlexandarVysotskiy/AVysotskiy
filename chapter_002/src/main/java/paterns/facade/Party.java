package paterns.facade;

/**
 * Class Party
 *
 * @author Alexandar Vysotskiy
 * @version 1.0
 */

public class Party {
    private DiscoLight discoLight;
    private Light light;
    private Music music;

    public Party() {
        this.discoLight = new DiscoLight();
        this.light = new Light();
        this.music = new Music();
    }

    void doParty() {
        discoLight.on();
        light.off();
        music.on();
    }

    void dontParty() {
        discoLight.off();
        light.on();
        music.off();
    }
}
