package com.github.bitfexl.tetris.ai.evaluation;

import com.github.bitfexl.tetris.board.Board;

public class HeightEvaluator implements Evaluator{
    @Override
    public double evaluate(Board board) {
        double score = 0;
        for(int y=0; y<board.getHeight(); y++) {
            for(int x=0; x<board.getWidth(); x++) {
                if(!board.isEmpty(x, y)) {
                    // - linenumber form bottom
                    score -= board.getHeight() - y;
                }
            }
        }
        return score;
    }
}
