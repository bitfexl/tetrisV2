package com.github.bitfexl.tetris.pieces;

public abstract class Piece {
    /**
     * The 4 piece orientations and their coordinates.
     */
    protected byte[][][] pieceOrientations;

    /**
     * Current piece orientation (0-3).
     */
    private int orientation;

    /**
     * The block to display.
     */
    private Block block;

    public Piece(int orientation, Block block) {
        this.orientation = orientation;
        this.block = block;
    }

    /**
     * The relative coordinates from the center block where blocks are placed.
     * @return An array of block coordinate offsets: [x,y].
     */
    public byte[][] getCoordinates() {
        return pieceOrientations[orientation];
    }

    /**
     * The relative coordinates from the center block where blocks are placed.
     * @param orientation The orientation to get (0-3).
     * @return An array of block coordinate offsets: [x,y].
     */
    public byte[][] getCoordinates(int orientation) {
        if(orientation < 0 || orientation > 3) {
            orientation = 0;
        }
        return pieceOrientations[orientation];
    }

    /**
     * Rotate the piece.
     */
    public void rotate() {
        if(orientation == 3) {
            orientation = 0;
        } else {
            orientation++;
        }
    }

    public void setOrientation(int orientation) {
        if(orientation >= 0 && orientation <= 3) {
            this.orientation = orientation;
        }
    }

    public int getOrientation() {
        return orientation;
    }

    public Block getBlock() {
        return block;
    }
}
