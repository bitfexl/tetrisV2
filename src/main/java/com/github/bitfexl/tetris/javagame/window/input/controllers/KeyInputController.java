package com.github.bitfexl.tetris.javagame.window.input.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.HashSet;

// todo: fix justPressed and justReleased
public class KeyInputController implements KeyListener {
    /**
     * Key status for each key.
     */
    private HashMap<Integer, KeyStatus> keyStatus;

    /**
     * List of all keyListeners for reset just press handled and just released handled.
     */
    private HashMap<Integer, HashSet<KeySubscription>> keySubscriptions;

    public KeyInputController() {
        this.keyStatus = new HashMap<>();
        this.keySubscriptions = new HashMap<>();
    }

    /**
     * Get a subscription for a specific key.
     * @param keyCode The key to subscribe.
     * @return The subscription.
     */
    public KeySubscription addKeyListener(int keyCode) {
        KeySubscription subscription = new KeySubscription() {
            private boolean justPressHandled = true, justReleasedHandled = true;

            @Override
            public void resetJustPressHandled() {
                justPressHandled = false;
            }

            @Override
            public void resetJustReleasedHandled() {
                justReleasedHandled = false;
            }

            @Override
            public boolean isPressed() {
                return keyStatus.get(keyCode).isPressed();
            }

            @Override
            public long holdTime() {
                return keyStatus.get(keyCode).getHoldTime();
            }

            @Override
            public long releaseTime() {
                return keyStatus.get(keyCode).getReleasedTime();
            }

            @Override
            public boolean justPressed() {
                if(!justPressHandled) {
                    justPressHandled = true;
                    return true;
                }
                return false;
            }

            @Override
            public boolean justReleased() {
                if(!justReleasedHandled) {
                    justPressHandled = true;
                    return true;
                }
                return false;
            }

            @Override
            public int getKeyCode() {
                return keyCode;
            }
        };

        initKeyStatus(keyCode);
        initKeySubscriptions(keyCode);

        keySubscriptions.get(keyCode).add(subscription);

        return subscription;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        initKeyStatus(e.getKeyCode());
        initKeySubscriptions(e.getKeyCode());

        if(!keyStatus.get(e.getKeyCode()).isPressed()) {
            keySubscriptions.get(e.getKeyCode()).forEach(KeySubscription::resetJustReleasedHandled);
            keyStatus.get(e.getKeyCode()).setPressed(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        initKeyStatus(e.getKeyCode());
        initKeySubscriptions(e.getKeyCode());

        if(keyStatus.get(e.getKeyCode()).isPressed()) {
            keySubscriptions.get(e.getKeyCode()).forEach(KeySubscription::resetJustPressHandled);
            keyStatus.get(e.getKeyCode()).setPressed(false);
        }
    }

    private void initKeyStatus(int keyCode) {
        if(!keyStatus.containsKey(keyCode)) {
            keyStatus.put(keyCode, new KeyStatus());
        }
    }

    private void initKeySubscriptions(int keyCode) {
        if(!keySubscriptions.containsKey(keyCode)) {
            keySubscriptions.put(keyCode, new HashSet<>());
        }
    }
}
