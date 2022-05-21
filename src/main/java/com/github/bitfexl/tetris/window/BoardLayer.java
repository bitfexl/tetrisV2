package com.github.bitfexl.tetris.window;

import com.github.bitfexl.tetris.board.Board;
import com.github.bitfexl.tetris.javagame.exception.RenderException;
import com.github.bitfexl.tetris.javagame.render.RenderProps;
import com.github.bitfexl.tetris.javagame.render.component.DrawLayer;
import com.github.bitfexl.tetris.javagame.util.ImageLoader;

import java.awt.*;
import java.io.IOException;

public class BoardLayer implements DrawLayer {
    /**
     * The image loader used to load the block images.
     */
    private ImageLoader imageLoader;

    /**
     * The base image folder path.
     */
    private String basePath;

    /**
     * The board to draw pieces for.
     */
    private Board board;

    /**
     * The area where to draw the pieces.
     */
    private DrawArea drawArea;

    public BoardLayer(ImageLoader imageLoader, String basePath, Board board, DrawArea drawArea) {
        this.imageLoader = imageLoader;
        this.basePath = basePath;
        this.board = board;
        this.drawArea = drawArea;
    }

    @Override
    public void draw(Graphics2D g2d, RenderProps renderProps) throws RenderException {
        int blockWidth = (int)((float)drawArea.getWidth() / board.getWidth());
        int blockHeight = (int)((float)drawArea.getHeight() / board.getHeight());

        try {
            for (int x = 0; x < board.getWidth(); x++) {
                for (int y = 0; y < board.getHeight(); y++) {
                    if(board.isEmpty(x, y)) {
                        continue;
                    }

                    g2d.drawImage(imageLoader.loadImage(basePath + board.getBlock(x, y).getImagePath(), true),
                            drawArea.getX() + blockWidth * x,
                            drawArea.getY() + blockHeight * y,
                            blockWidth,
                            blockHeight,
                            Color.BLACK, null);
                }
            }
        } catch (IOException ex) {
            throw new RenderException("Cannot load image.", ex);
        }
    }

    @Override
    public boolean renderBelowLayers() {
        return true;
    }
}
