package com.zhialex;

import com.zhialex.board.Board;
import com.zhialex.pieces.Piece;

import java.util.List;
import java.util.Set;

//класс для проверки на пат
public class StalemateGameStateChecker extends GameStateChecker{
    @Override
    public GameState check(Board board, Color color) {
        List<Piece> pieces = board.getPiecesByColor(color);

        for(Piece piece : pieces){
           Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquares(board);

           if (availableMoveSquares.size() >0) {
               return GameState.ONGOING;
           }
        }

        return GameState.STALEMATE;
    }
}
