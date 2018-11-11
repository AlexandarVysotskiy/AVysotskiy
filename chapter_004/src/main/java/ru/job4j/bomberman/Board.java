package ru.job4j.bomberman;

import jdk.nashorn.internal.ir.annotations.Immutable;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.locks.ReentrantLock;

@ThreadSafe
public class Board {
    @GuardedBy("this")
    private final ReentrantLock[][] board;

    @Immutable
    private final int width;

    @Immutable
    private final int height;

    Board(int width, int height) {
        this.width = width;
        this.height = height;

        board = new ReentrantLock[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    /**
     * @return This is method return locked cell by coordinates.
     */
    public ReentrantLock getCell(int x, int y) {
        return this.board[x][y];
    }

    /**
     * The method is blocking a cell of field, where must not going.
     */
    public void cellLock(int x, int y) {
        getCell(x, y).lock();
    }
}
