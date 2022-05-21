package com.github.bitfexl.tetris.javagame.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Load an image from classpath.
 */
public class ClassPathImageLoader extends ImageLoader {
    @Override
    public BufferedImage loadImage(String imgPath, boolean cache) throws IOException {
        if(imageCache.containsKey(imgPath)) {
            return imageCache.get(imgPath);
        }
        BufferedImage image = ImageIO.read(this.getClass().getResourceAsStream(imgPath));
        if(cache) {
            imageCache.put(imgPath, image);
        }
        return image;
    }
}
