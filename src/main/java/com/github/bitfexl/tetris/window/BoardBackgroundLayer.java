package com.github.bitfexl.tetris.window;

import com.github.bitfexl.tetris.board.Board;
import com.github.bitfexl.tetris.javagame.exception.RenderException;
import com.github.bitfexl.tetris.javagame.render.RenderProps;
import com.github.bitfexl.tetris.javagame.render.component.DrawLayer;

import java.awt.*;

public class BoardBackgroundLayer implements DrawLayer {
    /**
     * The board to draw a background for.
     */
    private Board board;

    /**
     * The area where to draw the background.
     */
    private DrawArea drawArea;

    /**
     * The background color.
     */
    private Color bgColor;

    /**
     * The line color between the squares.
     */
    private Color separatorColor;

    public BoardBackgroundLayer(Board board, DrawArea drawArea) {
        this.board = board;
        this.drawArea = drawArea;
    }

    @Override
    public void draw(Graphics2D g2d, RenderProps renderProps) throws RenderException {
        g2d.setColor(bgColor);
        g2d.fillRect(drawArea.getX(), drawArea.getY(), drawArea.getWidth(), drawArea.getHeight());

        int blockWidth = (int)((float)drawArea.getWidth() / board.getWidth());
        int blockHeight = (int)((float)drawArea.getHeight() / board.getHeight());

        g2d.setColor(separatorColor);
        for(int i=0; i<=board.getWidth(); i++) {
            g2d.drawLine(drawArea.getX() + blockWidth * i, drawArea.getY(), drawArea.getX() + blockWidth * i, drawArea.getY() + drawArea.getHeight());
        }
        for(int i=0; i<=board.getHeight(); i++) {
            g2d.drawLine(drawArea.getX(), drawArea.getY() + blockHeight * i, drawArea.getX() + drawArea.getWidth(), drawArea.getY() + blockHeight * i);
        }
    }

    public Color getBgColor() {
        return bgColor;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    public Color getSeparatorColor() {
        return separatorColor;
    }

    public void setSeparatorColor(Color separatorColor) {
        this.separatorColor = separatorColor;
    }

    @Override
    public boolean renderBelowLayers() {
        return true;
    }
}
