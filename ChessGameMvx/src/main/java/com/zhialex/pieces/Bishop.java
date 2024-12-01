package com.zhialex.pieces;

import com.zhialex.Coordinates;
import com.zhialex.Color;

import java.util.Set;

//реализуем фигуру слона
public class Bishop extends LongRangePiece implements IBishop {


    public Bishop(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
       return getBishopMoves();
    }
}

