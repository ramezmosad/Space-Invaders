package SIgame.controller;

import SIgame.ControllerInterface;
import SIgame.model.TankModel;
import SIgame.view.TankView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TankController implements KeyListener, ControllerInterface {
    private TankModel model;
    private TankView view;

    public TankController(TankModel model, TankView view) {
        this.model = model;
        this.view = view;
        view.addKeyListener(this);
        view.setFocusable(true);
    }

    public void updateView() {
        view.setBounds(model.getX(), model.getY(), 60, 60);
        view.repaint();
    }

    @Override
    public void handleInput(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch(keyCode) {
            case KeyEvent.VK_LEFT:
                model.moveLeft();
                break;
            case KeyEvent.VK_RIGHT :
                model.moveRight();
                break;
        }
        updateView();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        handleInput(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Do nothing
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Do nothing
    }
}
