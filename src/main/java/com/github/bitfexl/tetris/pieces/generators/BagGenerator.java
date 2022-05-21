package com.github.bitfexl.tetris.pieces.generators;

import com.github.bitfexl.tetris.pieces.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Generate all pieces once before generating a piece twice.
 */
public class BagGenerator implements Generator {
    private Random random;

    private ArrayList<Piece> currentBag;

    public BagGenerator() {
        random = new Random();
        currentBag = new ArrayList<>();
    }

    @Override
    public Piece nextPiece() {
        if(currentBag.isEmpty()) {
            currentBag.addAll(Arrays.asList(PIECES));
        }
        return currentBag.remove(random.nextInt(0, currentBag.size()));
    }
}
