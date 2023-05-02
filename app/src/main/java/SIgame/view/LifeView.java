package SIgame.view;

import javax.swing.JLabel;
import java.awt.*;

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

public class LifeView extends JLabel
{
    private JLabel livesLabel;

    public LifeView(int numOfLives)
    {
        this.setBounds(250, 10, 80, 30);
        setText("Lives: " + numOfLives);
        setForeground(Color.WHITE);
    }

    public void loseLife(int numOfLives)
    {
        setText("Lives: " + numOfLives);
        playLifeLostNoise();
        
    }

    public void setLives(int numOfLives)
    {
        setText("Lives: " + numOfLives);
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
