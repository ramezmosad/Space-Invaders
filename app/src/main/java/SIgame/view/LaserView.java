package SIgame.view;

import SIgame.model.LaserModel;

import javax.swing.*;
import java.awt.*;

public class LaserView extends JPanel 
{
    public static final int WIDTH = 5;
    public static final int HEIGHT = 10;

    private LaserModel laserModel;

    public LaserView(LaserModel laserModel) 
    {
        this.laserModel = laserModel;
        setOpaque(false);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public LaserModel getLaserModel() 
    {
        return laserModel;
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }
}
