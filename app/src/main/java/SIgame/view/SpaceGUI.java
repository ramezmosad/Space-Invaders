package SIgame.view;

import javax.swing.*;
import SIgame.controller.*;
import SIgame.view.*;
import java.awt.*;
import SIgame.*;

public class SpaceGUI extends JPanel
{
    JFrame mainFrame;
    JPanel titlescreen;
    
    ControllerInterface controller;
    Score score;

    public SpaceGUI(ControllerInterface controller, Score score)
    {   
        this.controller = controller;
        this.score = score;

        mainFrame = new JFrame("Space Invaders");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawMainMenu();

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public void drawMainMenu()
    {
        titlescreen = new JPanel();
        titlescreen.setPreferredSize(new Dimension(640, 480));
        titlescreen.setBackground(Color.BLACK);
        mainFrame.add(titlescreen);
    }

}
