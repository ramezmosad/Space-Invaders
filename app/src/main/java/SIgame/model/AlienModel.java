package SIgame.model;

public class AlienModel 
{
    int pos_x;
    int pos_y;

    public AlienModel(int pos_x, int pos_y) 
    {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }
    

    public int getX()
    {
        return pos_x;
    }

    public void setX(int x)
    {   
        this.pos_x = x;
    }

    public int getY()
    {
        return pos_y;
    }

    public void setY(int y)
    {
        this.pos_y = y;
    }


    
}
