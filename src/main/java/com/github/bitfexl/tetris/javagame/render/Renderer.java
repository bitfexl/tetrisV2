package com.github.bitfexl.tetris.javagame.render;

import com.github.bitfexl.tetris.javagame.exception.RenderException;
import com.github.bitfexl.tetris.javagame.util.Time;

import java.awt.*;

public class Renderer {
    /**
     * The render props to update.
     */
    private RenderProps renderProps;

    /**
     * The game canvas.
     */
    private GameCanvas canvas;

    /**
     * The layers to render.
     */
    private LayerManager layerManager;

    /**
     * The maximum fps count.
     */
    private int maxFps;

    /**
     * The minimum frame time. Set with fps count.
     */
    private int minFrameTime;

    /**
     * The time the last render took.
     */
    private int lastRenderTime;

    /**
     * The time the last render was started.
     */
    private long lastRenderStartTime;

    /**
     * Init a new Renderer. Max FPS defaults to 30.
     * @param renderProps The props to update.
     * @param canvas The canvas to draw.
     */
    public Renderer(RenderProps renderProps, GameCanvas canvas, LayerManager layerManager) {
        this.renderProps = renderProps;
        this.canvas = canvas;
        this.layerManager = layerManager;
        setMaxFps(30);
        lastRenderStartTime = Time.getCurrentTimeMs();
    }

    /**
     * Render the next frame. If min frame time is not reached (too much fps) wait the given time.
     */
    public void render() throws InterruptedException, RenderException {
        int currentFrameTime = (int)(Time.getCurrentTimeMs() - lastRenderStartTime);
        lastRenderStartTime = Time.getCurrentTimeMs();

        Graphics2D renderGraphics = canvas.getRenderGraphics();
        layerManager.renderAll(renderGraphics, renderProps);
        canvas.finishRender(renderGraphics);

        lastRenderTime = (int)(Time.getCurrentTimeMs() - lastRenderStartTime);
        currentFrameTime += lastRenderTime;
        renderProps.setLastFrameRenderTime(lastRenderTime);

        int sleepTime = minFrameTime - currentFrameTime;
        if(sleepTime > 0) {
            Thread.sleep(sleepTime);
            currentFrameTime += sleepTime;
        }

        renderProps.setFrameTime(currentFrameTime);

        canvas.displayRender();
    }

    public int getMaxFps() {
        return maxFps;
    }

    public void setMaxFps(int maxFps) {
        this.maxFps = maxFps;
        this.minFrameTime = 1000 / maxFps;
    }
}
