package com.zhialex.pieces;

import com.zhialex.Coordinates;
import com.zhialex.Color;
import com.zhialex.pieces.Piece;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//реализуем фигуру коня
public class Horse extends Piece {

    //описываем возможные ходы коня - массивом сдвигов
    public Horse(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        return new HashSet<>(Arrays.asList(
                new CoordinatesShift(1, 2),
                new CoordinatesShift(2, 1),
                new CoordinatesShift(2, -1),
                new CoordinatesShift(1, -2),
                new CoordinatesShift(-2, -1),
                new CoordinatesShift(-1, -2),
                new CoordinatesShift(-2, 1),
                new CoordinatesShift(-1, 2)
        ));
    }
}
