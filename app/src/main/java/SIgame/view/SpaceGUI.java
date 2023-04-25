package SIgame.view;

import javax.swing.*;
import SIgame.model.*;
import java.awt.*;
import SIgame.controller.GameController;

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
    Score score;
    String difficulty = "Normal"; //hard coded for now

    public SpaceGUI(GameController gameController, Score score, TankView tankView, AlienView alienView, LifeView lifeView) 
    {
        this.controller = gameController;
        this.score = score;
        this.tankView = tankView;
        this.alienView = alienView;
        this.lifeView = lifeView;

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

        JLabel scoreLabel = new JLabel("Score: 5");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(20, 10, 80, 30);
        gameScreen.add(scoreLabel);

        JLabel highScoreLabel = new JLabel("High Score: 35");
        highScoreLabel.setForeground(Color.WHITE);
        highScoreLabel.setBounds(120, 10, 100, 30);
        gameScreen.add(highScoreLabel);
        this.alienView.setBounds(alienStartX + (0 * 50), 50, 40, 40);
        
        gameScreen.add(this.alienView);
        gameScreen.add(this.lifeView);
        gameScreen.add(tankVieww);

        gameFrame.add(gameScreen);
        gameFrame.pack();
        gameFrame.setVisible(true);
    }

    public void addLaser(LaserModel laserModel) 
    {
        LaserView laserView = new LaserView(laserModel);
        laserView.setBounds(laserModel.getX(), laserModel.getY(), LaserView.WIDTH, LaserView.HEIGHT);
        gameScreen.add(laserView);
        gameScreen.repaint();
    }
}
