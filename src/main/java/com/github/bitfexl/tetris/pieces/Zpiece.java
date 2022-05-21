package com.github.bitfexl.tetris.pieces;

public class Zpiece  extends Piece {
    /*
            # *
              # #
     */

    public Zpiece() {
        super(0, new Block("light_green.png"));
        // cords relative to the Anchor (x, y):
        pieceOrientations = new byte[][][] {{
                {-1, 0}, {0, 0}, {0, 1}, {1, 1}
        },{
                {0, -1}, {0, 0}, {-1, 0}, {-1, 1}
        },{
                {-1, 0}, {0, 0}, {0, 1}, {1, 1}
        },{
                {0, -1}, {0, 0}, {-1, 0}, {-1, 1}
        }};
    }
}