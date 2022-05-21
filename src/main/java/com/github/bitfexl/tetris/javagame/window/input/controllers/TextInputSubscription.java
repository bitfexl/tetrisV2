package com.github.bitfexl.tetris.javagame.window.input.controllers;

public interface TextInputSubscription {
    /**
     * Returns if the subscription is still active.
     * @return true: subscription still active, false: subscription is finished (no more keys added);
     */
    boolean isActive();

    /**
     * Stops the read.
     */
    void stop();

    /**
     * Read the current string or the finished read string.
     * @return The input. Will not be null (empty if input "").
     */
    String read();
}
