package sample;

import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class coneHeadZombie extends Zombie {
    public coneHeadZombie(Pane gameinterfacepane, double y, double health, int row, Creature[][] plantGrid,  ArrayList<ArrayList<Zombie>> rowzombie, boolean[][] isPlant, Image zombie, LawnMower lawnMower, ArrayList<Timeline> timelines, int levelnumber, Stage primaryStage) throws FileNotFoundException {
        super(gameinterfacepane, y, health, row,plantGrid, rowzombie, isPlant, zombie, lawnMower, timelines,levelnumber, primaryStage);
    }
}
