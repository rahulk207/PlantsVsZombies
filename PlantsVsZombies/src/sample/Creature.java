package sample;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.Serializable;

public class Creature implements Serializable {

    transient Pane gameinterfacepane;
    transient ImageView fakeImage;
    double x,y;
    public double health;
    public Creature(double x, double y, ImageView fake, Pane gameinterfacepane, double health){
            this.gameinterfacepane = gameinterfacepane;
            this.x = x; this.y = y;
            this.health = health;
            this.fakeImage = fake;
            this.fakeImage.setX(this.x); this.fakeImage.setY(this.y);
            this.fakeImage.setFitWidth(106.6); this.fakeImage.setFitHeight(98);
            this.fakeImage.setOpacity(0);

            this.gameinterfacepane.getChildren().add(this.fakeImage);
    }

    public ImageView getImage() {
        return fakeImage;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Pane getGameinterfacepane() {
        return gameinterfacepane;
    }

}
