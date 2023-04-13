package SIgame.view;

import javax.swing.*;
import java.awt.*;

public class AlienModel extends JLabel
{
    private ImageIcon alienIcon;
    private Image scaledAlien;
    private JLabel alienLabel;
    private int alienID;

    public AlienModel(int alienID)
    {
        this.alienID = alienID;
        alienIcon = new ImageIcon(getClass().getClassLoader().getResource("alien.png"));
        scaledAlien = alienIcon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        alienIcon = new ImageIcon(scaledAlien);
        alienLabel = new JLabel(alienIcon);
    }

    public int getAlienID()
    {
        return alienID;
    }


}
