package com.zhialex;

import com.zhialex.board.Board;
import com.zhialex.board.Move;

import java.util.List;

public class Game {
    private final Board board;

    private BoardConsoleRenderer renderer = new BoardConsoleRenderer();

    private final List<GameStateChecker> checkers = List.of(
            new StalemateGameStateChecker(),
            new CheckmateGameStateChecker()
    );
    public Game(Board board) {
        this.board = board;
    }


    public void gameLoop() {
        Color colorToMove = Color.WHITE; //определяем, чей сейчас ход

        GameState state = determineGameState(board, colorToMove);

        while (state == GameState.ONGOING) {
            renderer.render(board); //рендерим доску
            if (colorToMove == Color.WHITE) {
                System.out.println("Hod belih");
            } else {
                System.out.println("Hod chernyh");
            }

            Move move = InputCoordinates.inputMove(board, colorToMove, renderer);

            // делаем ход
            board.makeMove(move);

            // передаем ход второму игроку
            colorToMove = colorToMove.opposite();

            state = determineGameState(board, colorToMove);
        }

        renderer.render(board);
        System.out.println("Igra zakonchena s rezultatom = " + state);
    }

    private GameState determineGameState(Board board, Color color) {
        for (GameStateChecker checker : checkers){
            GameState state = checker.check(board, color);

            if (state != GameState.ONGOING) {
                return state;
            }
        }

        return GameState.ONGOING;
    }
}
