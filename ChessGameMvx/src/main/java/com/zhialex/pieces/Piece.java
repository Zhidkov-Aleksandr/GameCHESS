package com.zhialex.pieces;

import com.zhialex.board.Board;
import com.zhialex.Coordinates;
import com.zhialex.Color;

import java.util.HashSet;
import java.util.Set;

abstract public class Piece {
    public final Color color;
    public Coordinates coordinates;

    public Piece(Color color, Coordinates coordinates) {
        this.color = color;
        this.coordinates = coordinates;
    }

    //метод для описания возможных ходов
    public Set<Coordinates> getAvailableMoveSquares(Board board) {
        Set<Coordinates> result = new HashSet<>();
        for (CoordinatesShift shift : getPieceMoves()) {
            if (coordinates.canShift(shift)) {
                Coordinates newCoordinates = coordinates.shift(shift);

                if (isSquareAvailableForMove(newCoordinates, board)) {
                    result.add(newCoordinates);

                }
            }
        }
        return result;
    }

    // проверка доступности клетки для хода
    protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        return board.isSquareEmpty(coordinates) || board.getPiece(coordinates).color != color;
    }


    protected abstract Set<CoordinatesShift> getPieceMoves();

    protected Set<CoordinatesShift> getPieceAttacks() {
        return getPieceMoves();
    }

    public Set<Coordinates> getAttackedSquares(Board board) {
        Set<CoordinatesShift> pieceAttacks = getPieceAttacks();
        Set<Coordinates> result = new HashSet<>();

        for (CoordinatesShift pieceAttack : pieceAttacks) {
            if (coordinates.canShift(pieceAttack)) {
                Coordinates shiftedCoordinates = coordinates.shift(pieceAttack);

                if (isSquareAvailableForAttack(shiftedCoordinates, board)) {
                    result.add(shiftedCoordinates);
                }
            }

        }
        return result;
    }

    protected boolean isSquareAvailableForAttack(Coordinates coordinates, Board board) {
        return true;
    }
}
