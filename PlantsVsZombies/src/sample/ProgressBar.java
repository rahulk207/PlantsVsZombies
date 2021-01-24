package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

public class ProgressBar implements Serializable {
    transient ImageView emptyprogressbar;
    transient ImageView finalwaveflagimageview;
    transient ImageView zombieheadimageview;
    transient Pane gameinterfacepane;
    public double emptyX=620, emptyY=5, flagX=630, flagY=7, headX=840, headY=7;

    public ProgressBar(Pane gameinterfacepane) throws FileNotFoundException {
        this.gameinterfacepane = gameinterfacepane;
        Image barimage = new Image(new FileInputStream("PvZ_PNGs/Progress_Bar/emptyprogressbar.png"));
        this.emptyprogressbar = new ImageView(barimage);
        emptyprogressbar.setX(620); emptyprogressbar.setY(5);
        emptyprogressbar.setFitWidth(260); emptyprogressbar.setFitHeight(35);

        Image finalwaveflagimage=new Image(new FileInputStream("PvZ_PNGs/Progress_Bar/finalwaveflag.png"));
        this.finalwaveflagimageview = new ImageView(finalwaveflagimage);
        finalwaveflagimageview.setX(630); finalwaveflagimageview.setY(7);
        finalwaveflagimageview.setFitWidth(30); finalwaveflagimageview.setFitHeight(30);


        Image zombieheadimage=new Image(new FileInputStream("PvZ_PNGs/Progress_Bar/zombiehead.png"));
        this.zombieheadimageview = new ImageView(zombieheadimage);
        zombieheadimageview.setX(840); zombieheadimageview.setY(7);
        zombieheadimageview.setFitWidth(30); zombieheadimageview.setFitHeight(30);

        this.gameinterfacepane.getChildren().add(emptyprogressbar);
        this.gameinterfacepane.getChildren().add(zombieheadimageview);
        this.gameinterfacepane.getChildren().add(finalwaveflagimageview);
        increment();
    }

    public void increment(){ //USE TIMELINE AND UPDATE X AND Y
        Timeline progress = new Timeline(new KeyFrame(Duration.millis(400), event -> {
            zombieheadimageview.setX(zombieheadimageview.getX()-0.84);
            headX = zombieheadimageview.getX();
        }));
        progress.setCycleCount(250);
        progress.play();
    }

    public ImageView getEmptyprogressbar() {
        return emptyprogressbar;
    }

    public ImageView getFinalwaveflagimageview() {
        return finalwaveflagimageview;
    }

    public ImageView getZombieheadimageview() {
        return zombieheadimageview;
    }

    public Pane getGameinterfacepane() {
        return gameinterfacepane;
    }
}
