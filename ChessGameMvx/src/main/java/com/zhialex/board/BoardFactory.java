package com.zhialex.board;

import com.zhialex.Coordinates;
import com.zhialex.File;
import com.zhialex.PieceFactory;

// передаем расстановку фигур по общепринятому коду FEN
public class BoardFactory {

    private PieceFactory pieceFactory = new PieceFactory();

    public Board fromFEN(String fen) {
        //  rnbq1bnr/pppkpppp/8/3p4/4P3/5N2/PPPP1PPP/RNBQKB1R w KQ - 2 3  -
        //  расположерние фигур полсе нескольких ходов

        Board board = new Board(fen);

        String[] parts = fen.split(" ");
        String piecePositions = parts[0];

        String[] fenRows = piecePositions.split("/");
        for (int i = 0; i < fenRows.length; i++) {
            String row = fenRows[i];
            int rank = 8 - i;

            int fileIndex = 0;
            for (int j = 0; j < row.length(); j++) {
                char fenChar = row.charAt(j);

                if (Character.isDigit(fenChar)) {
                    fileIndex += Character.getNumericValue(fenChar);
                } else {
                    File file = File.values()[fileIndex];
                    Coordinates coordinates = new Coordinates(file, rank);

                    board.setPieces(coordinates, pieceFactory.fromFenChar(fenChar, coordinates));
                    {
                        fileIndex++;
                    }
                }
            }
        }
        return board;
    }
    public Board copy(Board source) {
        Board clone = fromFEN(source.startingFen);

        for (Move move : source.moves) {
            clone.makeMove(move);
        }

        return clone;
    }

}