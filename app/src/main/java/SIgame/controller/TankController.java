package SIgame.controller;

import SIgame.ControllerInterface;
import SIgame.model.TankModel;
import SIgame.view.TankView;
import SIgame.model.LaserModel;
import SIgame.view.LaserView;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TankController implements KeyListener, ControllerInterface
{
    private TankModel model;
    private TankView view;
    private GameController gameController;
    
    public TankController(TankModel model, TankView view, GameController gameController) 
    {
        this.model = model;
        model.setSpeed(15);
        this.view = view;
        this.gameController = gameController;
        view.addKeyListener(this);
        view.setFocusable(true);
        view.requestFocusInWindow();
    }

    public void setTankView(TankView view) 
    {
        this.view = view;
        view.addKeyListener(this);
        view.setFocusable(true);
    }

    public void updateView() 
    {
        view.setBounds(model.getX(), model.getY(), 60, 60);
        view.repaint();
    }

    public void setGameController(GameController gameController) 
    {
        this.gameController = gameController;
    }
    
    private void shootLaser() 
    {
        LaserModel laserModel = new LaserModel(model.getX() + 30, model.getY() - 10, 5);
        LaserView laserView = new LaserView(laserModel, Color.RED);
        gameController.addLaser(laserModel, laserView);
    }    

    @Override
    public void handleInput(KeyEvent e) 
    {
        int keyCode = e.getKeyCode();
        switch(keyCode) {
            case KeyEvent.VK_LEFT:
                model.moveLeft();
                break;
            case KeyEvent.VK_RIGHT :
                model.moveRight();
                break;
            case KeyEvent.VK_SPACE:
                shootLaser();
                break;
        }
        updateView();
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        int keyCode = e.getKeyCode();
        switch (keyCode) 
        {
            case KeyEvent.VK_LEFT:
                model.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                model.moveRight();
                break;
            case KeyEvent.VK_SPACE:
                shootLaser();
                break;
        }
        updateView();
    }

    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void keyReleased(KeyEvent e){}  
    
}
