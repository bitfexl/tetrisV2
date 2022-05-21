package com.github.bitfexl.tetris.javagame.window;

import com.github.bitfexl.tetris.javagame.render.GameCanvas;
import com.github.bitfexl.tetris.javagame.render.LayerManager;
import com.github.bitfexl.tetris.javagame.render.RenderProps;
import com.github.bitfexl.tetris.javagame.render.Renderer;
import com.github.bitfexl.tetris.javagame.window.input.controllers.InputController;

import javax.swing.*;

public class GameWindow extends JFrame {
    /**
     * The render props for this window.
     */
    private RenderProps renderProps;

    /**
     * The layer manager for this window.
     */
    private LayerManager layerManager;

    /**
     * The game canvas for the window.
     */
    private GameCanvas gameCanvas;

    /**
     * The renderer for the window.
     */
    private Renderer renderer;

    /**
     * The keyboard manager for this window.
     */
    private InputController inputController;

    /**
     * Construct a new window. Does not open it.
     * @param width The width of the window.
     * @param height The height of the window.
     */
    public GameWindow(int width, int height) {
        this.renderProps = new RenderProps();
        renderProps.setWidth(width);
        renderProps.setHeight(height);
    }

    /**
     * Initialize the game canvas (and add to window), layer manager and renderer.
     * Initialize input listeners.
     * @return this
     */
    public GameWindow init() {
        this.gameCanvas = new GameCanvas(renderProps);
        this.layerManager = new LayerManager();
        this.renderer = new Renderer(renderProps, gameCanvas, layerManager);

        gameCanvas.setSize(renderProps.getWidth(), renderProps.getHeight());
        gameCanvas.setFocusable(false);
        add(gameCanvas);
        pack();

        this.inputController = new InputController();
        addKeyListener(inputController);

        return this;
    }

    public RenderProps getRenderProps() {
        return renderProps;
    }

    public LayerManager getLayerManager() {
        return layerManager;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public InputController getInputController() {
        return inputController;
    }
}
