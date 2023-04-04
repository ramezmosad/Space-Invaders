package SIgame.view;

import javax.swing.*;
import SIgame.controller.*;
import SIgame.view.*;
import SIgame.model.*;

import java.awt.*;
import SIgame.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import javax.imageio.ImageIO;

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

    public void drawMainMenu() {
        ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.png"));
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel image = new JLabel(logo);
        titleScreen.setPreferredSize(new Dimension(640, 480));
        titleScreen.setBackground(Color.BLACK);
        titleScreen.setLayout(new BoxLayout(titleScreen, BoxLayout.PAGE_AXIS));
        JPanel labelPanel = new JPanel();
        labelPanel.setBackground(Color.BLACK);
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.PAGE_AXIS));
        JLabel label = new JLabel("Select Difficulty:");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        JLabel normalLabel = new JLabel("[ Normal ]");
        normalLabel.setForeground(Color.WHITE);
        normalLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        JLabel hardLabel = new JLabel("Hard");
        hardLabel.setForeground(Color.WHITE);
        hardLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        labelPanel.add(Box.createVerticalGlue());
        labelPanel.add(label);
        labelPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        labelPanel.add(normalLabel);
        labelPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        labelPanel.add(hardLabel);
        labelPanel.add(Box.createVerticalGlue());
        titleScreen.add(image);
        titleScreen.add(labelPanel);
        menuFrame.add(titleScreen);
        menuFrame.pack();
        menuFrame.setVisible(true);
    }

    

    public void drawGameScreen(String difficulty) {
        int alienStartX = 50;
        gameFrame = new JFrame(difficulty);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        gameScreen.setBackground(Color.BLACK);
        gameScreen.setPreferredSize(new Dimension(640, 480));
        gameScreen.setLayout(null);

        JLabel scoreLabel = new JLabel("Score: 5");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(20, 10, 80, 30);
        gameScreen.add(scoreLabel);
        JLabel highScoreLabel = new JLabel("High Score: 35");
        highScoreLabel.setForeground(Color.WHITE);
        highScoreLabel.setBounds(120, 10, 100, 30);
        gameScreen.add(highScoreLabel);
        JLabel livesLabel = new JLabel("Lives: 2");
        livesLabel.setForeground(Color.WHITE);
        livesLabel.setBounds(250, 10, 80, 30);
        gameScreen.add(livesLabel);
        ImageIcon tankIcon = new ImageIcon(getClass().getClassLoader().getResource("tank.png"));
        Image scaledTank = tankIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        tankIcon = new ImageIcon(scaledTank);
        JLabel tankLabel = new JLabel(tankIcon);
        tankLabel.setBounds(290, 420, 60, 60);
        gameScreen.add(tankLabel);
             
        for (int i = 0; i < 6; i++) 
        {
            ImageIcon alienIcon = new ImageIcon(getClass().getClassLoader().getResource("alien.png"));
            Image scaledAlien = alienIcon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
            alienIcon = new ImageIcon(scaledAlien);
            JLabel alienLabel = new JLabel(alienIcon);
            alienLabel.setBounds(alienStartX + (i * 50), 50, 40, 40);
            gameScreen.add(alienLabel);
        }
    
        gameFrame.add(gameScreen);
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
    
    public void resize(ImageIcon image){


    }

    
    


}
