package SIgame.controller;

import SIgame.model.*;
import SIgame.view.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.concurrent.locks.ReentrantLock;

public class AlienController 
{
    private AlienModel alienModel;
    private AlienView alienView;
    private LaserController laserController;
    private boolean isHit;
    private GameController gameController;
    private ArrayList<Integer> timeIntervals;
    private int interval;
    private boolean doesShoot;
    private ReentrantLock lock;
    private Thread alienThread;

    public AlienController(AlienModel alienModel, AlienView alienView, GameController gameController, boolean doesShoot) 
    {
        timeIntervals = new ArrayList();
        timeIntervals.add(10000);
        timeIntervals.add(15000);
        timeIntervals.add(20000);
        timeIntervals.add(25000);
        timeIntervals.add(30000);
        timeIntervals.add(40000);
        this.alienModel = alienModel;
        this.alienView = alienView;
        this.gameController = gameController;
        this.doesShoot = doesShoot;
        this.isHit = false;
        Random random = new Random();
        //interval = timeIntervals.get(random.nextInt(6)); 
        interval = 5000; // hard-coded for now

        this.lock = new ReentrantLock();
        if (doesShoot && !isHit) {
            Thread alienThread = new Thread(() -> {
                while (!isHit) {
                    lock.lock();
                    try {
                        shootLaser();
                    } finally {
                        lock.unlock();
                    }
                    try {
                        Thread.sleep(interval);
                    } catch (InterruptedException e) {
                        // handle interruption
                    }
                }
            });
            alienThread.start();
        }
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
}
