package com.github.bitfexl.tetris.window;

import com.github.bitfexl.tetris.javagame.exception.RenderException;
import com.github.bitfexl.tetris.javagame.render.RenderProps;
import com.github.bitfexl.tetris.javagame.render.component.DrawLayer;
import com.github.bitfexl.tetris.javagame.util.ImageLoader;
import com.github.bitfexl.tetris.pieces.Piece;

import java.awt.*;
import java.io.IOException;

public class PieceDisplayLayer implements DrawLayer {
    /**
     * The image loader used to load the block images.
     */
    private ImageLoader imageLoader;

    /**
     * The base image folder path.
     */
    private String basePath;

    /**
     * The area where to draw the piece. Should be quadratic.
     */
    private DrawArea drawArea;

    /**
     * Background color.
     */
    private Color bgColor;

    /**
     * Border color.
     */
    private Color borderColor;

    /**
     * The piece to display.
     */
    private Piece piece;

    public PieceDisplayLayer(ImageLoader imageLoader, String basePath, DrawArea drawArea) {
        this.imageLoader = imageLoader;
        this.basePath = basePath;
        this.drawArea = drawArea;
    }

    @Override
    public void draw(Graphics2D g2d, RenderProps renderProps) throws RenderException {
        g2d.setColor(bgColor);
        g2d.fillRect(drawArea.getX(), drawArea.getY(), drawArea.getWidth(), drawArea.getHeight());
        g2d.setColor(borderColor);
        g2d.drawRect(drawArea.getX(), drawArea.getY(), drawArea.getWidth(), drawArea.getHeight());

        if(piece ==  null) {
            return;
        }

        // 4x4 grid for drawing
        int blockWidth = drawArea.getWidth() / 4;
        int blockHeight = drawArea.getHeight() / 4;

        for(byte[] cords : piece.getCoordinates(0)) {
            try {
                g2d.drawImage(imageLoader.loadImage(basePath + piece.getBlock().getImagePath(), true),
                        drawArea.getX() + (cords[0] + 1) * blockWidth,
                        drawArea.getY() + (cords[1] + 1) * blockHeight,
                        blockWidth,
                        blockHeight,
                        null, null);
            } catch (IOException ex) {
                throw new RenderException("Cannot load image.", ex);
            }
        }
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    @Override
    public boolean renderBelowLayers() {
        return true;
    }

    public Color getBgColor() {
        return bgColor;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }
}
