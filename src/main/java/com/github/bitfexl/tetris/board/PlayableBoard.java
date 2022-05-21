package com.github.bitfexl.tetris.board;

import com.github.bitfexl.tetris.pieces.Piece;

public class PlayableBoard {
    /**
     * Blocks that are fixed.
     */
    private Board staticBoard;

    /**
     * The board with currently dropping piece.
     */
    private Board currentBoard;

    /**
     * The currently moving piece.
     */
    private Piece currentPiece;

    /**
     * Coordinates of the current piece.
     */
    private int xCord, yCord;

    public PlayableBoard(int width, int height) {
        this.staticBoard = new Board(width, height);
        this.currentBoard = new Board(width, height);
    }

    /**
     * Move current piece left.
     * @return true: move successful, false: unsuccessful;
     */
    public boolean moveLeft() {
        if(currentPiece == null) {
            return false;
        }

        if(staticBoard.canPlacePiece(xCord-1, yCord, currentPiece)) {
            xCord -= 1;
            copyBoard();
            return true;
        }
        return false;
    }

    /**
     * Move current piece right.
     * @return true: move successful, false: unsuccessful;
     */
    public boolean moveRight() {
        if(currentPiece == null) {
            return false;
        }

        if(staticBoard.canPlacePiece(xCord+1, yCord, currentPiece)) {
            xCord += 1;
            copyBoard();
            return true;
        }
        return false;
    }

    /**
     * Move current piece down one line.
     * @return true: move successful, false: move unsuccessful -> piece has reached the bottom -> line clear, next piece;
     */
    public boolean moveDown() {
        if(currentPiece == null) {
            return false;
        }

        if(staticBoard.canPlacePiece(xCord, yCord+1, currentPiece)) {
            yCord += 1;
        } else {
            staticBoard.placePiece(xCord, yCord, currentPiece);
            currentPiece = null;
        }

        copyBoard();
        return hasDroppingPiece();
    }

    /**
     * Rotate the piece.
     * @return true: move successful, false: unsuccessful;
     */
    public boolean rotate() {
        if(currentPiece == null) {
            return false;
        }

        int rotation = currentPiece.getOrientation();
        currentPiece.rotate();

        if(staticBoard.canPlacePiece(xCord, yCord, currentPiece)) {
            copyBoard();
            return true;
        }
        currentPiece.setOrientation(rotation);
        return false;

    }

    /**
     * Drop the piece to the bottom.
     */
    public void dropPiece() {
        while(moveDown());
    }

    /**
     * Drop a new piece.
     * @param piece The new piece to drop.
     * @return true: successful, false: could not drop piece -> game over;
     */
    public boolean newPiece(Piece piece) {
        piece.setOrientation(0);
        xCord = (staticBoard.getWidth() / 2) - 1;
        yCord = 0;

        if(staticBoard.canPlacePiece(xCord, yCord, piece)) {
            currentPiece = piece;
            copyBoard();
            return true;
        } else {
            currentPiece = null;
            return false;
        }
    }

    /**
     * If a piece is currently dropping.
     * @return true: piece is currently dropping, false: no piece currently dropping;
     */
    public boolean hasDroppingPiece() {
        return currentPiece != null;
    }

    public Board getStaticBoard() {
        return staticBoard;
    }

    public Board getCurrentBoard() {
        return currentBoard;
    }

    /**
     * Clear the line
     * @return The amount of cleared lines.
     */
    public int clearLines() {
        return staticBoard.clearLines();
    }

    /**
     * Copies the static board to current board and places the current piece.
     */
    private void copyBoard() {
        staticBoard.copyTo(currentBoard);
        if(currentPiece != null) {
            currentBoard.placePiece(xCord, yCord, currentPiece);
        }
    }
}
