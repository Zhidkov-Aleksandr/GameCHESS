package com.zhialex.pieces;

import com.zhialex.Coordinates;
import com.zhialex.Color;
import com.zhialex.pieces.Piece;

import java.util.Set;

//реализуем фигуру ферзя
public class Queen extends LongRangePiece implements IRook, IBishop {

    public Queen(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        Set <CoordinatesShift> moves = getBishopMoves();
        moves.addAll(getRookMoves());

        return moves;
    }
}
