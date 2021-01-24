package sample;

import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public abstract class Shooter extends Plant {
    private double shootInterval;

    public Shooter(double x, double y, ImageView plant, double shootInterval, Pane gameinterfacePane, double health) {
        super(x, y, plant, gameinterfacePane, health);
        this.shootInterval = shootInterval;
    }

    abstract public void shoot(ArrayList<Pea> thisRowPeas, ArrayList<Zombie> thisRowZombie, ArrayList<Timeline> timelines) throws FileNotFoundException;

    public double getShootInterval() {
        return shootInterval;
    }
}
