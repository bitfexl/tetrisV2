package com.github.bitfexl.tetris.pieces.generators;

import com.github.bitfexl.tetris.pieces.Piece;

import java.util.Random;

public class RandomGenerator implements Generator {
    private Random random;

    public RandomGenerator() {
        random = new Random();
    }

    @Override
    public Piece nextPiece() {
        return PIECES[random.nextInt(0, PIECES.length)];
    }
}
