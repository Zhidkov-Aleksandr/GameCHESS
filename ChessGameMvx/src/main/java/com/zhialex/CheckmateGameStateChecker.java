package com.zhialex;

import com.zhialex.board.Board;
import com.zhialex.board.BoardFactory;
import com.zhialex.board.Move;
import com.zhialex.pieces.King;
import com.zhialex.pieces.Piece;

import java.util.List;
import java.util.Set;

// класс для проверки на мат
public class CheckmateGameStateChecker extends GameStateChecker {
    @Override
    public GameState check(Board board, Color color) {
        //допускаем что Король присутствует на доске (для FEN - кодов)
        Piece king = board.getPiecesByColor(color).stream().filter(piece -> piece instanceof King).findFirst().get();

        if (!board.isSquareAttackedByColor(king.coordinates, color.opposite())) {
            return GameState.ONGOING;
        }

        List<Piece> pieces = board.getPiecesByColor(color);
        for (Piece piece : pieces) {
            Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquares(board);
            //клонируем доску
            for (Coordinates coordinates : availableMoveSquares) {
                Board clone = new BoardFactory().copy(board);
                clone.makeMove(new Move(piece.coordinates, coordinates));
                Piece clonedKing = clone.getPiecesByColor(color).stream().filter(p -> p instanceof King).findFirst().get();
                //проверяем короля на клонированной доске
                if (!clone.isSquareAttackedByColor(clonedKing.coordinates, color.opposite())) {
                    return GameState.ONGOING;
                }
            }
        }
        if (color == Color.WHITE) {
            return GameState.CHECKMATE_TO_WHITE_KING; //мат белым
        } else {
            return GameState.CHECKMATE_TO_BLACK_KING; // мат черным
        }
    }
}


