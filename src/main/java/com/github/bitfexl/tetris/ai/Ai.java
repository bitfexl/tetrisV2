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

        Position bestPosition = getBestPosition(positionSimulator.getPositions(board, currentPiece));

        // game over check
        if(bestPosition.board == null) {
            return false;
        }

        Position holdPosition = getBestPosition(positionSimulator.getPositions(board, pieceManager.getHold()));

        if(holdPosition.board != null && holdPosition.score > bestPosition.score) {
            bestPosition = holdPosition;
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
