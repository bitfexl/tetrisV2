package com.github.bitfexl.tetris.javagame.render;

public class RenderProps {
    /**
     * The width of the canvas.
     */
    private int width;

    /**
     * The height of the canvas.
     */
    private int height;

    /**
     * The time between frames in ms.
     */
    private int frameTime;

    /**
     * The time it took to render the last frame.
     */
    private int lastFrameRenderTime;

    /**
     * The width of the canvas.
     */
    public int getWidth() {
        return width;
    }

    /**
     * The width of the canvas. Does not actually change it.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * The height of the canvas.
     */
    public int getHeight() {
        return height;
    }

    /**
     * The height of the canvas. Does not actually change it.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * The time between frames in ms.
     */
    public int getFrameTime() {
        return frameTime;
    }

    /**
     * The time between frames in ms. Does not actually change it. Should only be invoked by Renderer.
     */
    public void setFrameTime(int frameTime) {
        this.frameTime = frameTime;
    }

    /**
     * fps: 1000 / frameTime
     */
    public double getFPS() {
        try {
            return 1000.0 / (double)frameTime;
        } catch (ArithmeticException ex) {
            return 9999;
        }
    }

    /**
     * fps as int
     */
    public int getIntFPS() {
        try {
            return 1000 / frameTime;
        } catch (ArithmeticException ex) {
            return 9999;
        }
    }

    /**
     * The time it took to render the last frame.
     */
    public int getLastFrameRenderTime() {
        return lastFrameRenderTime;
    }

    /**
     * The time it took to render the last frame. Should only be invoked by Renderer.
     */
    public void setLastFrameRenderTime(int lastFrameRenderTime) {
        this.lastFrameRenderTime = lastFrameRenderTime;
    }
}
