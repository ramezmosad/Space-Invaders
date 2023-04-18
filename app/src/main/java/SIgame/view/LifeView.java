package SIgame.view;

import javax.swing.JLabel;

import SIgame.model.LifeTracker;

import java.awt.*;
import javax.swing.*;

public class LifeView extends JLabel
{
    JLabel livesLabel;

    public LifeView(int numOfLives)
    {
        livesLabel = new JLabel("Lives: " + numOfLives);
        livesLabel.setForeground(Color.WHITE);
        this.setBounds(250, 10, 80, 30);
        setText("Lives: " + numOfLives);
        setForeground(Color.WHITE);
    }

    public void loseLife(int numOfLives)
    {
        this.livesLabel.setText("Lives: " + numOfLives);
    }
}
