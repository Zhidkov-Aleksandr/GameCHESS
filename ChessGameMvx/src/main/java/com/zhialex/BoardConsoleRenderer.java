package com.zhialex;


import com.zhialex.board.Board;
import com.zhialex.pieces.Piece;

import java.util.Set;

import static java.util.Collections.emptySet;

//визуализация доски
public class BoardConsoleRenderer {
    //добавляем цвета шахмотных клеточек
    public static final String ANSI_RESET = "\u001b[0m"; //сброс цвета
    public static final String ANSI_WHITE_PIECE_COLOR = "\u001B[97m";
    public static final String ANSI_BLACK_PIECE_COLOR = "\u001B[30m";
    public static final String ANSI_WHITE_SQUARE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_BLACK_SQUARE_BACKGROUND = "\u001b[0;100m";
    public static final String ANSI_HIGHLIGHTED_SQUARE_BACKGROUND = "\u001B[45m";


    public void render(Board board, Piece pieceToMove) {
        Set<Coordinates> availableMoveSquares = emptySet();
        if (pieceToMove != null) {
            availableMoveSquares = pieceToMove.getAvailableMoveSquares(board);
        }

        for (int rank = 8; rank >= 1; rank--) {
            String line = "";
            for (File file : File.values()) {
                Coordinates coordinates = new Coordinates(file, rank);
                boolean isHighlight = availableMoveSquares.contains(coordinates);

                if (board.isSquareEmpty(coordinates)) {

                    line += getSpriteForEmptySquare(coordinates, isHighlight);
                } else {
                    line += getPieceSprite(board.getPiece(coordinates), isHighlight);
                }
            }
            line += ANSI_RESET;
            System.out.println(line);
        }
    }

    public void render(Board board) {
        render(board, null);
    }

    //раскрашиваем клетки
    private String colorizeSprite(String sprite, Color pieceColor, boolean isSquareDark, boolean isHighlight) {
        String result = sprite;
        if (pieceColor == Color.WHITE) {
            result = ANSI_WHITE_PIECE_COLOR + result;
        } else {
            result = ANSI_BLACK_PIECE_COLOR + result;
        }
        if (isHighlight) {
            result = ANSI_HIGHLIGHTED_SQUARE_BACKGROUND + result;

        } else if (isSquareDark) {
            result = ANSI_BLACK_SQUARE_BACKGROUND + result;
        } else {
            result = ANSI_WHITE_SQUARE_BACKGROUND + result;
        }
        return result;
    }

    private String getSpriteForEmptySquare(Coordinates coordinates, boolean isHighlight) {
        return colorizeSprite("    ", Color.WHITE, Board.isSquareDark(coordinates), isHighlight);

    }


    //отображение шахматных фигур вместо букв
    private String selectUnicodeSpriteForPiece(Piece piece) {
        switch (piece.getClass().getSimpleName()) {
            case "Pawn":
                return "Pw";
            case "Horse":
                return "Hr";
            case "Bishop":
                return "Bi";
            case "Rook":
                return "Rk";
            case "Queen":
                return "Qn";
            case "King":
                return "Kg";


        }
        return "";
    }

    private String getPieceSprite(Piece piece, boolean isHighlight) {
        return colorizeSprite(
                " " + selectUnicodeSpriteForPiece(piece) + " ",
                piece.color, Board.isSquareDark(piece.coordinates), isHighlight);//раскрашиваем клетки в разные цвета


    }
}