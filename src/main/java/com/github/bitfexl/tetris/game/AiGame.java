package com.github.bitfexl.tetris.game;

import com.github.bitfexl.tetris.ai.Ai;
import com.github.bitfexl.tetris.board.Board;
import com.github.bitfexl.tetris.javagame.util.ClassPathImageLoader;
import com.github.bitfexl.tetris.pieces.generators.BagGenerator;
import com.github.bitfexl.tetris.pieces.management.PieceManager;
import com.github.bitfexl.tetris.window.TetrisGameWindow;

public class AiGame {
    public static final int MAX_PEEKS = 1;

    public static void main(String[] args) throws InterruptedException {
        // todo: empty columns evaluator
        if(args.length > 0 && args[0].equals("simulate")) {
            simulateGames(10);
        } else {
            showGame();
        }
    }

    public static double simulateGames(int count) {
        int[] scores = new int[count];

        Board board = new Board(10, 20);
        Ai ai = new Ai(board, new PieceManager(new BagGenerator()), MAX_PEEKS);

        for(int i=0; i<count; i++) {
            while (ai.playNextPiece()) {
                if(ai.getClearedLines() % 1000 == 0) {
                    System.out.print(".");
                }
            }
            System.out.println();

            scores[i] = ai.getClearedLines();
            board.clear();
            ai.resetClearedLines();

            int minScore = Integer.MAX_VALUE;
            int maxScore = Integer.MIN_VALUE;
            double avg = 0;
            for (int j=0; j<=i; j++) {
                minScore = Math.min(minScore, scores[j]);
                maxScore = Math.max(maxScore, scores[j]);
                avg += scores[j];
            }
            avg /= i+1;

            System.out.println("Simulated game " + (i+1) + "/" + count + ":");
            System.out.println("Min: " + minScore);
            System.out.println("Max: " + maxScore);
            System.out.println("Avg: " + avg);
            System.out.println();

        }

        return 0;
    }

    public static void showGame() throws InterruptedException {
        Board board = new Board(10, 20);

        TetrisGameWindow window = new TetrisGameWindow(board, new ClassPathImageLoader(), "/com.github.bitfexl.tetris.assets/blocks/");
        window.setTitle("Tetris Game");
//        window.enableDebugging();
        TetrisGameWindow.WindowStop stopWindow = window.openGameWindow();

        Ai ai = new Ai(board, new PieceManager(new BagGenerator()), MAX_PEEKS);

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
