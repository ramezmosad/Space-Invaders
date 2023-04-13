package SIgame.view;

import javax.swing.JLabel;
import java.awt.*;

public class Lives extends JLabel
{
    JLabel livesLabel;
    public Lives(int numOfLives)
    {
        livesLabel = new JLabel("Lives: " + numOfLives);
        livesLabel.setForeground(Color.WHITE);
        livesLabel.setBounds(250, 10, 80, 30);
    }

    public void loseLife(int numOfLives)
    {
        this.livesLabel.setText("Lives: " + numOfLives);
    }
}
