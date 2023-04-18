package SIgame.model;

import SIgame.view.LifeView;

public class LifeTracker 
{
    private int lives;
    private LifeView lifeView;

    public LifeTracker(LifeView lifeView)
    {
        this.lifeView = lifeView;
        this.lives = 3;
    }

    public void hitByAlien()
    {
        lives--;
        lifeView.loseLife(lives);
    }

    public int getLives()
    {
        return lives;
    }

}
