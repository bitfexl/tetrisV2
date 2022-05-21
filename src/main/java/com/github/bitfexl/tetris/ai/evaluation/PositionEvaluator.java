package com.github.bitfexl.tetris.ai.evaluation;

import com.github.bitfexl.tetris.board.Board;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Evaluate a board using multiple evaluators.
 */
public class PositionEvaluator implements Evaluator {
    /**
     * The base score. Value does not matter.
     */
    private double baseScore;

    /**
     * The evaluators to use for evaluation.
     */
    private HashSet<Evaluator> evaluators;

    /**
     * The multipliers for each evaluator.
     */
    private HashMap<Evaluator, Double> multipliers;

    public PositionEvaluator(double baseScore) {
        this.baseScore = baseScore;
        this.evaluators = new HashSet<>();
        this.multipliers = new HashMap<>();
    }

    /**
     * Add an evaluator to evaluate boards.
     * @param evaluator The evaluator to add.
     */
    public void addEvaluator(Evaluator evaluator) {
        addEvaluator(evaluator, 1.0);
    }

    /**
     * Add an evaluator to evaluate boards.
     * @param evaluator The evaluator to add.
     * @param multiplier The multiplier of the returned score by this evaluator.
     */
    public void addEvaluator(Evaluator evaluator, double multiplier) {
        evaluators.add(evaluator);
        multipliers.put(evaluator, multiplier);
    }

    /**
     * Sees how good a board is.
     * @param board The board to evaluate.
     * @return The board score (can be negative), higher is better.
     */
    @Override
    public double evaluate(Board board) {
        double score = baseScore;
        for(Evaluator evaluator : evaluators) {
            score += evaluator.evaluate(board) * multipliers.get(evaluator);
        }
        return score;
    }

    public double getBaseScore() {
        return baseScore;
    }

    public void setBaseScore(double baseScore) {
        this.baseScore = baseScore;
    }
}
