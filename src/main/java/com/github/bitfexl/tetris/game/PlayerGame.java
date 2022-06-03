package com.github.bitfexl.tetris.game;

import com.github.bitfexl.tetris.board.PlayableBoard;
import com.github.bitfexl.tetris.javagame.util.ClassPathImageLoader;
import com.github.bitfexl.tetris.javagame.window.input.controllers.KeyInputController;
import com.github.bitfexl.tetris.javagame.window.input.controllers.KeySubscription;
import com.github.bitfexl.tetris.pieces.Piece;
import com.github.bitfexl.tetris.pieces.generators.BagGenerator;
import com.github.bitfexl.tetris.pieces.management.PieceManager;
import com.github.bitfexl.tetris.window.TetrisGameWindow;

import java.awt.event.KeyEvent;

public class PlayerGame {
    public static void main(String[] args) throws InterruptedException {
        PlayableBoard board = new PlayableBoard(10, 20);
        PieceManager pieceManager = new PieceManager(new BagGenerator());

        TetrisGameWindow window = new TetrisGameWindow(board.getCurrentBoard(), new ClassPathImageLoader(), "/com.github.bitfexl.tetris.assets/blocks/");
        window.setTitle("Tetris Game");
//        window.enableDebugging();
        TetrisGameWindow.WindowStop stopWindow = window.openGameWindow();

        KeyInputController inputController = window.getWindow().getInputController().getKeyInputController();
        KeySubscription left = inputController.addKeyListener(KeyEvent.VK_LEFT);
        KeySubscription right = inputController.addKeyListener(KeyEvent.VK_RIGHT);
        KeySubscription down = inputController.addKeyListener(KeyEvent.VK_DOWN);
        KeySubscription rotate = inputController.addKeyListener(KeyEvent.VK_UP);
        KeySubscription hardDrop = inputController.addKeyListener(KeyEvent.VK_SPACE);
        KeySubscription hold = inputController.addKeyListener(KeyEvent.VK_C);

        long lastDownMove = System.currentTimeMillis();

        int linesCleared = 0;

        boolean holdAvailable = true;

        Piece currentPiece = null;

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
            if(hold.justPressed() && holdAvailable && currentPiece != null) {
                Piece piece = pieceManager.hold(currentPiece);
                window.setHoldPiece(currentPiece);
                if(piece == null) {
                    piece = pieceManager.consumeNext();
                }
                board.newPiece(piece);
                window.setPreviewPiece(pieceManager.peekNext());
                holdAvailable = false;
            }

            if(!board.hasDroppingPiece()) {
                linesCleared += board.clearLines();
                currentPiece = pieceManager.consumeNext();
                if(!board.newPiece(currentPiece)) {
                    System.out.println("GAME OVER!");
                    System.out.println("Lines cleared: " + linesCleared);
                    window.setTitle("GAME OVER! Score: " + linesCleared);
                    return;
                }
                window.setPreviewPiece(pieceManager.peekNext());
                holdAvailable = true;
                window.setTitle("Score: " + linesCleared);
            }

            if(System.currentTimeMillis() - lastDownMove > 800) {
                board.moveDown();
                lastDownMove = System.currentTimeMillis();
            }

            Thread.sleep(20);
        }
    }
}
