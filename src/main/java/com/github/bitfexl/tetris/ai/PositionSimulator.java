package com.github.bitfexl.tetris.ai;

import com.github.bitfexl.tetris.board.Board;
import com.github.bitfexl.tetris.pieces.Piece;

import java.util.LinkedList;
import java.util.List;

/**
 * Simulate every end position of a given board and piece.
 */
public class PositionSimulator {
    /**
     * Get every position for a given piece.
     * @param originalBoard The board to drop the piece from, must be at least 3 lines high. Not modified.
     * @param piece The piece to drop.
     * @return An array of all the possible end positions.
     */
    public LinkedList<Board> getPositions(Board originalBoard, Piece piece) {
        LinkedList<Board> positions = new LinkedList<>();

        // test every column
        for(int x=0; x<originalBoard.getWidth(); x++) {
            // test every rotation
            for(int r=0; r<4; r++) {
                // test the first 3 lines in case it would overflow the top otherwise
                for(int y=0; y<3; y++) {
                    piece.setOrientation(r);
                    if(originalBoard.canPlacePiece(x, y, piece)) {
                        Board endPosition = dropPiece(originalBoard.getCopy(), piece, x, y);
                        addNewPosition(positions, endPosition);
                        break; // do not simulate any other lines
                    }
                }
            }
        }

        return positions;
    }

    /**
     * Drop a piece.
     * @param board The board to drop on.
     * @param piece The piece to drop.
     * @param x The x start cord (piece must fit).
     * @param y The y start cord (piece must fit).
     * @return board
     */
    private Board dropPiece(Board board, Piece piece, int x, int y) {
        while(board.canPlacePiece(x, y+1, piece)) {
            y++;
        }
        board.placePiece(x, y, piece);
        return board;
    }

    /**
     * Add new position only if not already added.
     * @param list The list to add to.
     * @param board The position to add.
     */
    private void addNewPosition(List<Board> list, Board board) {
        for(Board contained : list) {
            if(contained.equals(board)) {
                return;
            }
        }
        list.add(board);
    }
}
