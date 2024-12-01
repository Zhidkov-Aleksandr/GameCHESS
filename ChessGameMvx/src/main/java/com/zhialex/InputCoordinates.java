package com.zhialex;

import com.zhialex.board.Board;
import com.zhialex.board.BoardFactory;
import com.zhialex.board.Move;
import com.zhialex.pieces.King;
import com.zhialex.pieces.Piece;

import java.util.Scanner;
import java.util.Set;

// читаем ввод координат хода из консоли
public class InputCoordinates {
    private static final Scanner scanner = new Scanner(System.in);

    public static Coordinates input() {
        while (true) {
            System.out.println("Vvedite koordinati hoda (primer a1)");

            String line = scanner.nextLine();
            if (line.length() != 2) {
                System.out.println("Nepravilniy vvod");
                continue;
            }
            char fileChar = line.charAt(0);
            char rankChar = line.charAt(1);

            if (!Character.isLetter(fileChar)) {
                System.out.println("Nepravilniy vvod");
                continue;
            }
            if (!Character.isDigit(rankChar)) {
                System.out.println("Nepravilniy vvod");
                continue;
            }
            int rank = Character.getNumericValue(rankChar);
            if (rank < 1 || rank > 8) {
                System.out.println("Nepravilniy vvod");
                continue;
            }
            File file = File.fromChar(fileChar);
            if (file == null) {
                System.out.println("Nepravilniy vvod");
                continue;
            }
            return new Coordinates(file, rank);
        }

    }

    public static Coordinates inputPieceCoordinatesForColor(Color color, Board board) {
        while (true) {
            System.out.println("Vvedite coordinaty figuri dlya peremesheniya");
            Coordinates coordinates = input();
            if (board.isSquareEmpty(coordinates)) {
                System.out.println("Pustaja kletka");
                continue;
            }
            Piece piece = board.getPiece(coordinates);
            if (piece.color != color) {
                System.out.println("Nelzya dvigat chuzhyu figuru");
                continue;
            }
            Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquares(board);
            if (availableMoveSquares.size() == 0) {
                System.out.println("Figura zablokirovana");
                continue;
            }
            return coordinates;


        }
    }

    public static Coordinates inputAvailableSquare(Set<Coordinates> coordinates) {
        while (true) {
            System.out.println("Vvedite koordinaty hoda");
            Coordinates input = input();

            if (!coordinates.contains(input)) {
                System.out.println("Nedostypnaya dlya hoda kletka");
                continue;
            }
            return input;
        }
    }

    public static Move inputMove(Board board, Color color, BoardConsoleRenderer renderer) {
        // читаем ввод с консоли
        Move move = null;
        while (true) {
            Coordinates sourceCoordinates = InputCoordinates.inputPieceCoordinatesForColor(
                    color, board);
            Piece piece = board.getPiece(sourceCoordinates);
            Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquares(board);

            renderer.render(board, piece);
            Coordinates targetCoordinates = InputCoordinates.inputAvailableSquare(availableMoveSquares);
            move = new Move(sourceCoordinates, targetCoordinates);
            // проверка короля на шах
            if (validateIfKingInCheckAfterMove(board, color, move)) {
                System.out.println("Korol` pod shahom!!");
                continue;
            }
            return move;
        }
    }

    private static boolean validateIfKingInCheckAfterMove(Board board, Color color, Move move) {
        Board copy = (new BoardFactory()).copy(board); //копируем доску
        copy.makeMove(move); // делаем ход на скопированной доске
        Piece king = copy.getPiecesByColor(color).stream().filter(
                piece -> piece instanceof King).findFirst().get(); // ищем Короля на доске, делаем допущение что он есть на доске
        return copy.isSquareAttackedByColor(king.coordinates, color.opposite());  // провеяем Короля
    }
}
