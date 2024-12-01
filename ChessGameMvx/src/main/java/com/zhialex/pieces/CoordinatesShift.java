package com.zhialex.pieces;

//класс описывает сдвиг по горизонтали и вертикали фигуры во время хода
public class CoordinatesShift {
    public CoordinatesShift(int fileShift, int rankShift) {
        this.fileShift = fileShift;
        this.rankShift = rankShift;
    }

    public final int fileShift;
    public final int rankShift;
}
