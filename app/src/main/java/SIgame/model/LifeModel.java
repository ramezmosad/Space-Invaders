package SIgame.model;

public class LifeModel 
{
    private int lives;

    public LifeModel()
    {
        this.lives = 3;
    }

    public void hitByAlien()
    {
        lives--;
    }

    public int getLives()
    {
        return lives;
    }

    public boolean isGameOver()
    {
        boolean gameOver = false;
        if(this.lives == 0)
        {
            gameOver = true;
        }

        return gameOver;
    }
}
