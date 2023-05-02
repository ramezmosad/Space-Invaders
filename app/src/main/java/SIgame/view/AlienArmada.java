package SIgame.view;

import SIgame.controller.AlienController;
import SIgame.controller.GameController;
import SIgame.model.AlienModel;

import java.util.ArrayList;

public class AlienArmada 
{
    private ArrayList<AlienController> aliens;
    private GameController gameController;

    public AlienArmada(GameController gameController) 
    {
        this.gameController = gameController;
        this.aliens = new ArrayList<>();
        createArmada();
    }

    private void createArmada() 
    {
        int rows = 3;
        int columns = 9;
        int alienWidth = 20;
        int alienHeight = 20;
        int horizontalSpacing = 40;
        int verticalSpacing = 20;

        for (int i = 0; i < rows; i++) 
        {
            for (int j = 0; j < columns; j++) 
            {
                int x = 50 + (j * (alienWidth + horizontalSpacing));
                int y = 50 + (i * (alienHeight + verticalSpacing));
                AlienModel alienModel = new AlienModel(x, y);
                AlienView alienView = new AlienView(0);
                AlienController alienController = new AlienController(alienModel, alienView, gameController, true);
                aliens.add(alienController);
            }
        }
    }

    public ArrayList<AlienController> getAliens() 
    {
        return aliens;
    }

    public void resetAliens()
    {
        aliens.clear();
    }

    public void addAlien(AlienController alienController) 
    {
        aliens.add(alienController);
    }

    public void removeAlien(AlienController alienController) 
    {
        aliens.remove(alienController);
    }

    public void setAlienSpeed(int speed) 
    {
        for (AlienController alienController : aliens) 
        {
            alienController.setSpeed(speed);
        }
    }
}
