package SIgame.model;

public class BarrierModel 
{
    private int x;
    private int y;
    private int width;
    private int height;
    private int hitCount = 10;

    public BarrierModel(int x, int y, int width, int height) 
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean hitByLaser() 
    {
        if (hitCount > 0) 
        {
            hitCount--;
        }
        return hitCount <= 0;
    }

    public int getHitCount()
    {
        return hitCount;
    }

    public int getX() 
    {
        return x;
    }

    public int getY() 
    {
        return y;
    }

    public int getWidth() 
    {
        return width;
    }

    public int getHeight() 
    {
        return height;
    }
}
