package com.zhialex.pieces;

import com.zhialex.board.Board;
import com.zhialex.Coordinates;
import com.zhialex.Color;

import java.util.HashSet;
import java.util.Set;

//реализуем фигуру короля
public class King extends Piece {


    public King(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        Set<CoordinatesShift> result = new HashSet<>();

        for (int fileShift = -1; fileShift <= 1; fileShift++){
            for (int rankShift = -1; rankShift <= 1; rankShift++){
                if ((fileShift == 0) && (rankShift ==0)){
                    continue;
                }
                result.add(new CoordinatesShift(fileShift, rankShift));
            }
        }

        return result;
    }

    @Override
    protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        boolean result = super.isSquareAvailableForMove(coordinates, board);

        if (result) {
            return  !board.isSquareAttackedByColor(coordinates, color.opposite());

        }
        return false;
    }
}
