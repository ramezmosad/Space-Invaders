package SIgame.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SIgame.controller.GameController;
import SIgame.model.ScoreModel;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.*;

import java.io.InputStream;
import java.io.BufferedInputStream;

public class TitleScreen implements KeyListener
{
    private JFrame menuFrame;
    private JPanel titleScreen;
    private JLabel normalLabel;
    private JLabel hardLabel;
    private String difficulty;
    private int selectedButton = 0;

    public void drawMainMenu()
    {
        titleScreen = new JPanel();
        menuFrame = new JFrame("Space Invaders");
        ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.png"));
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel image = new JLabel(logo);
        
        titleScreen.setPreferredSize(new Dimension(640, 480));
        titleScreen.setBackground(Color.BLACK);
        titleScreen.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        titleScreen.add(image, gbc);

        gbc.gridy = 1;

        JPanel labelPanel = new JPanel();
        labelPanel.setBackground(Color.BLACK);
        labelPanel.setLayout(new BoxLayout(labelPanel, 3));

        JLabel label = new JLabel("Select Difficulty:");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Monospaced", Font.BOLD, 24));

        normalLabel = new JLabel("[ Normal ]");
        normalLabel.setForeground(Color.WHITE);
        normalLabel.setFont(new Font("Monospaced", Font.PLAIN, 20));

        hardLabel = new JLabel("Hard");
        hardLabel.setForeground(Color.WHITE);
        hardLabel.setFont(new Font("Monospaced", Font.PLAIN, 20));

        labelPanel.add(label);
        labelPanel.add(normalLabel);
        labelPanel.add(hardLabel);

        titleScreen.add(labelPanel, gbc);

        menuFrame.add(titleScreen);
        menuFrame.pack();
        menuFrame.setVisible(true);

        titleScreen.addKeyListener(this);
        titleScreen.setFocusable(true);
        titleScreen.requestFocusInWindow();
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            playSelectNoise();
            if (selectedButton == 1)
            {
                normalLabel.setText("[ Normal ]");
                hardLabel.setText("Hard");
                selectedButton = 0;
            }
        } 
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            playSelectNoise();
            if (selectedButton == 0)
            {
                normalLabel.setText("Normal");
                hardLabel.setText("[ Hard ]");
                selectedButton = 1;
            }
        } 
        else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (selectedButton == 0) 
            {
                difficulty = "Normal";
            } 
            else 
            {
                difficulty = "Hard";
            }
            menuFrame.dispose();
            ScoreModel scoreModel = new ScoreModel();
            GameController gameController = new GameController(scoreModel, difficulty);
        }
        
    }

    public String getDifficulty()
    {
        return difficulty;
    }

    public JFrame getMenuFrame()
    {
        return this.menuFrame;
    }

    public void playSelectNoise()
    {
        try 
        {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("selection.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(inputStream));
            DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioInputStream);
            clip.start();
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }
}
