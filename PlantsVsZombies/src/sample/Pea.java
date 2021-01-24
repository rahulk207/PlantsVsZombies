package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;

public class Pea implements Serializable {
    transient private Pane gameinterfacepane;
    private double x,y;
    private double peaPower;
    transient private ImageView peaView;

    public Pea(Pane gameinterfacepane, double x, double y) throws FileNotFoundException {
        this.gameinterfacepane = gameinterfacepane;
        Image pea  = new Image(new FileInputStream("PvZ_PNGs/Plants/peashooter/pea.png"));
        this.peaView = new ImageView(pea);
        this.peaPower = 1;
        this.x = x + 60; this.y = y + 20;
        peaView.setX(this.x); peaView.setY(this.y);
        peaView.setFitWidth(25); peaView.setFitHeight(25);
        this.gameinterfacepane.getChildren().add(peaView);
    }

    public void move(ArrayList<Zombie> thisRowZombie, ArrayList<Pea> thisRowPeas, ArrayList<Timeline> timelines){
        Timeline peatimeline = new Timeline(new KeyFrame(Duration.millis(40), actionEvent -> {
            peaView.setX(peaView.getX()+7);
            x = x + 14;
            if(thisRowZombie.size()>0) {
                Zombie firstZombie = thisRowZombie.get(0);
                if (Math.abs(peaView.getX() - firstZombie.getZombieView().getX()) < 5) {
                    firstZombie.health -= this.peaPower;
                    if(firstZombie.health<=0){
                        firstZombie.getZombieView().setVisible(false);
                        firstZombie.zombietimeline.stop();
                        gameinterfacepane.getChildren().remove(firstZombie.getZombieView());
                        thisRowZombie.remove(firstZombie);

                    }
                    peaView.setVisible(false);
                    gameinterfacepane.getChildren().remove(peaView);
                    thisRowPeas.remove(this);
                }
            }
        }));
        peatimeline.setCycleCount(Timeline.INDEFINITE);
        peatimeline.play();
        timelines.add(peatimeline);

    }
}
