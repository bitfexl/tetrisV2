package com.github.bitfexl.tetris.pieces;

public class Jpiece extends Piece {
    /*
            # * #
                #
     */

    public Jpiece() {
        super(0, new Block("blue.png"));
        // cords relative to the Anchor (x, y):
        pieceOrientations = new byte[][][] {{
                {-1, 0}, {0, 0}, {1, 0}, {1, 1}
        },{
                {0, -1}, {0, 0}, {0, 1}, {-1, 1}
        },{
                {-1, -1}, {-1, 0}, {0, 0}, {1, 0}
        },{
                {0, -1}, {1, -1}, {0, 0}, {0, 1}
        }};
    }
}
