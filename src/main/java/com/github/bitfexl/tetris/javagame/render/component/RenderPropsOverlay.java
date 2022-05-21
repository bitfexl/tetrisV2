package com.github.bitfexl.tetris.javagame.render.component;

import com.github.bitfexl.tetris.javagame.render.RenderProps;

import java.awt.*;

public class RenderPropsOverlay implements DrawLayer {
    @Override
    public void draw(Graphics2D g2d, RenderProps renderProps) {
        String frameRenderTimeMsg = "FRAME RENDER TIME: " + renderProps.getLastFrameRenderTime() + "ms";
        String frameTimeMsg = "FRAME TIME: " + renderProps.getFrameTime() + "ms";
        String fpsMsg = "FPS: " + renderProps.getIntFPS();

        g2d.setColor(Color.BLACK);
        g2d.drawString(frameRenderTimeMsg, 10, 20);
        g2d.drawString(frameTimeMsg + " -- " + fpsMsg, 10, 35);
    }

    @Override
    public boolean renderBelowLayers() {
        return true;
    }
}
