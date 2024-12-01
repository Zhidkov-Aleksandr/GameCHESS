package com.zhialex;

import com.zhialex.board.Board;
import com.zhialex.board.BoardFactory;

public class Main {
    public static void main(String[] args) {
//        Board board = new Board();
//        board.setupDefaultPiecesPositions();


        Board board = (new BoardFactory()).fromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq"); //вставляем сюда FEN код расстановки фигур

        BoardConsoleRenderer renderer = new BoardConsoleRenderer();
//        renderer.render(board);
//
//
//        Piece piece = board.getPiece(new Coordinates(File.G, 8));
//        Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquares(board);
//
//        int a = 123;


        Game game = new Game(board);
        game.gameLoop();
    }
}
