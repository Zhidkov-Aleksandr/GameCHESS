package com.zhialex;

import com.zhialex.pieces.CoordinatesShift;

public class Coordinates {
    public final File file; // вертикаль доски
    public final Integer rank; // горизонталь доски

    public Coordinates(File file, Integer rank) {
        this.file = file;
        this.rank = rank;
    }

    //сдвигаем координатy
    public Coordinates shift(CoordinatesShift shift) {
        return new Coordinates(File.values()[this.file.ordinal() + shift.fileShift], this.rank + shift.rankShift);
    }

    //проверка на возможность сдвига координат
    public boolean canShift(CoordinatesShift shift) {
        int f = file.ordinal() + shift.fileShift;
        int r = rank + shift.rankShift;
        if ((f < 0) || (f > 7)) return false;
        if ((r < 1) || (r > 8)) return false;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (file != that.file) return false;
        return rank.equals(that.rank);
    }

    //хэшкод вычисляет уникальные численные значения на основании полей
    @Override
    public int hashCode() {
        int result = file.hashCode();
        result = 31 * result + rank.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return file + String.valueOf(rank);
    }
}
