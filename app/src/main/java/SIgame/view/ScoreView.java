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
        int screenWidth = 640;
        int labelWidth = 300;
        int labelX = (screenWidth / 2) - (labelWidth / 2);
        this.setBounds(labelX - 40, 10, labelWidth, 30);
        setText("Score: " + score.getCurrentScore() + "  " + "  High Score: " + score.getHighScore());
        setFont(new Font("Monospaced", Font.PLAIN, 15));
        setForeground(Color.WHITE);
    }

    public void updateScore(int score)
    {
        setText("Score: " + score +"  " + "  High Score: " + this.score.getHighScore());
        setFont(new Font("Monospaced", Font.PLAIN, 15));
    }
}

    

