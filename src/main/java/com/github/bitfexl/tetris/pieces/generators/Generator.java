package com.github.bitfexl.tetris.pieces.generators;

import com.github.bitfexl.tetris.pieces.*;

public interface Generator {
    /**
     * All the possible pieces to select from.
     */
    Piece[] PIECES = {new Ipiece(), new Jpiece(), new Lpiece(), new Opiece(), new Spiece(), new Tpiece(), new Zpiece()};

    /**
     * Generate the next piece.
     * @return The next piece.
     */
    Piece nextPiece();
}
