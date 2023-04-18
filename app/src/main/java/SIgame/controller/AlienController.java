package SIgame.controller;

import SIgame.model.*;
import SIgame.view.*;

public class AlienController 
{
    private AlienModel alienModel;
    private AlienView alienView;

    public AlienController(AlienModel alienModel, AlienView alienView) {
        this.alienModel = alienModel;
        this.alienView = alienView;
    }

    public boolean isCollision(LaserController laserController) {
        final int MARGIN = 1;
    
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
    
        return xOverlap && yOverlap;
    }
    
    

    public void removeAlien(SpaceGUI spaceGUI) {
        spaceGUI.getGameScreen().remove(alienView);
        spaceGUI.getGameScreen().revalidate();
        spaceGUI.getGameScreen().repaint();
    }
}
