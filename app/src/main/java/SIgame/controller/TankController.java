package SIgame.controller;

import SIgame.ControllerInterface;
import SIgame.model.TankModel;
import SIgame.view.TankView;
import SIgame.model.LaserModel;
import SIgame.view.LaserView;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.*;
import java.io.InputStream;
import java.io.BufferedInputStream;

public class TankController implements KeyListener, ControllerInterface, ActionListener
{
    private TankModel model;
    private TankView view;
    private GameController gameController;
    private Timer leftTimer;
    private Timer rightTimer;
    
    public TankController(TankModel model, TankView view, GameController gameController) 
    {
        this.model = model;
        model.setSpeed(3);
        this.view = view;
        this.gameController = gameController;
        view.addKeyListener(this);
        view.setFocusable(true);
        view.requestFocusInWindow();
        leftTimer = new Timer(10, this);
        rightTimer = new Timer(10, this);
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
        try 
        {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("laser.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(inputStream));
            DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioInputStream);
            clip.start();
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
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
                if (!leftTimer.isRunning()) {
                    leftTimer.start();
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (!rightTimer.isRunning()) {
                    rightTimer.start();
                }
                break;
            case KeyEvent.VK_SPACE:
                shootLaser();
                break;
        }
        updateView();
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        switch (keyCode)
        {
            case KeyEvent.VK_LEFT:
                leftTimer.stop();
                break;
            case KeyEvent.VK_RIGHT:
                rightTimer.stop();
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == leftTimer) {
            model.moveLeft();
        } else if (e.getSource() == rightTimer) {
            model.moveRight();
        }
        updateView();
    }

    @Override
    public void keyTyped(KeyEvent e){}
}

