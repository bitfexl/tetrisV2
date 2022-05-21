package com.github.bitfexl.tetris.ai.evaluation;

import com.github.bitfexl.tetris.board.Board;

import java.util.Arrays;

/**
 * Negative points for empty columns.
 */
public class ColumnEvaluator implements Evaluator {

    @Override
    public double evaluate(Board board) {
        // The amount of empty squares for each column, only incremented if left and right is a block.
        int[] emptySquares = new int[board.getWidth()];
        Arrays.fill(emptySquares, -1);

        for(int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                boolean leftSquare = x <= 0 || !board.isEmpty(x - 1, y);
                boolean rightSquare = x >= board.getWidth()-1 || !board.isEmpty(x + 1, y);
                if(leftSquare && rightSquare) {
                    emptySquares[x]++;
                }
            }
        }

        double score = 0;
        for (int count : emptySquares) {
            score -= count * count;
        }
        return score;
    }
}
