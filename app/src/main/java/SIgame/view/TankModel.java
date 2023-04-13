package SIgame.view;

import javax.swing.*;
import java.awt.*;

public class TankModel extends JLabel
{
    ImageIcon tankIcon;
    Image scaledTank;
    JLabel tankLabel;

    public TankModel()
    {
        tankIcon = new ImageIcon(getClass().getClassLoader().getResource("tank.png"));
        scaledTank = tankIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        tankIcon = new ImageIcon(scaledTank);
        tankLabel = new JLabel(tankIcon);
        tankLabel.setBounds(290, 420, 60, 60);
    }
    
}
