package com.github.bitfexl.tetris.pieces;

public class Opiece extends Piece {
    /*
            Anchor is the top left:
            * #
            # #
     */

    public Opiece() {
        super(0, new Block("yellow.png"));
        // cords relative to the Anchor (x, y):
        pieceOrientations = new byte[][][] {{
                {0, 0}, {1, 0},
                {0, 1}, {1, 1}
        },{
                {0, 0}, {1, 0},
                {0, 1}, {1, 1}
        },{
                {0, 0}, {1, 0},
                {0, 1}, {1, 1}
        },{
                {0, 0}, {1, 0},
                {0, 1}, {1, 1}
        }};
    }
}
