package ru.job4j.chess;

public class Board {
    private Figure[] figures = new Figure[32];
    private int index = 0;

    void add(Figure figure) {
        this.figures[index++] = figure;
    }


    boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        if (!figureSearch(source)) {
            throw new FigureNotFoundException("Фигура не найдена");
        }
        for (Figure figure : figures) {
            if (figure.way(source, dest) == null) {
                throw new ImpossibleMoveException("Неверный ход");
            }
            Cell[] way = figure.way(source, dest);
            for (Cell cell : way) {
                if (cell.equals(figure.position)) {
                    throw new OccupiedWayException("Путь занят");
                }
                figure.copy(dest);
            }
            figure.copy(dest);
            break;
        }
        return true;
    }

    private boolean figureSearch(Cell cell) {
        boolean result = false;
        for (int i = 0; i <= this.figures.length; i++) {
            if (this.figures[i].position.equals(cell)) {
                result = true;
                break;
            }
        }
        return result;
    }
}