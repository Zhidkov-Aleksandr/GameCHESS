package com.zhialex.board;

import com.zhialex.Coordinates;
import com.zhialex.File;

import java.util.ArrayList;
import java.util.List;

public class BoardUtils {
    //отсекаем недоступные ходы по диагонали
    public static List<Coordinates> getDiagonalCoordinatesBetween(Coordinates source, Coordinates target) {


        List<Coordinates> result = new ArrayList<>();

        int fileShift = source.file.ordinal() < target.file.ordinal() ? 1 : -1;
        int rankShift = source.rank < target.rank ? 1 : -1;

        for (
                int fileIndex = source.file.ordinal() + fileShift,
                rank = source.rank + rankShift;

                fileIndex != target.file.ordinal() && rank != target.rank;

                fileIndex += fileShift, rank += rankShift
        ) {
            result.add(new Coordinates(File.values()[fileIndex], rank));
        }


        return result;
    }

    //отсекаем недоступные ходы по вертикали
    public static List<Coordinates> getVerticalCoordinatesBetween(Coordinates source, Coordinates target) {


        List<Coordinates> result = new ArrayList<>();

        int rankShift = source.rank < target.rank ? 1 : -1;

        for (int rank = source.rank + rankShift; rank != target.rank; rank += rankShift) {
            result.add(new Coordinates(source.file, rank));
        }


        return result;
    }

    //отсекаем недоступные ходы по горизонтали
    public static List<Coordinates> getHorizontalCoordinatesBetween(Coordinates source, Coordinates target) {


        List<Coordinates> result = new ArrayList<>();

        int fileShift = source.file.ordinal() < target.file.ordinal() ? 1 : -1;


        for (
                int fileIndex = source.file.ordinal() + fileShift; fileIndex != target.file.ordinal(); fileIndex += fileShift) {
            result.add(new Coordinates(File.values()[fileIndex], source.rank));
        }


        return result;
    }

    public static void main(String[] args) {
        List<Coordinates> list = getVerticalCoordinatesBetween(new Coordinates(File.D, 4), new Coordinates(File.G, 7));
        System.out.println("List = " + list);
    }

}
