package com.github.bitfexl.tetris.game;

import com.github.bitfexl.tetris.ai.Ai;
import com.github.bitfexl.tetris.board.Board;
import com.github.bitfexl.tetris.javagame.util.ClassPathImageLoader;
import com.github.bitfexl.tetris.pieces.generators.BagGenerator;
import com.github.bitfexl.tetris.pieces.management.PieceManager;
import com.github.bitfexl.tetris.window.TetrisGameWindow;

public class AiGame {
    public static void main(String[] args) throws InterruptedException {
        // todo: empty columns evaluator, use peek
        // simulateGames(100);
        showGame();
    }

    public static double simulateGames(int count) {
        int[] scores = new int[count];

        Board board = new Board(10, 20);
        Ai ai = new Ai(board, new PieceManager(new BagGenerator()), 1);

        for(int i=0; i<count; i++) {
            while (ai.playNextPiece());

            scores[i] = ai.getClearedLines();
            board.clear();
            ai.resetClearedLines();
        }

        int minScore = Integer.MAX_VALUE;
        int maxScore = Integer.MIN_VALUE;
        double avg = 0;
        for(int score : scores) {
            minScore = Math.min(minScore, score);
            maxScore = Math.max(maxScore, score);
            avg += score;
        }
        avg /= count;

        System.out.println("Simulated " + count + " games!");
        System.out.println("Min: " + minScore);
        System.out.println("Max: " + maxScore);
        System.out.println("Avg: " + avg);

        return avg;
    }

    public static void showGame() throws InterruptedException {
        Board board = new Board(10, 20);

        TetrisGameWindow window = new TetrisGameWindow(board, new ClassPathImageLoader(), "/com.github.bitfexl.tetris.assets/blocks/");
        window.setTitle("Tetris Game");
//        window.enableDebugging();
        TetrisGameWindow.WindowStop stopWindow = window.openGameWindow();

        Ai ai = new Ai(board, new PieceManager(new BagGenerator()), 1);

        while (ai.playNextPiece()) {
            window.setHoldPiece(ai.getPieceManager().getHold());
            window.setPreviewPiece(ai.getPieceManager().peekNext());
            window.setTitle("Score: " + ai.getClearedLines());
            Thread.sleep(10);
        }

        System.out.println("Lines cleared: " + ai.getClearedLines());
        System.out.println("GAME OVER!");
    }
}
