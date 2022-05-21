package com.github.bitfexl.tetris.javagame.render.component;

import com.github.bitfexl.tetris.javagame.render.RenderProps;

import java.awt.*;

public class BackgroundFiller implements DrawLayer {
    /**
     * The background color to use.
     */
    private Color color;

    /**
     * Init a new BackgroundFiller
     * @param color The color to fill with.
     */
    public BackgroundFiller(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g2d, RenderProps renderProps) {
        g2d.setColor(color);
        g2d.fillRect(0, 0, renderProps.getWidth(), renderProps.getHeight());
    }

    @Override
    public boolean renderBelowLayers() {
        return false;
    }
}
