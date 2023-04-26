package SIgame.view;

import javax.swing.*;
import SIgame.model.*;
import java.awt.*;
import SIgame.controller.*;

public class SpaceGUI
{
    JFrame menuFrame;
    JFrame gameFrame;
    JPanel titleScreen;
    JPanel gameScreen;
    JLabel image;
    ImageIcon icon;
    TankView tankView;
    AlienView alienView;
    LifeView lifeView;
    GameController controller;
    ScoreModel score;
    ScoreView scoreView;
    AlienArmada alienArmada;
    String difficulty = "Normal"; //hard coded for now

    public SpaceGUI(GameController gameController, ScoreModel score, TankView tankView, LifeView lifeView, AlienArmada alienArmada, ScoreView scoreView) 
    {
        this.controller = gameController;
        this.score = score;
        this.tankView = tankView;
        this.alienView = alienView;
        this.lifeView = lifeView;
        this.alienArmada = alienArmada;
        this.scoreView = scoreView;


        gameFrame = new JFrame("Space Invaders");
        gameScreen = new JPanel();

        drawGameScreen(difficulty, this.tankView);
    }

    public void addLaserToGameScreen(LaserView laserView) 
    {
        gameScreen.add(laserView);
        gameScreen.revalidate();
        gameScreen.repaint();
    }

    public TankView getTankView() 
    {
        return tankView;
    }

    public JPanel getGameScreen() 
    {
        return gameScreen;
    }

    public void drawGameScreen(String difficulty, TankView tankVieww) 
    {
        int alienStartX = 50;
        gameFrame = new JFrame(difficulty);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        gameScreen.setBackground(Color.BLACK);
        gameScreen.setPreferredSize(new Dimension(640, 480));
        gameScreen.setLayout(null);
    
    
        gameScreen.add(scoreView);
    
        JLabel highScoreLabel = new JLabel("High Score: 35");
        highScoreLabel.setForeground(Color.WHITE);
        highScoreLabel.setBounds(120, 10, 100, 30);
        gameScreen.add(highScoreLabel);
    
        for (AlienController alienController : alienArmada.getAliens()) {
            AlienView alienView = alienController.getAlienView();
            AlienModel alienModel = alienController.getAlienModel();
            alienView.setBounds(alienModel.getX(), alienModel.getY(), 40, 40);
            gameScreen.add(alienView);
        }
    
        gameScreen.add(this.lifeView);
        gameScreen.add(tankVieww);
    
        gameFrame.add(gameScreen);
        gameFrame.pack();
        gameFrame.setVisible(true);
    }
    
    public void addLaser(LaserModel laserModel) 
    {
        LaserView laserView = new LaserView(laserModel, Color.red);
        laserView.setBounds(laserModel.getX(), laserModel.getY(), LaserView.WIDTH, LaserView.HEIGHT);
        gameScreen.add(laserView);
        gameScreen.repaint();
    }
    
    public void addBarrierToGameScreen(BarrierView barrierView) {
        gameScreen.add(barrierView);
    }
}
