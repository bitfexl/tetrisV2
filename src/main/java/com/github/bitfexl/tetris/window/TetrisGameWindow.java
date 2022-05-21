package com.github.bitfexl.tetris.window;

import com.github.bitfexl.tetris.board.Board;
import com.github.bitfexl.tetris.javagame.render.LayerManager;
import com.github.bitfexl.tetris.javagame.render.component.BackgroundFiller;
import com.github.bitfexl.tetris.javagame.render.component.DrawLayer;
import com.github.bitfexl.tetris.javagame.render.component.RenderPropsOverlay;
import com.github.bitfexl.tetris.javagame.util.ClassPathImageLoader;
import com.github.bitfexl.tetris.javagame.util.ImageLoader;
import com.github.bitfexl.tetris.javagame.window.GameWindow;
import com.github.bitfexl.tetris.pieces.Lpiece;
import com.github.bitfexl.tetris.pieces.Piece;

import javax.swing.*;
import java.awt.*;

public class TetrisGameWindow {
    public interface WindowStop {
        /**
         * Stop rendering and close window.
         */
        void stop() throws InterruptedException;
    }

    private static final int CANVAS_WIDTH = 601;
    private static final int CANVAS_HEIGHT = 601;

    /**
     * The window title.
     */
    private String title;

    /**
     * The game window.
     */
    private GameWindow window;

    /**
     * The board to display.
     */
    private Board tetrisBoard;

    /**
     * Image loader to load pieces/blocks.
     */
    private ImageLoader imageLoader;

    /**
     * The pieces/blocks image path.
     */
    private String imageBasePath;

    /**
     * The two pieces to display.
     */
    PieceDisplayLayer holdPiece, previewPiece;

    /**
     * If the window is open or not.
     */
    private boolean open;

    /**
     * Enable debugging info.
     */
    private boolean debug;

    /**
     * Stop the main loop by setting false.
     */
    private boolean mainLoop;

    public TetrisGameWindow(Board tetrisBoard, ImageLoader imageLoader, String imageBasePath) {
        this.tetrisBoard = tetrisBoard;
        this.imageLoader = imageLoader;
        this.imageBasePath = imageBasePath;
        this.debug = false;
        this.open = false;
        this.mainLoop = true;
    }

    /**
     * Start the window.
     */
    public void mainLoop() throws Exception {
        window = new GameWindow(CANVAS_WIDTH, CANVAS_HEIGHT);
        window.setTitle(title);

        window.init();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);

        addLayers(window.getLayerManager());

        window.setVisible(true);
        open = true;

        while (mainLoop) {
            window.getRenderer().render();
        }
    }

    private void addLayers(LayerManager layerManager) {
        DrawArea boardArea = new DrawArea(150, 0, 300, 600);
        BoardBackgroundLayer backgroundLayer = new BoardBackgroundLayer(tetrisBoard, boardArea);
        backgroundLayer.setSeparatorColor(new Color(60, 99, 130));
        backgroundLayer.setBgColor(new Color(10, 61, 98));
        BoardLayer boardLayer = new BoardLayer(imageLoader, imageBasePath, tetrisBoard, boardArea);
        // todo: enable set
        holdPiece = new PieceDisplayLayer(imageLoader, imageBasePath, new DrawArea(25, 75, 90, 90));
        holdPiece.setBgColor(new Color(10, 61, 98));
        holdPiece.setBorderColor(new Color(47, 54, 64));
        // todo: enable set
        previewPiece = new PieceDisplayLayer(imageLoader, imageBasePath, new DrawArea(483, 75, 90, 90));
        previewPiece.setBgColor(new Color(10, 61, 98));
        previewPiece.setBorderColor(new Color(47, 54, 64));

        layerManager.addLayer(new BackgroundFiller(new Color(64, 115, 158)));
        layerManager.addLayer(backgroundLayer);
        layerManager.addLayer(boardLayer);
        layerManager.addLayer(holdPiece);
        layerManager.addLayer(previewPiece);

        if(debug) {
            layerManager.addLayer(new RenderPropsOverlay());
        }
    }

    /**
     * Render debug info. Must be called before mainLoop().
     */
    public void enableDebugging() {
        debug = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        if(window != null) {
            window.setTitle(title);
        }
    }

    public boolean isOpen() {
        return open;
    }

    public boolean isDebug() {
        return debug;
    }

    public GameWindow getWindow() {
        return window;
    }

    public void setHoldPiece(Piece piece) {
        holdPiece.setPiece(piece);
    }

    public void setPreviewPiece(Piece piece) {
        previewPiece.setPiece(piece);
    }

    /**
     * Open the window and start a rendering thread.
     * @return A windowStop object for shutdown of the window and thread.
     */
    public WindowStop openGameWindow() {
        Thread windowThread = new Thread(() -> {
            try {
                mainLoop = true;
                this.mainLoop();
            } catch (Exception ex) {
                ex.printStackTrace();
                System.exit(1);
            }
        });
        windowThread.start();

        while (!this.isOpen()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) { }
        }

        return () -> {
            mainLoop = false;
            windowThread.join();
            window.dispose();
        };
    }
}
