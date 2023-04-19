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
}
