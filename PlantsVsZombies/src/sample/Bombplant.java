package sample;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public abstract class Bombplant extends Plant{
    public Bombplant(double x, double y, ImageView plant, Pane gameinterfacepane, double health) {
        super(x, y, plant, gameinterfacepane, health);
    }

    public abstract void explode(ArrayList<ArrayList<Zombie>> zombies, Zombie zombie);

}
