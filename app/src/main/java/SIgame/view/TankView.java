package SIgame.view;

import javax.swing.*;
import java.awt.*;

import SIgame.controller.*;
import SIgame.model.*;

public class TankView extends JLabel 
{
    ImageIcon tankIcon;
    Image scaledTank;

    public TankView() 
    {
        tankIcon = new ImageIcon(getClass().getClassLoader().getResource("tank.png"));
        scaledTank = tankIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        tankIcon = new ImageIcon(scaledTank);
        setIcon(tankIcon);
        this.setBounds(290, 420, 60, 60);
    }

    public boolean isCollision(LaserController laserController) 
    {
        final int MARGIN = 0;
        
        LaserModel laserModel = laserController.getLaserModel();
        int laserX = laserModel.getX();
        int laserY = laserModel.getY()+10;
        int laserWidth = LaserView.WIDTH;
        int laserHeight = LaserView.HEIGHT;
    
        int tankX = getX() + 15;
        int tankY = getY() + 20;
        int tankWidth = 33;
        int tankHeight = 70;
    
        boolean xOverlap = (laserX + laserWidth + MARGIN >= tankX) && (laserX - MARGIN <= tankX + tankWidth);
        boolean yOverlap = (laserY + laserHeight + MARGIN >= tankY) && (laserY - MARGIN <= tankY + tankHeight);
    
        return xOverlap && yOverlap;
    }


}
