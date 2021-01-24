package sample;

import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Repeater extends Shooter {
    public Repeater(double x, double y, ImageView plant, int shootInterval, Pane gameinterfacePane, double health) {
        super(x, y, plant, shootInterval, gameinterfacePane, health);
    }

    @Override
    public void shoot(ArrayList<Pea> thisRowPeas, ArrayList<Zombie> thisRowZombies, ArrayList<Timeline> timelines) throws FileNotFoundException {

    }
}
