package SIgame.controller;

import SIgame.view.*;
import SIgame.model.*;
import SIgame.*;


public class GameController implements ControllerInterface{
    
    Score score;
    SpaceGUI gui;

    public GameController(Score score)
    {
        this.score = new Score();
        this.gui = new SpaceGUI(this, score);
    }

}
