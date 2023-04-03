package SIgame.view;

import javax.swing.*;
import SIgame.controller.*;
import SIgame.view.*;
import SIgame.model.*;

import java.awt.*;
import SIgame.*;

public class SpaceGUI
{
    JFrame menuFrame;
    JFrame gameFrame;
    JPanel titleScreen;
    JPanel gameScreen;
    JLabel image;
    ImageIcon icon;

    
    ControllerInterface controller;
    Score score;
    String difficulty = "Normal"; //hard coded for now

    public SpaceGUI(ControllerInterface controller, Score score)
    {   
        this.controller = controller;
        this.score = score;

        menuFrame = new JFrame("Space Invaders");
        titleScreen = new JPanel();

        gameScreen = new JPanel();

        drawMainMenu();
        drawGameScreen(difficulty);
    }

    public void drawMainMenu()
    {
        imageDrawing("/SIgame/resources/logo.png", titleScreen, 1);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleScreen.setPreferredSize(new Dimension(640, 480));
        titleScreen.setBackground(Color.BLACK);
        titleScreen.setLayout(new BoxLayout(titleScreen, BoxLayout.PAGE_AXIS));

        menuFrame.add(titleScreen);
        menuFrame.pack();
        menuFrame.setVisible(true);
    }

    public void drawGameScreen(String difficulty)
    {
        JLabel label = new JLabel("GAME");
        label.setOpaque(true);
        gameFrame = new JFrame(difficulty);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameScreen.setBackground(Color.BLACK);
        gameScreen.setPreferredSize(new Dimension(640, 480));
        gameScreen.setLayout(new BoxLayout(gameScreen, BoxLayout.PAGE_AXIS));

        imageDrawing("/SIgame/view/alien.png", gameScreen, 0.5);
        
        gameFrame.add(gameScreen);
        gameFrame.add(label);
        gameFrame.pack();
        gameFrame.setVisible(true);
    }

    //takes in the pathway of an image, a panel, and a scale.
    //It resizes the image by the scale
    //and adds the scaled image to the panel.
    public void imageDrawing(String pathway, JPanel targetPanel, double scale) 
    {
        java.net.URL imageURL = getClass().getResource(pathway);
        if (imageURL != null) 
        {
            icon = new ImageIcon(imageURL);
        } 
        else 
        {
            System.err.println("Resource not found: " + pathway);
            return;
        }
        
        image = new JLabel(icon);
        image.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        int height = icon.getIconHeight();
        int width = icon.getIconWidth();
        
        height = (int)(height * scale);
        width = (int)(width * scale);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        icon.setImage(img);
        //image.setBounds((targetPanel.getWidth() - width) / 2, (targetPanel.getHeight() - height) / 2, width, height);

        targetPanel.add(image);
    }
    
    


}
