package com.github.bitfexl.tetris.ai.evaluation;

import com.github.bitfexl.tetris.board.Board;

/**
 * -1 point for each hole.
 */
public class HolesEvaluator implements Evaluator {
    @Override
    public double evaluate(Board board) {
        double score = 0;

        for(int x=0; x<board.getWidth(); x++) {
            int blocksAbove = 0;
            for (int y = 0; y < board.getHeight(); y++) {
                if(board.isEmpty(x, y) && blocksAbove > 0) {
                    score -= 1;
                } else if(!board.isEmpty(x, y)) {
                    blocksAbove++;
                }
            }
        }

        return score;
    }
}
