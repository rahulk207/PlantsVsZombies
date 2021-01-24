package sample;

import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class PeaShooter extends Shooter {
    transient Timeline shootTimer;
    public PeaShooter(double x, double y, ImageView plant, double shootInterval, Pane gameinterfacePane, double health) {
        super(x, y, plant, shootInterval, gameinterfacePane, health);
    }

    @Override
    public void shoot(ArrayList<Pea> thisRowPeas, ArrayList<Zombie> thisRowZombies, ArrayList<Timeline> timelines) throws FileNotFoundException {
            Pea pea = new Pea(getGameinterfacepane(), this.getX(), this.getY());
            thisRowPeas.add(pea);
            pea.move(thisRowZombies, thisRowPeas, timelines);
    }


}

