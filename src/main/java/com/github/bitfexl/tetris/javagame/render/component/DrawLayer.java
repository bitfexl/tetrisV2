package com.github.bitfexl.tetris.javagame.render.component;

import com.github.bitfexl.tetris.javagame.exception.RenderException;
import com.github.bitfexl.tetris.javagame.render.RenderProps;

import java.awt.*;

public interface DrawLayer {
    /**
     * Draw the layers content on the given graphics object.
     * @param g2d The graphics object to draw on. Do not call dispose().
     * @param renderProps The properties of the current (frame). Setters should not be called.
     */
    void draw(Graphics2D g2d, RenderProps renderProps) throws RenderException;

    /**
     * Gets called before rendering.
     * @return true (should be default): render all layers below, false: stop with this layer.
     */
    boolean renderBelowLayers();
}
