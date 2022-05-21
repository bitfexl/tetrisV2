package com.github.bitfexl.tetris.javagame.render;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameCanvas extends Canvas {
    /**
     * The render props. Width and height must be set manually.
     */
    private RenderProps renderProps;

    /**
     * The double buffer strategy.
     */
    private BufferStrategy bufferStrategy;

    /**
     * Init a new game canvas.
     * @param renderProps The render props for layer components.
     */
    public GameCanvas(RenderProps renderProps) {
        setIgnoreRepaint(true);
        this.renderProps = renderProps;
    }

    /**
     * Show the rendered content.
     */
    public void displayRender() {
        bufferStrategy.show();
    }

    /**
     * Get graphics object for the next frame.
     * @throws IllegalStateException Component is not visible.
     * @return The graphics object for the next frame.
     */
    public Graphics2D getRenderGraphics() {
        if(bufferStrategy == null) {
            createBufferStrategy(2);
            bufferStrategy = getBufferStrategy();
        }

        Graphics g = bufferStrategy.getDrawGraphics();
        return (Graphics2D)g;
    }

    /**
     * Finis the render. Display with displayRender().
     * @param g2d The current graphics object.
     */
    public void finishRender(Graphics2D g2d) {
        g2d.dispose();
    }
}
