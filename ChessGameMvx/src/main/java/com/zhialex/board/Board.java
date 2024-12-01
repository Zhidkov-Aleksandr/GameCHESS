package com.zhialex.board;

import com.zhialex.Color;
import com.zhialex.Coordinates;
import com.zhialex.File;
import com.zhialex.pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Board {
    public final String startingFen;
    private HashMap<Coordinates, Piece> pieces = new HashMap<>(); //ключами хеш мапы сделаем координаты а значениями фигуры

    public List <Move> moves = new ArrayList<>(); // создаем массив для истории ходов


    public Board(String startingFen) {
        this.startingFen = startingFen;
    }

    //устанавливаем фигуру по координатам
    public void setPieces(Coordinates coordinates, Piece piece) {
        piece.coordinates = coordinates;
        pieces.put(coordinates, piece);
    }

    //убираем фигуры с доски
    public void removePiece(Coordinates coordinates) {
        pieces.remove(coordinates);
    }

    //двигаем фигуры с какой на какую координату - перенос фигуры
    public void makeMove(Move move) {
        Piece piece = getPiece(move.from);

        removePiece(move.from); // удаляем фигуру по тем координатам где она была
        setPieces(move.to, piece); // добавляем фигуру по тем координатам, куда она будет добавлена

        moves.add(move); //записываем ход в массив ходов
    }

    //проверяем ячеку на пустоту
    public boolean isSquareEmpty(Coordinates coordinates) {
        return !pieces.containsKey(coordinates);
    }

    public Piece getPiece(Coordinates coordinates) {
        return pieces.get(coordinates);

    }

    //расставляем фигуры по доске
    public void setupDefaultPiecesPositions() {
        for (File file : File.values()) {
            setPieces(new Coordinates(file, 2), new Pawn(Color.WHITE, new Coordinates(file, 2)));
            setPieces(new Coordinates(file, 7), new Pawn(Color.BLACK, new Coordinates(file, 7)));
        }
        setPieces(new Coordinates(File.A, 1), new Rook(Color.WHITE, new Coordinates(File.A, 1)));
        setPieces(new Coordinates(File.H, 1), new Rook(Color.WHITE, new Coordinates(File.H, 1)));
        setPieces(new Coordinates(File.A, 8), new Rook(Color.BLACK, new Coordinates(File.A, 8)));
        setPieces(new Coordinates(File.H, 8), new Rook(Color.BLACK, new Coordinates(File.H, 8)));
        setPieces(new Coordinates(File.B, 1), new Horse(Color.WHITE, new Coordinates(File.B, 1)));
        setPieces(new Coordinates(File.G, 1), new Horse(Color.WHITE, new Coordinates(File.G, 1)));
        setPieces(new Coordinates(File.B, 8), new Horse(Color.BLACK, new Coordinates(File.B, 8)));
        setPieces(new Coordinates(File.G, 8), new Horse(Color.BLACK, new Coordinates(File.G, 8)));
        setPieces(new Coordinates(File.C, 1), new Bishop(Color.WHITE, new Coordinates(File.C, 1)));
        setPieces(new Coordinates(File.F, 1), new Bishop(Color.WHITE, new Coordinates(File.F, 1)));
        setPieces(new Coordinates(File.C, 8), new Bishop(Color.BLACK, new Coordinates(File.C, 8)));
        setPieces(new Coordinates(File.F, 8), new Bishop(Color.BLACK, new Coordinates(File.F, 8)));
        setPieces(new Coordinates(File.D, 1), new Queen(Color.WHITE, new Coordinates(File.D, 1)));
        setPieces(new Coordinates(File.E, 1), new King(Color.WHITE, new Coordinates(File.E, 1)));
        setPieces(new Coordinates(File.E, 8), new King(Color.BLACK, new Coordinates(File.E, 8)));
        setPieces(new Coordinates(File.D, 8), new Queen(Color.BLACK, new Coordinates(File.D, 8)));

    }

    //проверяем на четность клетку, чтобы раскрасить ее в нужный цвет
    public static boolean isSquareDark(Coordinates coordinates) {
        return (((coordinates.file.ordinal() + 1) + coordinates.rank) % 2) == 0;

    }

    public List<Piece> getPiecesByColor(Color color) {
        List<Piece> result = new ArrayList<>();
        for (Piece piece : pieces.values()) {
            if (piece.color == color) {
                result.add(piece);
            }
        }
        return result;
    }

    public boolean isSquareAttackedByColor(Coordinates coordinates, Color color) {
        List<Piece> pieces = getPiecesByColor(color);

        for (Piece piece : pieces) {
            Set <Coordinates> attackedSquares = piece.getAttackedSquares(this);

            if (attackedSquares.contains(coordinates)) {
                return true;
            }
        }
        return false;
    }


}