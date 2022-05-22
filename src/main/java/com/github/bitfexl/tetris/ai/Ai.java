package com.github.bitfexl.tetris.ai;

import com.github.bitfexl.tetris.ai.evaluation.HeightEvaluator;
import com.github.bitfexl.tetris.ai.evaluation.HolesEvaluator;
import com.github.bitfexl.tetris.ai.evaluation.PositionEvaluator;
import com.github.bitfexl.tetris.board.Board;
import com.github.bitfexl.tetris.pieces.Piece;
import com.github.bitfexl.tetris.pieces.management.PieceManager;

import java.util.LinkedList;

public class Ai {
    private static class Position {
        Board board;
        double score;
        int clearedLines;
    }

    /**
     * The tetris board.
     */
    private Board board;

    private PieceManager pieceManager;

    /**
     * The maximum peeks the Ai is allowed.
     */
    private int maxPeek;

    private PositionSimulator positionSimulator;

    private PositionEvaluator positionEvaluator;

    /**
     * Number of lines cleared in last or current game.
     */
    private int clearedLines;

    public Ai(Board board, PieceManager pieceManager, int maxPeek) {
        this.board = board;
        this.pieceManager = pieceManager;
        this.maxPeek = maxPeek;
        this.positionSimulator = new PositionSimulator();
        this.positionEvaluator = new PositionEvaluator(0);
        positionEvaluator.addEvaluator(new HeightEvaluator());
        positionEvaluator.addEvaluator(new HolesEvaluator(), 20);
    }

    /**
     * Play the next move.
     * @return true: success, false: game over;
     */
    public boolean playNextPiece() {
        if(pieceManager.getHold() == null) {
            pieceManager.setHold(pieceManager.consumeNext());
        }
        Piece currentPiece = pieceManager.consumeNext();

        Position bestPosition = getBestPosition(board, getPiecesPreview(currentPiece), 0);

        // game over check
        if(bestPosition.board == null) {
            return false;
        }

        Position bestPositionUseHold = getBestPosition(board, getPiecesPreview(pieceManager.getHold()), 0);

        if(bestPositionUseHold.board != null && bestPositionUseHold.score > bestPosition.score) {
            bestPosition = bestPositionUseHold;
            currentPiece = pieceManager.hold(currentPiece);
        }

        bestPosition.board.copyTo(board);
        clearedLines += bestPosition.clearedLines;
        return true;
    }

    public int getClearedLines() {
        return clearedLines;
    }

    public void resetClearedLines() {
        clearedLines = 0;
    }

    private Position getBestPosition(Board board, Piece[] pieces, int start) {
        if(start+1 == pieces.length) {
            return getBestPosition(positionSimulator.getPositions(board, pieces[start]));
        }

        Position bestPosition = new Position();
        bestPosition.score = -Double.MAX_VALUE;

        for(Board boardPosition : positionSimulator.getPositions(board, pieces[start])) {
            int clearedLines = boardPosition.clearLines();
            double endScore = getBestPosition(boardPosition, pieces, start+1).score;
            if(endScore > bestPosition.score) {
                bestPosition.board = boardPosition;
                bestPosition.score = endScore;
                bestPosition.clearedLines = clearedLines;
            }
        }

        return bestPosition;
    }

    private Position getBestPosition(LinkedList<Board> positions) {
        Position bestPosition = new Position();
        bestPosition.score = -Double.MAX_VALUE;

        for(Board boardPosition : positions) {
            int clearedLines = boardPosition.clearLines();
            double score = positionEvaluator.evaluate(boardPosition);
            if(score > bestPosition.score) {
                bestPosition.board = boardPosition;
                bestPosition.score = score;
                bestPosition.clearedLines = clearedLines;
            }
        }

        return bestPosition;
    }

    private Piece[] getPiecesPreview(Piece current) {
        Piece[] pieces = new Piece[maxPeek + 1];
        pieces[0] = current;
        for(int i=1; i<pieces.length; i++) {
            pieces[i] = pieceManager.peekNext(i-1);
        }
        return pieces;
    }

    public Board getBoard() {
        return board;
    }

    public PieceManager getPieceManager() {
        return pieceManager;
    }

    public int getMaxPeek() {
        return maxPeek;
    }
}
