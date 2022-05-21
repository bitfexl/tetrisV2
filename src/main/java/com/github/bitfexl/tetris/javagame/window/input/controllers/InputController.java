package com.github.bitfexl.tetris.javagame.window.input.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputController implements KeyListener {
    /**
     * The text input controller.
     */
    private TextInputController textInputController;

    /**
     * The key input controller. Blocked on text read.
     */
    private KeyInputController keyInputController;

    /**
     * The function key input controller. Does not get blocked on text read.
     */
    private KeyInputController functionKeyInputController;

    public InputController() {
        this.textInputController = new TextInputController();
        this.keyInputController = new KeyInputController();
        this.functionKeyInputController = new KeyInputController();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(textInputController.hasActiveSubscription()) {
            textInputController.keyTyped(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!textInputController.hasActiveSubscription()) {
            keyInputController.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyInputController.keyReleased(e);
    }

    public TextInputController getTextInputController() {
        return textInputController;
    }

    public KeyInputController getKeyInputController() {
        return keyInputController;
    }

    public KeyInputController getFunctionKeyInputController() {
        return functionKeyInputController;
    }
}
