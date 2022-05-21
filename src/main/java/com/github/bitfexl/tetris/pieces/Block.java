package com.github.bitfexl.tetris.pieces;

/**
 * A block that holds information for drawing (image path), must be final.
 */
public class Block {
    /**
     * The name of the image file.
     */
    private String imagePath;

    public Block(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }
}
