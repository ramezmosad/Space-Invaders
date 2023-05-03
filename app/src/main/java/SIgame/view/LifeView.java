package SIgame.view;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

public class LifeView extends JLabel
{
    public LifeView(int numOfLives) 
    {
        int screenWidth = 640;
        int labelWidth = 80;
        int labelX = (screenWidth / 2) + (labelWidth / 2);
        this.setBounds(labelX + 60, 10, labelWidth, 30);
        setText("Lives: " + numOfLives);
        setFont(new Font("Monospaced", Font.PLAIN, 15));
        setForeground(Color.WHITE);
    }

    public void loseLife(int numOfLives)
    {
        setText("Lives: " + numOfLives);
        setFont(new Font("Monospaced", Font.PLAIN, 15));
    }

    public void setLives(int numOfLives)
    {
        setText("Lives: " + numOfLives);
        setFont(new Font("Monospaced", Font.PLAIN, 15));
    }

}
