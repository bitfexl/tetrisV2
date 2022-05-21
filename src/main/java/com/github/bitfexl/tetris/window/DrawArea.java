package com.github.bitfexl.tetris.window;

/**
 * Draw area properties.
 */
public class DrawArea {
    /**
     * The x coordinate.
     */
    private int x;

    /**
     * The y coordinate.
     */
    private int y;

    /**
     * The width of the draw area.
     */
    private int width;

    /**
     * The height of the draw area.
     */
    private int height;

    public DrawArea(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
