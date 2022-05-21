package com.github.bitfexl.tetris.javagame.exception;

/**
 * A render could not complete.
 */
public class RenderException extends Exception {
    public RenderException(String message) {
        super(message);
    }

    public RenderException(String message, Throwable cause) {
        super(message, cause);
    }
}
