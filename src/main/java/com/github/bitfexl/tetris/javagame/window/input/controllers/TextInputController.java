package com.github.bitfexl.tetris.javagame.window.input.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TextInputController implements KeyListener {
    /**
     * The currently active subscription
     */
    private TextInputSubscription activeSubscription;

    /**
     * The current read.
     */
    private String currText;

    public TextInputController() {
        currText = "";
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() != KeyEvent.CHAR_UNDEFINED) {
            currText = currText + e.getKeyChar();
        }
    }

    /**
     * Make a new subscription, stops the old one.
     * @return The new subscription.
     */
    public TextInputSubscription subscribe() {
        if(activeSubscription != null) {
            activeSubscription.stop();
        }
        currText = "";

        activeSubscription = new TextInputSubscription() {
            private String finalText;

            @Override
            public boolean isActive() {
                return finalText == null;
            }

            @Override
            public void stop() {
                finalText = currText;
            }

            @Override
            public String read() {
                if(finalText == null) {
                    return currText;
                } else {
                    return finalText;
                }
            }
        };

        return activeSubscription;
    }

    /**
     * Tests if the subscription is still active.
     * @return true: active, false: stopped
     */
    public boolean hasActiveSubscription() {
        if(activeSubscription != null) {
            return activeSubscription.isActive();
        }
        return false;
    }

    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }
}
