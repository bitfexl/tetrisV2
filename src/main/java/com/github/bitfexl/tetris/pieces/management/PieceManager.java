package com.github.bitfexl.tetris.pieces.management;

import com.github.bitfexl.tetris.pieces.Piece;
import com.github.bitfexl.tetris.pieces.generators.Generator;

import java.util.ArrayDeque;

/**
 * Highly unoptimised piece manager.
 */
public class PieceManager {
    /**
     * The piece generator to use.
     */
    private Generator pieceGenerator;

    /**
     * The already generated pieces.
     */
    private ArrayDeque<Piece> nextPieces;

    /**
     * The hold piece.
     */
    private Piece hold;

    public PieceManager(Generator pieceGenerator) {
        this.pieceGenerator = pieceGenerator;
        this.nextPieces = new ArrayDeque<>();
    }

    /**
     * Get the next piece and remove it.
     * @return The next piece to drop.
     */
    public Piece consumeNext() {
        if(nextPieces.size() == 0) {
            return pieceGenerator.nextPiece();
        }
        return nextPieces.poll();
    }

    /**
     * Peak the next piece.
     * @return The next piece for consumeNext.
     */
    public Piece peekNext() {
        return peekNext(0);
    }

    /**
     * Peak the nth piece.
     * @param n 0 (next) to n
     * @return The piece that will be returned by consumeNext in n+1 calls.
     */
    public Piece peekNext(int n) {
        while (nextPieces.size() <= n) {
            nextPieces.add(pieceGenerator.nextPiece());
        }
        return (Piece)nextPieces.toArray()[n];
    }

    /**
     * Hold a new piece.
     * @param piece The new piece to hold.
     * @return The previously held piece.
     * @see PieceManager#getHold()
     * @see PieceManager#setHold(Piece)
     */
    public Piece hold(Piece piece) {
        try {
            return hold;
        } finally {
            hold = piece;
        }
    }

    public Piece getHold() {
        return hold;
    }

    public void setHold(Piece hold) {
        this.hold = hold;
    }

    public void setPieceGenerator(Generator pieceGenerator) {
        this.pieceGenerator = pieceGenerator;
    }
}
