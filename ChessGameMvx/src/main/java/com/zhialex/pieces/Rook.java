package com.zhialex.pieces;

import com.zhialex.Coordinates;
import com.zhialex.Color;

import java.util.Set;

//реализуем фигуру ладьи
public class Rook extends LongRangePiece implements IRook{


    public Rook(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        return getRookMoves();

    }
}