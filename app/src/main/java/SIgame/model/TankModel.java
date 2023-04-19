package SIgame.model;

public class TankModel 
{
    private int x;
    private int y;
    private int speed;

    public TankModel(int x, int y, int speed) 
    {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public LaserModel shoot() 
    {
        return new LaserModel(x + 27, y - 10, 5);
    }
    

    public int getX() 
    {
        return x;
    }

    public int getY() 
    {
        return y;
    }

    public int getSpeed() 
    {
        return speed;
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    public void moveLeft() 
    {
        x -= speed;
    }

    public void moveRight() 
    {
        x += speed;
    }
}
