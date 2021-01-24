package sample;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Barrier extends Plant {
    public Barrier(double x, double y, ImageView plant, Pane gameinterfacepane, double health) {
        super(x, y, plant, gameinterfacepane, health);
    }
}
