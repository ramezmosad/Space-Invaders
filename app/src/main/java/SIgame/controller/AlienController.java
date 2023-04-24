package SIgame.controller;

import SIgame.model.*;
import SIgame.view.*;

public class AlienController 
{
    private AlienModel alienModel;
    private AlienView alienView;
    private LaserController laserController;
    private boolean isHit;

    public AlienController(AlienModel alienModel, AlienView alienView) 
    {
        this.alienModel = alienModel;
        this.alienView = alienView;
        this.isHit = false;
    }

    public boolean isCollision(LaserController laserController) 
    {
        final int MARGIN = 1;

        if(!isHit){
    
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
    
    

    public void removeAlien(SpaceGUI spaceGUI) 
    {
        spaceGUI.getGameScreen().remove(alienView);
        this.isHit = true;
        spaceGUI.getGameScreen().remove(this.laserController.getLaserView());
        spaceGUI.getGameScreen().revalidate();
        spaceGUI.getGameScreen().repaint();
    }
}
