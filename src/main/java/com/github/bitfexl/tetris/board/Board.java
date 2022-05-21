package com.github.bitfexl.tetris.board;

import com.github.bitfexl.tetris.pieces.Block;
import com.github.bitfexl.tetris.pieces.Piece;

import java.util.Arrays;

/**
 * Represents a tetris board.
 */
public class Board {
    /**
     * The width in blocks
     */
    private int width;

    /**
     * The height in blocks.
     */
    private int height;

    /**
     * The placed blocks.
     * 0,0 is top left; height,width is bottom right;
     */
    private Block[][] board;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new Block[height][width];
    }

    /**
     * Set a block. 0,0 is top left.
     * @param x column, 0 to width-1;
     * @param y line, 0 to height-1;
     */
    public void setBlock(int x, int y, Block block) {
        board[y][x] = block;
    }

    /**
     * Set a block. 0,0 is top left.
     * @param x column, 0 to width-1;
     * @param y line, 0 to height-1;
     * @return The block, can be null if no block.
     */
    public Block getBlock(int x, int y) {
        return board[y][x];
    }

    /**
     * Places a piece on the board.
     * @param x column, 0 to width-1;
     * @param y line, 0 to height-1;
     * @param piece The piece to place.
     * @return true: placed successfully,
     * false: One or more blocks could not be placed either because they are out of bounds or because there is already a block.
     * If false no blocks will be placed.
     */
    public boolean placePiece(int x, int y, Piece piece) {
        byte[][] cordOffsets = piece.getCoordinates();

        if(!canPlacePiece(x, y, piece)) {
            return false;
        }

        for(int i=0; i<cordOffsets.length; i++) {
            int xCord = x + cordOffsets[i][0], yCord = y + cordOffsets[i][1];
            board[yCord][xCord] = piece.getBlock();
        }
        return true;
    }

    /**
     * Tests if the piece would fit.
     * @param x column, 0 to width-1;
     * @param y line, 0 to height-1;
     * @param piece The piece to test.
     * @return true: would fit, false: does not fit;
     */
    public boolean canPlacePiece(int x, int y, Piece piece) {
        byte[][] cordOffsets = piece.getCoordinates();

        for(int i=0; i<cordOffsets.length; i++) {
            int xCord = x + cordOffsets[i][0], yCord = y + cordOffsets[i][1];
            if(!isInBounds(xCord, yCord) || !isEmpty(xCord, yCord)) {
                return false;
            }
        }

        return true;
    }


    /**
     * See if a block is set. 0,0 is top left.
     * @param x column, 0 to width-1;
     * @param y line, 0 to height-1;
     * @return true: empty, false: block set;
     */
    public boolean isEmpty(int x, int y) {
        return board[y][x] == null;
    }

    /**
     * See if line has at least one empty square.
     * @param y the line, 0 to height-1;
     * @return true: line has one or more empty squares, false: filled;
     */
    public boolean lineHasEmpty(int y) {
        for(int i=0; i<width; i++) {
            if(isEmpty(i, y)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Clear whole lines if possible.
     * @return The amout of cleared lines.
     */
    public int clearLines() {
        int clearedLines = 0;
        for(int i=0; i<height; i++) {
            if(!lineHasEmpty(i)) {
                // shift lines down
                for(int j=i; j>=0; j--) {
                    if(j == 0) {
                        board[j] = new Block[width];
                    } else {
                        board[j] = board[j - 1];
                    }
                }
                clearedLines++;
            }
        }
        return clearedLines;
    }

    /**
     * Copy this board to another board.
     * @param board2 The board to copy to.
     * @return board2
     */
    public Board copyTo(Board board2) {
        for(int y=0; y<height; y++) {
            for(int x=0; x<width; x++) {
                board2.board[y][x] = board[y][x];
            }
        }
        return board2;
    }

    /**
     * Get a copy of the current board.
     * @return The new copy.
     */
    public Board getCopy() {
        return copyTo(new Board(width, height));
    }

    /**
     * Checks if the two boards are the same (same blocks filled with any block).
     * @param board2 The board to compare to.
     * @return true: boards are equal, false: boards differ;
     */
    public boolean equals(Board board2) {
        for(int y=0; y<height; y++) {
            for(int x=0; x<width; x++) {
                if(isEmpty(x, y) != board2.isEmpty(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Clears the board.
     */
    public void clear() {
        for(int y=0; y<height; y++) {
            for(int x=0; x<width; x++) {
                board[y][x] = null;
            }
        }
    }

    /**
     * Checks if the coordinates are inbounds of the board.
     * @param x The x coordinate to check.
     * @param y The y coordinate to check.
     * @return true: valid coordinates, false: coordinates are out of bounds -> can throw an exception if passed to certain methods;
     */
    public boolean isInBounds(int x, int y) {
        return x >= 0 &&
                x < width &&
                y >= 0 &&
                y < height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
