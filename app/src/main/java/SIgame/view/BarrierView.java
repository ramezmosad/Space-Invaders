package SIgame.view;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SIgame.model.*;

public class BarrierView extends JPanel 
{
    private BarrierModel barrierModel;
    private Color color;
    private JLabel hitCountLabel;

    public BarrierView(BarrierModel barrierModel, Color color) 
    {
        this.barrierModel = barrierModel;
        this.color = color;
        setBounds(barrierModel.getX(), barrierModel.getY(), barrierModel.getWidth(), barrierModel.getHeight());
        setLayout(null);

        hitCountLabel = new JLabel(Integer.toString(barrierModel.getHitCount()));
        hitCountLabel.setForeground(Color.WHITE);
        hitCountLabel.setBounds(barrierModel.getWidth() / 2 - 10, barrierModel.getHeight() / 2 - 10, 20, 20);
        add(hitCountLabel);
    }

    public BarrierModel getBarrierModel() 
    {
        return barrierModel;
    }

    public void updateHitCountLabel(int hitCount) 
    {
        hitCountLabel.setText("" + hitCount);
        System.out.println("update hit called");
        this.revalidate();
        this.repaint();
    }    

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(0, 0, barrierModel.getWidth(), barrierModel.getHeight());
    }
}
