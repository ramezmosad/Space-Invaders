package SIgame.controller;

import SIgame.view.*;
import SIgame.model.*;

import java.util.ArrayList;
import java.util.List;

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
        this.gui = new SpaceGUI(this, score, this.tankView, lifeView, alienArmada, scoreView);
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
        for (int i = 0; i < laserControllers.size(); i++) 
        {
            LaserController laserController = laserControllers.get(i);
            for (AlienController alienController : alienArmada.getAliens()) {
                if (alienController.isCollision(laserController)) {
                    score.gainPoint();
                    scoreView.updateScore(score.getCurrentScore());
                    alienController.removeAlien(gui);
                    System.out.println("Alien collided with laser");
                }
            }
    
            if (tankView.isCollision(laserController)) 
            {
                lifeModel.hitByAlien();
                lifeView.loseLife(lifeModel.getLives());
                System.out.println("Tank collided with laser");
    
                removeLaser(laserModels.get(i), laserViews.get(i));
                laserModels.remove(i);
                laserViews.remove(i);
                laserControllers.remove(i);
                i--;
            }
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
