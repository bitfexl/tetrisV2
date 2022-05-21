package com.github.bitfexl.tetris.pieces;

public class Tpiece extends Piece {
    /*
            Anchor is top middle:
            # * #
              #
     */

    public Tpiece() {
        super(0, new Block("pink.png"));
        // cords relative to the Anchor (x, y):
        pieceOrientations = new byte[][][] {{
                {-1, 0}, {0, 0}, {1, 0}, {0, 1}
        }, {
                {0, -1}, {-1, 0}, {0, 0}, {0, 1}
        }, {
                {0, -1}, {-1, 0}, {0, 0}, {1, 0}
        }, {
                {0, -1}, {0, 0}, {1, 0}, {0, 1}
        }};
    }
}
