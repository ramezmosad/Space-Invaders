package SIgame.controller;

import SIgame.model.LaserModel;
import SIgame.view.LaserView;
import javax.swing.*;
import java.awt.*;

public class LaserController 
{
    private LaserModel model;
    private LaserView view;
    public boolean red;

    public LaserController(LaserModel model, LaserView view) 
    {
        this.model = model;
        this.view = view;
        if (view.color == Color.RED)
        {
            red = true;
        }
        else
        {
            red = false;
        }
    }

    public LaserModel getLaserModel()
    {
        return this.model;
    }
    public LaserView getLaserView()
    {
        return this.view;
    }

    public void updateView() 
    {
        view.setBounds(model.getX(), model.getY(), LaserView.WIDTH, LaserView.HEIGHT);
        view.repaint();
    }

    public void moveLaser() 
    {
        model.move();
    }

    public void laserHitRemove()
    {
        view.remove(view);
    }
}
