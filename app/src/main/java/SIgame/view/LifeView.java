package SIgame.view;

import javax.swing.JLabel;
import java.awt.*;

public class LifeView extends JLabel
{
    private JLabel livesLabel;

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
        this.setText("Lives: " + numOfLives);
    }
}
