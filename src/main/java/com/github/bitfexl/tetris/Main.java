package com.github.bitfexl.tetris;

import com.github.bitfexl.tetris.ai.PositionSimulator;
import com.github.bitfexl.tetris.ai.evaluation.ColumnEvaluator;
import com.github.bitfexl.tetris.ai.evaluation.HeightEvaluator;
import com.github.bitfexl.tetris.ai.evaluation.HolesEvaluator;
import com.github.bitfexl.tetris.ai.evaluation.PositionEvaluator;
import com.github.bitfexl.tetris.board.Board;
import com.github.bitfexl.tetris.board.PlayableBoard;
import com.github.bitfexl.tetris.javagame.window.GameWindow;
import com.github.bitfexl.tetris.javagame.window.input.controllers.KeySubscription;
import com.github.bitfexl.tetris.pieces.*;
import com.github.bitfexl.tetris.pieces.generators.BagGenerator;
import com.github.bitfexl.tetris.pieces.generators.Generator;
import com.github.bitfexl.tetris.window.*;
import com.github.bitfexl.tetris.javagame.util.ClassPathImageLoader;

import java.awt.event.KeyEvent;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        aiGame(true);
//        for(int i=0; i<10; i++) {
//            aiGame(false);
//        }
////        playerGame();
    }

    private static void aiGame(boolean extendedInfo) throws Exception {
        Piece[] pieces = {new Ipiece(), new Jpiece(), new Lpiece(), new Opiece(), new Spiece(), new Tpiece(), new Zpiece()};
        Random random = new Random();

        Board board = new Board(10, 20);

        GameWindow window = initGameWindow(board);

        PositionEvaluator evaluator = new PositionEvaluator(100);
        evaluator.addEvaluator(new HeightEvaluator());
        evaluator.addEvaluator(new HolesEvaluator(), 20);
        PositionSimulator positionSimulator = new PositionSimulator();

        Generator pieceGenerator = new BagGenerator();

        long lastDownMove = System.currentTimeMillis();

        int linesCleared = 0;

        while(true) {
            Piece piece = pieceGenerator.nextPiece();

            Board bestBoard = null;
            double bestScore = -99999;
            int bestBoardLinesCleared = 0;
            for(Board position : positionSimulator.getPositions(board, piece)) {
                int currentLinesCleared = position.clearLines();
                double score = evaluator.evaluate(position);
                if(score > bestScore) {
                    bestBoard = position;
                    bestScore = score;
                    bestBoardLinesCleared = currentLinesCleared;
                }
            }

            if(bestBoard == null) {
                if(extendedInfo) {
                    System.out.println("GAME OVER!");
                }
                System.out.println("Lines cleared: " + linesCleared);
                return;
            }

            bestBoard.copyTo(board);
            linesCleared += bestBoardLinesCleared;
            if(extendedInfo) {
                System.out.println("Board Score: " + bestScore + "    (" + linesCleared + " lines cleared)");
            }

            Thread.sleep(200);
        }
    }

    private static void playerGame() throws Exception {
        Piece[] pieces = {new Ipiece(), new Jpiece(), new Lpiece(), new Opiece(), new Spiece(), new Tpiece(), new Zpiece()};
        Random random = new Random();

        PlayableBoard board = new PlayableBoard(10, 20);
        GameWindow window = initGameWindow(board.getCurrentBoard());

        KeySubscription left = window.getInputController().getKeyInputController().addKeyListener(KeyEvent.VK_LEFT);
        KeySubscription right = window.getInputController().getKeyInputController().addKeyListener(KeyEvent.VK_RIGHT);
        KeySubscription down = window.getInputController().getKeyInputController().addKeyListener(KeyEvent.VK_DOWN);
        KeySubscription rotate = window.getInputController().getKeyInputController().addKeyListener(KeyEvent.VK_UP);
        KeySubscription hardDrop = window.getInputController().getKeyInputController().addKeyListener(KeyEvent.VK_SPACE);

        long lastDownMove = System.currentTimeMillis();

        int linesCleared = 0;

        while(true) {
            if(left.justPressed()) {
                board.moveLeft();
            }
            if(right.justPressed()) {
                board.moveRight();
            }
            if(down.justPressed()) {
                board.moveDown();
            }
            if(rotate.justPressed()) {
                board.rotate();
            }
            if(hardDrop.justPressed()) {
                board.dropPiece();
            }

            if(!board.hasDroppingPiece()) {
                linesCleared += board.clearLines();
                Piece piece = pieces[random.nextInt(0, pieces.length)];
                board.newPiece(piece);
                System.out.println("Lines cleared: " + linesCleared);
            }

            if(System.currentTimeMillis() - lastDownMove > 800) {
                board.moveDown();
                lastDownMove = System.currentTimeMillis();
            }

            Thread.sleep(20);
        }
    }

    private static GameWindow initGameWindow(Board board) {
        TetrisGameWindow window = new TetrisGameWindow(board, new ClassPathImageLoader(), "/com.github.bitfexl.tetris.assets/blocks/");
        window.setTitle("Tetris Game");
//        window.enableDebugging();
        openGameWindow(window);
        return window.getWindow();
    }

    private static void openGameWindow(TetrisGameWindow window) {
        new Thread(() -> {
            try {
                window.mainLoop();
            } catch (Exception ex) {
                ex.printStackTrace();
                System.exit(1);
            }
        }).start();

        while (!window.isOpen()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) { }
        }
    }
}
