package ru.job4j.bomberman;

import jdk.nashorn.internal.ir.annotations.Immutable;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;

@ThreadSafe
public class Game {

    @Immutable
    private final Board board;

    @GuardedBy("this")
    private final ArrayList<Personage> personages;

    Game(int width, int height) {
        board = new Board(width, height);
        personages = new ArrayList<>();
    }

    /**
     * The method creating personage (monster or hero);
     */
    public void createPersonage(String name, int x, int y) {
        personages.add(new Personage(board, name, x, y));
    }

    /**
     * Creating lock cell
     */
    public void createBlockCell(int x, int y) {
        this.board.cellLock(x, y);
        System.out.println(String.format("Cell is locking x%s, y%s", x, y));
    }

    public void startGame() {
        for (Personage personage : personages) {
            new Thread(personage, personage.getNamePersonage()).start();
        }
    }
}