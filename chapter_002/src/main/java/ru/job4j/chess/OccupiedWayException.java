package ru.job4j.chess;

public class OccupiedWayException extends RuntimeException {
    OccupiedWayException(String msg){
        super (msg);
    }
}
