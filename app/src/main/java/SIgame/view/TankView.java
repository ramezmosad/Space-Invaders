package SIgame.view;

import javax.swing.*;
import java.awt.*;

import SIgame.controller.*;
import SIgame.model.*;

public class TankView extends JLabel
{
    ImageIcon tankIcon;
    Image scaledTank;
    JLabel tankLabel;

    public TankView()
    {
        tankIcon = new ImageIcon(getClass().getClassLoader().getResource("tank.png"));
        scaledTank = tankIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        tankIcon = new ImageIcon(scaledTank);
        tankLabel = new JLabel(tankIcon);
        
        //addKeyListener(new TankController(new TankModel(290, 420, 5), this));
        //setFocusable(true);
        tankLabel.setBounds(290, 420, 60, 60);
        add(tankLabel);
       
    }  

}
