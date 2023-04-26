package SIgame.controller;

import SIgame.model.*;
import SIgame.view.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.Timer;

public class AlienController 
{
    private AlienModel alienModel;
    private AlienView alienView;
    private LaserController laserController;
    private boolean isHit;
    private GameController gameController;
    private Timer shootingTimer;
    private ArrayList<Integer> timeIntervals;

    public AlienController(AlienModel alienModel, AlienView alienView, GameController gameController, boolean doesShoot) 
    {
        this.alienModel = alienModel;
        this.alienView = alienView;
        this.gameController = gameController;
        this.isHit = false;

        timeIntervals = new ArrayList<>(Arrays.asList(5000, 10000, 15000, 20000));
        Random random = new Random();
        int randomTimeInterval = timeIntervals.get(random.nextInt(timeIntervals.size()));

        if (doesShoot && !isHit) 
        {
            shootingTimer = new Timer(randomTimeInterval, new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    if (!isHit) 
                    {
                        shootLaser();
                    }
                }
            });
            shootingTimer.start();
        }
    }

    public boolean isCollision(LaserController laserController) 
    {
        final int MARGIN = 1;
        if(!isHit)
        {
            LaserModel laserModel = laserController.getLaserModel();
            int laserX = laserModel.getX();
            int laserY = laserModel.getY();
            int laserWidth = LaserView.WIDTH;
            int laserHeight = LaserView.HEIGHT;

            int alienX = alienModel.getX();
            int alienY = alienModel.getY();
            int alienWidth = 40;
            int alienHeight = 40;

            boolean xOverlap = (laserX + laserWidth + MARGIN >= alienX) && (laserX - MARGIN <= alienX + alienWidth);
            boolean yOverlap = (laserY + laserHeight + MARGIN >= alienY) && (laserY - MARGIN <= alienY + alienHeight);
            this.laserController = laserController;
            return xOverlap && yOverlap;
        }
        return false;
    }

    private void shootLaser() 
    {
        LaserModel laserModel = new LaserModel(alienModel.getX() + 20, alienModel.getY() + 40, -3);
        LaserView laserView = new LaserView(laserModel);
        gameController.addLaser(laserModel, laserView);
    }

    public void removeAlien(SpaceGUI spaceGUI) 
    {
        spaceGUI.getGameScreen().remove(alienView);
        this.isHit = true;
        spaceGUI.getGameScreen().remove(this.laserController.getLaserView());
        spaceGUI.getGameScreen().revalidate();
        spaceGUI.getGameScreen().repaint();
    }

    public AlienView getAlienView()
    {
        return this.alienView;
    }

    public AlienModel getAlienModel() 
    {
        return this.alienModel;
    }    
}
