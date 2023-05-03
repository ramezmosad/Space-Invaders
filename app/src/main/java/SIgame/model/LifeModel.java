package SIgame.model;


import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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

    public void hitByAlien()
    {
        if (lives > 1)
        {
            playLifeLostNoise();
        }
        if (lives > 0) 
        {
            lives--;
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
        boolean gameOver = false;
        if(this.lives == 0)
        {
            gameOver = true;
            playGameOverNoise();
        }
        return gameOver;
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