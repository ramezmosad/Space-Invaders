package SIgame.model;

public class LaserModel {
    private int x;
    private int y;
    private int speed;

    public LaserModel(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public void move() {
        y -= speed;
    }
}
