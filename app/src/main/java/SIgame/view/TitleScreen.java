package SIgame.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


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
        titleScreen.setLayout(new BoxLayout(titleScreen, BoxLayout.PAGE_AXIS));
        JPanel labelPanel = new JPanel();
        labelPanel.setBackground(Color.BLACK);
        labelPanel.setLayout(new BoxLayout(labelPanel, 3));
        JLabel label = new JLabel("Select Difficulty:");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        normalLabel = new JLabel("[ Normal ]");
        normalLabel.setForeground(Color.WHITE);
        normalLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        hardLabel = new JLabel("Hard");
        hardLabel.setForeground(Color.WHITE);
        hardLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        labelPanel.add(label);
        labelPanel.add(normalLabel);
        labelPanel.add(hardLabel);
        titleScreen.add(image);
        titleScreen.add(labelPanel);
        menuFrame.add(titleScreen);
        menuFrame.pack();
        menuFrame.setVisible(true);
        titleScreen.addKeyListener(this);
        titleScreen.setFocusable(true);
        titleScreen.requestFocusInWindow();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            if (selectedButton == 1)
            {
                normalLabel.setText("[ Normal ]");
                hardLabel.setText("Hard");
                selectedButton = 0;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            if (selectedButton == 0)
            {
                normalLabel.setText("Normal");
                hardLabel.setText("[ Hard ]");
                selectedButton = 1;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            if (selectedButton == 0)
            {
                difficulty = "Normal";
            } else
            {
                difficulty = "Hard";
            }
            menuFrame.dispose();
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


    public String getDifficulty()
    {
        return difficulty;
    }

    public JFrame getMenuFrame()
    {
        return this.menuFrame;
    }


}
