package SIgame.controller;

import SIgame.view.*;
import SIgame.model.*;

import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.awt.EventQueue;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class GameController 
{
    private LifeModel lifeModel;
    private LifeView lifeView;
    private ScoreModel score;
    private TankView tankView;
    private SpaceGUI gui;
    private List<LaserModel> laserModels;
    private List<LaserView> laserViews;
    private List<LaserController> laserControllers;
    private List<BarrierView> barrierViews;
    private AlienArmada alienArmada;
    private ScoreView scoreView;
    private int alienSpeed;
    private boolean aliensRegenerating = false;
    private boolean directionChanged = false;
    private String difficulty;
    private Clip backgroundMusicClip;
    private TitleScreen titleScreen;

    public GameController(ScoreModel score, String difficulty) 
    {
        if (difficulty.equals("Hard"))
        {
            this.alienSpeed = 3;
        }
        else
        {
            this.alienSpeed = 1;
        }
        this.difficulty = difficulty;
        playBackgroundMusic();
        this.lifeView = new LifeView(3);
        this.lifeModel = new LifeModel();
        this.score = score;
        this.tankView = new TankView();
        this.alienArmada = new AlienArmada(this);
        this.laserModels = new ArrayList<>();
        this.laserViews = new ArrayList<>();
        this.laserControllers = new ArrayList<>();
        this.scoreView = new ScoreView(score);
        this.barrierViews = new ArrayList<>();
        this.gui = new SpaceGUI(this, score, this.tankView, lifeView, alienArmada, scoreView, difficulty);
        addBarriers();
        startGameLoop();
    }

    public void addLaser(LaserModel laserModel, LaserView laserView) 
    {
        laserModels.add(laserModel);
        laserViews.add(laserView);
        LaserController laserController = new LaserController(laserModel, laserView);
        laserControllers.add(laserController);
        gui.addLaserToGameScreen(laserView);
    }

    public void removeLaser(LaserModel laserModel, LaserView laserView) 
    {
        gui.getGameScreen().remove(laserView);
        gui.getGameScreen().revalidate();
        gui.getGameScreen().repaint();
    }    

    public void moveLasers() 
    {
        for (int i = 0; i < laserModels.size(); i++) 
        {
            LaserModel laserModel = laserModels.get(i);
            LaserView laserView = laserViews.get(i);
            LaserController laserController = laserControllers.get(i);

            laserController.moveLaser();
            laserController.updateView();

            if (laserModel.getY() < -LaserView.HEIGHT) 
            {
                gui.getGameScreen().remove(laserView);
                gui.getGameScreen().revalidate();
                gui.getGameScreen().repaint();

                laserModels.remove(i);
                laserViews.remove(i);
                laserControllers.remove(i);
                i--;
            }
        }
    }

    public void checkForCollisions() 
    {
        List<Integer> lasersToRemove = new ArrayList<>();
        for (int i = 0; i < laserControllers.size(); i++) 
        {
            LaserController laserController = laserControllers.get(i);
            boolean laserRemoved = false;

            for (AlienController alienController : alienArmada.getAliens()) 
            {
                if (alienController.isCollision(laserController) && laserController.isRed == true) 
                {
                    score.gainPoint();
                    scoreView.updateScore(score.getCurrentScore());
                    alienArmada.removeAlien(alienController);
                    alienController.removeAlien(gui);
                    lasersToRemove.add(i);
                    laserRemoved = true;
                    break;
                }
            }   
        
            if (laserRemoved) continue;

            for (int j = 0; j < barrierViews.size(); j++) 
            {
                BarrierView barrierView = barrierViews.get(j);
                BarrierModel barrierModel = barrierView.getBarrierModel();

                if (barrierView.getBounds().intersects(laserController.getLaserView().getBounds())) 
                {
                    lasersToRemove.add(i);
    
                    barrierView.getBarrierModel().hitByLaser();
                    barrierView.updateHitCountLabel(barrierView.getBarrierModel().getHitCount());
                    if (barrierModel.getHitCount() <= 0) 
                    {
                        gui.getGameScreen().remove(barrierView);
                        gui.getGameScreen().revalidate();
                        gui.getGameScreen().repaint();
                        barrierViews.remove(j);
                    }
                    
                    break;
                }
            }

            if (tankView.isCollision(laserController) && !laserController.isRed) 
            {
                lifeModel.hitByAlien(this);
                lifeView.loseLife(lifeModel.getLives());
                lasersToRemove.add(i);
                if (lifeModel.isGameOver())
                {
                    gameOver();
                }
            }
        }

        for (int i = lasersToRemove.size() - 1; i >= 0; i--)
        {
            int index = lasersToRemove.get(i);
            removeLaser(laserModels.get(index), laserViews.get(index));
            laserModels.remove(index);
            laserViews.remove(index);
            laserControllers.remove(index);
        }
        
    }

    private void addBarriers() 
    {
        int barrierWidth = 50;
        int barrierHeight = 25;
        int numberOfBarriers = 3;
        int barrierSpacing = 150;
        int barrierStartX = 100;
        int barrierY = 350;
    
        for (int i = 0; i < numberOfBarriers; i++) 
        {
            BarrierModel barrierModel = new BarrierModel(barrierStartX + (i * (barrierWidth + barrierSpacing)), barrierY, barrierWidth, barrierHeight);
            BarrierView barrierView = new BarrierView(barrierModel, Color.GREEN);
            barrierViews.add(barrierView);
            gui.addBarrierToGameScreen(barrierView);
        }
    }

    public void moveAliens() 
    {
        if (aliensRegenerating) return;

        boolean changeDirection = false;
        boolean moveDown = false;
        for (AlienController alienController : alienArmada.getAliens()) 
        {
            AlienModel alienModel = alienController.getAlienModel();
            AlienView alienView = alienController.getAlienView();
    
            if (!changeDirection && (alienModel.getX() <= 0 || alienModel.getX() + 40 >= gui.getGameScreen().getWidth())) 
            {
                changeDirection = true;
                moveDown = true;
            }
        }
    
        for (AlienController alienController : alienArmada.getAliens()) 
        {
            AlienModel alienModel = alienController.getAlienModel();
            AlienView alienView = alienController.getAlienView();
    
            if (changeDirection) 
            {
                alienModel.setX(alienModel.getX() - alienSpeed);
            } 
            else 
            {
                alienModel.setX(alienModel.getX() + alienSpeed);
            }
    
            if (moveDown) 
            {
                alienModel.setY(alienModel.getY() + 40);
            }
    
            alienView.setLocation(alienModel.getX(), alienModel.getY());
    
            if (alienModel.getY() >= tankView.getY() - 40) 
            {
                stopBackgroundMusic();
                lifeModel.playGameOverNoise();
                JOptionPane.showMessageDialog(null, "Game Over: Aliens have reached your tank! Score: " + score.getCurrentScore() + ", HighScore: " + score.getHighScore(), "Game Over", JOptionPane.DEFAULT_OPTION);
                gui.close();
            }
        }
    
        if (changeDirection) 
        {
            alienSpeed = -alienSpeed;
            directionChanged = !directionChanged;
        }
    }    
    
    public void startGameLoop() {
        int delay = 50;
    
        ActionListener gameLoop = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (alienArmada.getAliens().isEmpty() && !aliensRegenerating) {
                    regenerateAliens();
                }
                moveAliens();
                checkForCollisions();
                moveLasers();
    
                if (lifeModel.isGameOver()) {
                    stopBackgroundMusic();
                    int result = JOptionPane.showConfirmDialog(null,
                            "Score: " + score.getCurrentScore() + "   " + "HighScore: " + score.getHighScore() + "\nDo you want to play again?",
                            "Game Over", JOptionPane.YES_NO_OPTION);
    
                    if (result == JOptionPane.YES_OPTION) {
                        restartGame();
                    } else {
                        gui.close();
                    }
                }
            }
        };
    
        Timer timer = new Timer(delay, gameLoop);
        timer.start();
    }
       

    public void regenerateAliens() 
    {
        final int alienSpeedIncrement = 1;
        aliensRegenerating = true;
        if (directionChanged) 
        {
            alienSpeed = -alienSpeed;
            directionChanged = false;
        }
        alienSpeed += alienSpeedIncrement;
        alienArmada.resetAliens();
        AlienArmada newAlienArmada = new AlienArmada(this);
        for (AlienController alienController : newAlienArmada.getAliens()) 
        {
            AlienView alienView = alienController.getAlienView();
            AlienModel alienModel = alienController.getAlienModel();
            alienView.setBounds(alienModel.getX(), alienModel.getY(), 40, 40);
            if (this.difficulty.equals("Normal"))
            {
                this.lifeModel.setLives(3);
                this.lifeView.setLives(3);
            }
            gui.getGameScreen().add(alienView);
            gui.getGameScreen().revalidate();
            gui.getGameScreen().repaint();
            alienArmada.addAlien(alienController);
        }
        alienArmada = newAlienArmada;
        aliensRegenerating = false;
    }

    private void playBackgroundMusic() 
    {
        try 
        {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("arcade-music-loop.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(inputStream));
            DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
            backgroundMusicClip = (Clip) AudioSystem.getLine(info);
            backgroundMusicClip.open(audioInputStream);
            backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
    }
    
    public void stopBackgroundMusic() 
    {
        if (backgroundMusicClip != null) 
        {
            backgroundMusicClip.stop();
        }
    } 

    public void restartGame() 
    {
        stopBackgroundMusic();
        gui.close();
        lifeModel.setLives(3);
        lifeView.setLives(3);
        score.resetScore();
        scoreView.updateScore(score.getCurrentScore());
        alienArmada.resetAliens();
        TitleScreen titleScreen = new TitleScreen();
        titleScreen.drawMainMenu();
    }
    
    public void gameOver() 
    {
        stopBackgroundMusic();
    
        int result = JOptionPane.showConfirmDialog(null,
                "Score: " + score.getCurrentScore() + "   " + "HighScore: " + score.getHighScore() + "\nDo you want to play again?",
                "Game Over", JOptionPane.YES_NO_OPTION);
    
        if (result == JOptionPane.YES_OPTION) 
        {
            restartGame();
        } 
        else 
        {
            gui.close();
        }
    }
    
    public String getDifficulty()
    {
        return this.difficulty;
    }

    public SpaceGUI getSpaceGUI() 
    {
        return gui;
    }

    public ScoreModel getScoreModel()
    {
        return this.score;
    }

    public void setTankView(TankView tankView) 
    {
        this.tankView = tankView;
    }

    public void setAlienArmada(AlienArmada alienArmada) 
    {
        this.alienArmada = alienArmada;
    }
}
