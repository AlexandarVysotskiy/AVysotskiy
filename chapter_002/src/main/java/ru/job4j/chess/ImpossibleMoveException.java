package ru.job4j.chess;

public class ImpossibleMoveException extends RuntimeException {
    ImpossibleMoveException(String msg) {
        super(msg);
    }
}
