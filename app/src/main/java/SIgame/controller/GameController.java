package SIgame.controller;

import SIgame.view.*;
import SIgame.model.*;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.color.*;

public class GameController 
{
    private LifeModel lifeModel;
    private LifeView lifeView;
    private ScoreModel score;
    private TankView tankView;
    private SpaceGUI gui;
    private List<LaserModel> laserModels;
    private List<LaserView> laserViews;
    private List<LaserController> laserControllers;
    private List<BarrierView> barrierViews;
    private AlienArmada alienArmada;
    private ScoreView scoreView;

    public GameController(ScoreModel score) 
    {
        this.lifeView = new LifeView(3);
        this.lifeModel = new LifeModel();
        this.score = score;
        this.tankView = new TankView();
        this.alienArmada = new AlienArmada(this);
        this.laserModels = new ArrayList<>();
        this.laserViews = new ArrayList<>();
        this.laserControllers = new ArrayList<>();
        this.scoreView = new ScoreView(score);
        this.barrierViews = new ArrayList<>();
        this.gui = new SpaceGUI(this, score, this.tankView, lifeView, alienArmada, scoreView); // Move this line before addBarriers()
        addBarriers();
    }
    

    public void addLaser(LaserModel laserModel, LaserView laserView) 
    {
        laserModels.add(laserModel);
        laserViews.add(laserView);
        LaserController laserController = new LaserController(laserModel, laserView);
        laserControllers.add(laserController);
        gui.addLaserToGameScreen(laserView);
    }

    public void removeLaser(LaserModel laserModel, LaserView laserView) 
    {
        gui.getGameScreen().remove(laserView);
        gui.getGameScreen().revalidate();
        gui.getGameScreen().repaint();
    }    

    public void moveLasers() 
    {
        for (int i = 0; i < laserModels.size(); i++) 
        {
            LaserModel laserModel = laserModels.get(i);
            LaserView laserView = laserViews.get(i);
            LaserController laserController = laserControllers.get(i);

            laserController.moveLaser();
            laserController.updateView();

            if (laserModel.getY() < -LaserView.HEIGHT) 
            {
                gui.getGameScreen().remove(laserView);
                gui.getGameScreen().revalidate();
                gui.getGameScreen().repaint();

                laserModels.remove(i);
                laserViews.remove(i);
                laserControllers.remove(i);
                i--;
            }
        }
    }

    public void checkForCollisions() 
    {
        List<Integer> lasersToRemove = new ArrayList<>();
        for (int i = 0; i < laserControllers.size(); i++) 
        {
            LaserController laserController = laserControllers.get(i);
            boolean laserRemoved = false;

            for (AlienController alienController : alienArmada.getAliens()) 
            {
                if (alienController.isCollision(laserController) && laserController.isRed == true) 
                {
                    score.gainPoint();
                    scoreView.updateScore(score.getCurrentScore());
                    alienController.removeAlien(gui);
                    System.out.println("Alien collided with laser");
                    lasersToRemove.add(i);
                    laserRemoved = true;
                    break;
                }
            }   
        
            if (laserRemoved) continue;

            for (int j = 0; j < barrierViews.size(); j++) 
            {
                BarrierView barrierView = barrierViews.get(j);
                BarrierModel barrierModel = barrierView.getBarrierModel();

                if (barrierView.getBounds().intersects(laserController.getLaserView().getBounds())) 
                {
                    lasersToRemove.add(i);
                    if (barrierModel.hitByLaser()) 
                    {
                        barrierView.updateHitCountLabel(barrierModel.getHitCount());
                        if (barrierModel.getHitCount() <= 0) 
                        {
                        gui.getGameScreen().remove(barrierView);
                        gui.getGameScreen().revalidate();
                        gui.getGameScreen().repaint();
                        barrierViews.remove(j);
                        }
                    }
                    break;
                }
            }

            if (tankView.isCollision(laserController)) 
            {
                lifeModel.hitByAlien();
                lifeView.loseLife(lifeModel.getLives());
                System.out.println("Tank collided with laser");
                lasersToRemove.add(i);
            }
        }

        for (int i = lasersToRemove.size() - 1; i >= 0; i--)
        {
            int index = lasersToRemove.get(i);
            removeLaser(laserModels.get(index), laserViews.get(index));
            laserModels.remove(index);
            laserViews.remove(index);
            laserControllers.remove(index);
        }
    }
    

    private void addBarriers() 
    {
        int barrierWidth = 50;
        int barrierHeight = 25;
        int numberOfBarriers = 3;
        int barrierSpacing = 150;
        int barrierStartX = 100;
        int barrierY = 350;
    
        for (int i = 0; i < numberOfBarriers; i++) 
        {
            BarrierModel barrierModel = new BarrierModel(barrierStartX + (i * (barrierWidth + barrierSpacing)), barrierY, barrierWidth, barrierHeight);
            BarrierView barrierView = new BarrierView(barrierModel, Color.GREEN);
            barrierViews.add(barrierView);
            gui.addBarrierToGameScreen(barrierView);
        }
    }

    public SpaceGUI getSpaceGUI() 
    {
        return gui;
    }

    public void setTankView(TankView tankView) 
    {
        this.tankView = tankView;
    }
}
