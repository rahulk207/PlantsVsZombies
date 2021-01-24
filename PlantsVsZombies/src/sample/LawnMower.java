package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;

public class LawnMower implements Serializable {
    transient Pane gameinterfacepane;
    public double x,y;
    transient ImageView lawnMowerImage;
    boolean isPresent;

    public LawnMower(Pane gameinterfacepane, double x, double y) throws FileNotFoundException {
        isPresent = true;
        FileInputStream lm = new FileInputStream("PvZ_PNGs/Lawn/lawnmower.png");
        Image lawnmowerImage = new Image(lm);
        this.lawnMowerImage = new ImageView(lawnmowerImage);
        this.x = x; this.y = y;
        this.lawnMowerImage.setX(this.x); this.lawnMowerImage.setY(this.y);
        this.lawnMowerImage.setFitWidth(60); this.lawnMowerImage.setFitHeight(54);

        this.gameinterfacepane = gameinterfacepane;
        this.gameinterfacepane.getChildren().add(this.lawnMowerImage);

    }

    public void moveAndKillAll(ArrayList<Zombie> thisrowZombies){
        isPresent = false;
        Timeline moveLawnMower = new Timeline(new KeyFrame(Duration.millis(100), actionEvent -> {
           lawnMowerImage.setX(lawnMowerImage.getX() + 8);
           x = x + 8;
           int nr_zombies = thisrowZombies.size();
           for(int i=0;i<nr_zombies;i++){
               if(lawnMowerImage.getX() >= thisrowZombies.get(0).getZombieView().getX() && lawnMowerImage.getX()<950){
                   thisrowZombies.get(0).getZombieView().setVisible(false);
                   thisrowZombies.get(0).getZombieView().disableProperty();
                   thisrowZombies.get(0).zombietimeline.stop();
                   gameinterfacepane.getChildren().remove(thisrowZombies.get(0).getZombieView());
                   thisrowZombies.remove(thisrowZombies.get(0));
               }

           }
        }));
        moveLawnMower.setCycleCount(Timeline.INDEFINITE);
        moveLawnMower.play();
    }
}