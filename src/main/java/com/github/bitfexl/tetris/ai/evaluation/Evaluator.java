package com.github.bitfexl.tetris.ai.evaluation;

import com.github.bitfexl.tetris.board.Board;

/**
 * Evaluate a board. The evaluator is not allowed to make any changes to the board.
 */
public interface Evaluator {
    /**
     * Evaluate a board after a certain criteria and return its score.
     * @param board The board to evaluate.
     * @return The score. Higher is better. Can be negative, should not be 0 because of multiplier.
     */
    double evaluate(Board board);
}
