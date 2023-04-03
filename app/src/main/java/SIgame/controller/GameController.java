package SIgame.controller;

import SIgame.view.*;
import SIgame.*;

public class GameController implements ControllerInterface{
    
    Score score;
    SpaceGUI gui;

    public GameController(Score score)
    {
        this.score = score;
        this.gui = new SpaceGUI(this,score);
    }

}
