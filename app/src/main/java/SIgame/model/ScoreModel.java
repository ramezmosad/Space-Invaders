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
        currentScore = currentScore+10;
    }

    public int getCurrentScore()
    {
        return this.currentScore;
    }

    public int getHighScore()
    {
        return this.currentScore;
    }
}
