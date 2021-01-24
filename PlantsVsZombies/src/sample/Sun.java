package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Random;

public class Sun implements Serializable {
    transient private Pane gameinterfacepane;
    private double x,y;
    transient private ImageView sunView;

    public Sun(Pane gameinterfacepane, double x, double y) throws FileNotFoundException {
        this.gameinterfacepane = gameinterfacepane;
        Image sun = new Image(new FileInputStream("PvZ_PNGs/Plants/sunflower/sun.png"));
        this.sunView = new ImageView(sun);
        sunView.setX(x); sunView.setY(y);
        sunView.setFitHeight(80); sunView.setFitWidth(80);
        this.gameinterfacepane.getChildren().add(sunView);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public ImageView getSunView() {
        return sunView;
    }

    public void fall(){
        TranslateTransition moveSun = new TranslateTransition(Duration.seconds(5), this.sunView);
        Random random = new Random();
        int r = random.nextInt(500);
        moveSun.setByY(r);
        moveSun.setCycleCount(1);
        moveSun.play();
    }

}
