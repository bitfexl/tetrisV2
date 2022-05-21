package com.github.bitfexl.tetris.javagame.window.input.controllers;

public interface KeySubscription {
    /**
     * Returns if the key is currently pressed.
     * @return boolean: currently pressed, false: not pressed
     */
    boolean isPressed();

    /**
     * Returns how long the key is already held (isPressed = true)
     * or how long the last press was (isPressed = false).
     * @return The hold time in ms.
     */
    long holdTime();

    /**
     * The time the key has been released (isPressed = false)
     * or how long the last release time was (isPressed = true).
     * @return The time in ms.
     */
    long releaseTime();

    /**
     * Returns if the key has just been pressed.
     * @return true: The key is pressed and this is the first call to justPressed, false: the key is not pressed or this is not the first call to justPressed;
     */
    boolean justPressed();

    /**
     * Returns if the key has just been released.
     * @return true: The key is not pressed and this is the first call to justReleased, false: the key is pressed or this is not the first call to justReleased;
     */
    boolean justReleased();

    /**
     * Mark current press as not handled (justPressed).
     */
    void resetJustPressHandled();

    /**
     * Mark current release as not handled (justReleased).
     */
    void resetJustReleasedHandled();

    /**
     * Returns the key code of the subscription.
     * @return The key code.
     */
    int getKeyCode();
}
