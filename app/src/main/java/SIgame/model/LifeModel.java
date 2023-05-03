package SIgame.model;


import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

import SIgame.controller.GameController;

import javax.sound.sampled.*;
import java.io.InputStream;
import java.io.BufferedInputStream;

public class LifeModel 
{
    private int lives;

    public LifeModel()
    {
        this.lives = 3;
    }

    public void hitByAlien(GameController gameController) {
        if (lives > 1) 
        {
            playLifeLostNoise();
        }

        if (lives > 0) 
        {
            lives--;
        }
    
        if (isGameOver()) 
        {
            playGameOverNoise();
            JOptionPane.showMessageDialog(null,
                    "Score: " + gameController.getScoreModel().getCurrentScore() + "   " + "HighScore: " + gameController.getScoreModel().getHighScore(),
                    "Game Over", JOptionPane.DEFAULT_OPTION);
            gameController.gameOver();
        }
    }    

    public void setLives(int lives) 
    {
        this.lives = lives;
    }

    public int getLives()
    {
        return lives;
    }

    public boolean isGameOver()
    {
        return this.lives == 0;
    }

    public void playGameOverNoise()
    {
        try 
        {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("gameover.wav");
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
    }

    public void playLifeLostNoise()
    {
        try 
        {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("lifelost.wav");
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
    }
}
