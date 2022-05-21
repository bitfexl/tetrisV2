package com.github.bitfexl.tetris.javagame.render.component;

import com.github.bitfexl.tetris.javagame.exception.RenderException;
import com.github.bitfexl.tetris.javagame.render.RenderProps;
import com.github.bitfexl.tetris.javagame.util.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageDrawer implements DrawLayer {
    /**
     * The x cord where to draw the image.
     */
    private int xCord;

    /**
     * The y cord where to draw the image.
     */
    private int yCord;

    /**
     * The x offset in pixel (before scaling).
     */
    private int xOffs;

    /**
     * The y offset in pixel (before scaling).
     */
    private int yOffs;

    /**
     * The scale factor.
     */
    private double scaleFactor;

    /**
     * Enable/disable image border.
     */
    private boolean borderEnabled;

    /**
     * The color of the border.
     */
    private Color borderColor;

    /**
     * The image to draw.
     */
    private BufferedImage image;

    public ImageDrawer() {
        this.scaleFactor = 1;
        this.borderEnabled = false;
        this.borderColor = Color.BLUE;
    }

    @Override
    public void draw(Graphics2D g2d, RenderProps renderProps) throws RenderException {
        int xCordFinal = (int)(xCord + (xOffs * scaleFactor) + 0.5);
        int yCordFinal = (int)(yCord + (yOffs * scaleFactor) + 0.5);

        int width = (int)(image.getWidth() * scaleFactor  + 0.5);
        int height = (int)(image.getWidth() * scaleFactor + 0.5);

        g2d.drawImage(image, xCordFinal, yCordFinal, width, height, null);

        if(borderEnabled) {
            g2d.setColor(borderColor);
            g2d.drawRect(xCord, yCord, width, height);
        }
    }

    @Override
    public boolean renderBelowLayers() {
        return true;
    }

    /**
     * Set the image/sprite for the next draw/frame. Set draw cords with setDrawCords().
     * @param image The image to draw.
     * @param xOffs The x offset.
     * @param yOffs The y offset.
     */
    public void set(BufferedImage image, int xOffs, int yOffs) {
        set(image, xOffs, yOffs, scaleFactor);
    }

    /**
     * Set the image/sprite for the next draw/frame. Set draw cords with setDrawCords().
     * @param image The image to draw.
     * @param xOffs The x offset.
     * @param yOffs The y offset.
     * @param scaleFactor The scale factor.
     */
    public void set(BufferedImage image, int xOffs, int yOffs, double scaleFactor) {
        this.image = image;
        this.xOffs = xOffs;
        this.yOffs = yOffs;
        this.scaleFactor = scaleFactor;
    }

    /**
     * Set the x and y cords for the next draw/frame.
     * @param x The x cord of the image (without offset and scaling).
     * @param y The y cord of the image (without offset and scaling).
     */
    public void setDrawCords(int x, int y) {
        xCord = x;
        yCord = y;
    }

    public int getXCord() {
        return xCord;
    }

    public void setXCord(int xCord) {
        this.xCord = xCord;
    }

    public int getYCord() {
        return yCord;
    }

    public void setYCord(int yCord) {
        this.yCord = yCord;
    }

    public int getXOffs() {
        return xOffs;
    }

    public void setXOffs(int xOffs) {
        this.xOffs = xOffs;
    }

    public int getYOffs() {
        return yOffs;
    }

    public void setYOffs(int yOffs) {
        this.yOffs = yOffs;
    }

    public double getScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(double scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    public boolean isBorderEnabled() {
        return borderEnabled;
    }

    public void setBorderEnabled(boolean borderEnabled) {
        this.borderEnabled = borderEnabled;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
