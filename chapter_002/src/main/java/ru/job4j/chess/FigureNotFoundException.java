package ru.job4j.chess;

public class FigureNotFoundException extends RuntimeException {
    FigureNotFoundException(String msg){
        super (msg);
    }
}
