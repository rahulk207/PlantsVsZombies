package sample;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;

public abstract class SunProducer extends Plant {

    private int sunProducingInterval;
    public SunProducer(double x, double y, ImageView plant, int sunProducingInterval, Pane gameinterfacePane, double health) {
        super(x, y, plant, gameinterfacePane, health);
        this.sunProducingInterval = sunProducingInterval;
    }

    public int getSunProducingInterval() {
        return sunProducingInterval;
    }

    public abstract void produceSun(double x, double y, Text sunCount) throws FileNotFoundException;
}
