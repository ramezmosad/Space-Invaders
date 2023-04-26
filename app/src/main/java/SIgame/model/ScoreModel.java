package SIgame.model;

public class ScoreModel 
{
    int currentScore;

    public ScoreModel()
    {
        this.currentScore = 0;
    }

    public void gainPoint()
    {
        currentScore++;
    }

    public int getCurrentScore()
    {
        return this.currentScore;
    }
}
