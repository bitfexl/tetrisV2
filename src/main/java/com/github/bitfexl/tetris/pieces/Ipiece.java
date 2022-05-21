package com.github.bitfexl.tetris.pieces;

public class Ipiece extends Piece {
    /*
            # * # #
     */

    public Ipiece() {
        super(0, new Block("light_blue.png"));
        // cords relative to the Anchor (x, y):
        pieceOrientations = new byte[][][] {{
                {-1, 0}, {0, 0}, {1, 0}, {2, 0}
        },{
                {0, -1}, {0, 0}, {0, 1}, {0, 2}
        },{
                {-2, 0}, {-1, 0}, {0, 0}, {1, 0}
        },{
                {0, 2}, {0, -1}, {0, 0}, {0, 1}
        }};
    }
}
