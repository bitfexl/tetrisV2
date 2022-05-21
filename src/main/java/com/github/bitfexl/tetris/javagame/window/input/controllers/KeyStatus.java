package com.github.bitfexl.tetris.javagame.window.input.controllers;

import com.github.bitfexl.tetris.javagame.util.Time;

public class KeyStatus {
    /**
     * The time the key was pressed down.
     */
    private long holdStartTime;

    /**
     * The time the key was released.
     */
    private long releaseStartTime;

    /**
     * The time the key was held.
     */
    private long holdTime;

    /**
     * The time the key was released.
     */
    private long releaseTime;

    /**
     * If the key is currently pressed.
     */
    private boolean isPressed;

    public KeyStatus() {
        releaseStartTime = Time.getCurrentTimeMs();
        releaseTime = 0;
        setPressed(false);
    }

    /**
     * The time the key has been held (isPressed = false) or is currently held (isPressed = true).
     */
    public long getHoldTime() {
        if(isPressed) {
            return Time.getCurrentTimeMs() - holdStartTime;
        }
        return holdTime;
    }

    /**
     * Returns the time the key has been released or 0 if pressed.
     * @return The time in ms.
     */
    public long getReleasedTime() {
        if(!isPressed) {
            return Time.getCurrentTimeMs() - releaseStartTime;
        }
        return releaseTime;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(boolean pressed) {
        long current = Time.getCurrentTimeMs();
        if(pressed) {
            holdStartTime = current;
            releaseTime = current - releaseStartTime;
        } else {
            releaseStartTime = current;
            holdTime = current - holdStartTime;
        }
        isPressed = pressed;
    }
}
