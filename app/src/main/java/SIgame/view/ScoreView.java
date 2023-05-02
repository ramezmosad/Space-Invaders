package SIgame.view;

import javax.swing.JLabel;
import java.awt.*;
import SIgame.model.*;

public class ScoreView extends JLabel
{
    private ScoreModel score;

    public ScoreView(ScoreModel score)
    {
        this.score = score;
        this.setBounds(20, 10, 200, 30);
        setText("Score: " + score.getCurrentScore() +"  " + "High Score: " + score.getHighScore());
        setForeground(Color.WHITE);
    }

    public void updateScore(int score)
    {
        setText("Score: " + score +"  " + "High Score: " + this.score.getHighScore());
    }
}

    

