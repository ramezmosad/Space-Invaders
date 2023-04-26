package SIgame.view;

import SIgame.model.LaserModel;

import javax.swing.*;
import java.awt.*;

public class LaserView extends JPanel 
{
    public static final int WIDTH = 5;
    public static final int HEIGHT = 10;

    private LaserModel laserModel;
    private Color color;

    public LaserView(LaserModel laserModel, Color color) 
    {
        this.laserModel = laserModel;
        this.color = color;
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
        g.setColor(color);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }
}
