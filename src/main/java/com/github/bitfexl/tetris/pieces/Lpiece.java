package com.github.bitfexl.tetris.pieces;

public class Lpiece extends Piece {
    /*
            # * #
            #
     */

    public Lpiece() {
        super(0, new Block("orange.png"));
        // cords relative to the Anchor (x, y):
        pieceOrientations = new byte[][][] {{
                {-1, 0}, {0, 0}, {1, 0}, {-1, 1}
        },{
                {0, -1}, {0, 0}, {0, 1}, {-1, -1}
        },{
                {1, -1}, {-1, 0}, {0, 0}, {1, 0}
        },{
                {0, -1}, {1, 1}, {0, 0}, {0, 1}
        }};
    }
}
