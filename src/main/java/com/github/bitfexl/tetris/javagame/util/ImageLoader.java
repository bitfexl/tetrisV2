package com.github.bitfexl.tetris.javagame.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ImageLoader {
    /**
     * The default image loader instance.
     */
    private static ImageLoader imageLoader;

    /**
     * Get the image loader instance.
     * @return The default instance.
     */
    public static ImageLoader getInstance() {
        if(imageLoader == null) {
            imageLoader = new ImageLoader();
        }
        return imageLoader;
    }

    /**
     * Load a image using the default instance and cache it.
     * @param imgPath The image path.
     * @return The image.
     * @throws IOException If the path is incorrect or the image does not exist.
     */
    public static BufferedImage loadImage(String imgPath) throws IOException {
        return getInstance().loadImage(imgPath, true);
    }

    /**
     * image path, image;
     */
    protected HashMap<String, BufferedImage> imageCache;

    /**
     * Use getInstance() for a default image loader.
     */
    public ImageLoader() {
        this.imageCache = new HashMap<>();
    }

    /**
     * Load an image from a file path.
     * @param imgPath The image path.
     * @param cache true: cache the image (for subsequent calls), false: ...
     * @return The read image.
     * @throws IOException If the path is incorrect or the image does not exist.
     */
    public BufferedImage loadImage(String imgPath, boolean cache) throws IOException {
        if(imageCache.containsKey(imgPath)) {
            return imageCache.get(imgPath);
        }

        BufferedImage image = ImageIO.read(new File(imgPath));

        if(cache) {
            imageCache.put(imgPath, image);
        }

        return image;
    }
}
