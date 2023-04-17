package SIgame.controller;

import SIgame.view.*;
import SIgame.model.*;
import SIgame.controller.*;
import SIgame.*;


public class GameController implements GameControllerInterface{
    
    Score score;
    TankView tankView;
    SpaceGUI gui;

    public GameController(Score score,TankView tankView)
    {
        // this.score = new Score();
        this.tankView = tankView;
        this.gui = new SpaceGUI(this, score, this.tankView);
    }

}
