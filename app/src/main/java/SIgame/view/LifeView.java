package SIgame.view;

import javax.swing.JLabel;
import java.awt.*;

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
    }

    public void setLives(int numOfLives)
    {
        setText("Lives: " + numOfLives);
    }
}
