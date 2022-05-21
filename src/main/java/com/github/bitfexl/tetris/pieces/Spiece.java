package com.github.bitfexl.tetris.pieces;

public class Spiece  extends Piece {
    /*
              * #
            # #
     */

    public Spiece() {
        super(0, new Block("red.png"));
        // cords relative to the Anchor (x, y):
        pieceOrientations = new byte[][][] {{
                {-1, 1}, {0, 1}, {0, 0}, {1, 0}
        },{
                {-1, 0}, {0, 0}, {-1, -1}, {0, 1}
        },{
                {-1, 0}, {0, 0}, {0, -1}, {1, -1}
        },{
                {-1, 0}, {-1, -1}, {0, 0}, {0, 1}
        }};
    }
}
