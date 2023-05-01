package SIgame.view;

import javax.swing.JLabel;
import java.awt.*;
import SIgame.model.*;

public class ScoreView extends JLabel
{
    private JLabel scoreLabel;
    private ScoreModel score;

    public ScoreView(ScoreModel score)
    {
        this.score = score;
        // scoreLabel = new JLabel("Lives: " + score.getCurrentScore());
        // scoreLabel.setForeground(Color.WHITE);
        this.setBounds(20, 10, 160, 30);
        setText("Score: " + score.getCurrentScore() +"  " + "High Score: " + score.getHighScore());
        setForeground(Color.WHITE);
    }

    public void updateScore(int score)
    {
        setText("Score: " + score +"  " + "High Score: " + this.score.getHighScore());
    }
}

    

