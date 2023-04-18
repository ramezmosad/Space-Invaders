package SIgame.controller;

import SIgame.view.*;
import SIgame.model.*;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    
    private Score score;
    private TankView tankView;
    private SpaceGUI gui;
    private List<LaserModel> laserModels;
    private List<LaserView> laserViews;
    private List<LaserController> laserControllers;

    public GameController(Score score) 
    {
        this.score = score;
        this.tankView = new TankView();
        this.gui = new SpaceGUI(this, score, this.tankView);
        this.laserModels = new ArrayList<>();
        this.laserViews = new ArrayList<>();
        this.laserControllers = new ArrayList<>();
    }

    public void addLaser(LaserModel laserModel, LaserView laserView) {
        laserModels.add(laserModel);
        laserViews.add(laserView);
        LaserController laserController = new LaserController(laserModel, laserView);
        laserControllers.add(laserController);
        gui.addLaserToGameScreen(laserView);
    }

    public void moveLasers() {
        for (int i = 0; i < laserModels.size(); i++) {
            LaserModel laserModel = laserModels.get(i);
            LaserView laserView = laserViews.get(i);
            LaserController laserController = laserControllers.get(i);

            laserController.moveLaser();
            laserController.updateView();

            if (laserModel.getY() < -LaserView.HEIGHT) {
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


    public SpaceGUI getSpaceGUI() {
        return gui;
    }

    public void setTankView(TankView tankView) {
        this.tankView = tankView;
    }
}
