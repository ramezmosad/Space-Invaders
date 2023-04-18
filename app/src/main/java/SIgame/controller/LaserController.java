package SIgame.controller;

import SIgame.model.LaserModel;
import SIgame.view.LaserView;

public class LaserController {
    private LaserModel model;
    private LaserView view;

    public LaserController(LaserModel model, LaserView view) {
        this.model = model;
        this.view = view;
    }

    public void updateView() {
        view.setBounds(model.getX(), model.getY(), LaserView.WIDTH, LaserView.HEIGHT);
        view.repaint();
    }

    public void moveLaser() {
        model.move();
    }
}
