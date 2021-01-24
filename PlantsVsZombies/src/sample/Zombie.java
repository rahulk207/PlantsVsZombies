package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Zombie implements Serializable {
    transient public Timeline zombietimeline;
    public boolean isCollided;
    private double speed;
    public int row;
    public int firstPlantPos = 0;
    public double health;
    private double x = 950, y;
    transient Pane gameinterfacepane;
    transient ImageView zombieView;
    private ArrayList<ArrayList<Zombie>> rowZombies;
    transient ArrayList<Timeline> timelines;
    transient Stage primaryStage;
    private int levelnumber;
    transient Image fakeImage = new Image(new FileInputStream("PvZ_PNGs/Lawn/fakeImage.jpg"));


    public Zombie(Pane gameinterfacepane, double y, double health, int row, Creature[][] plantGrid, ArrayList<ArrayList<Zombie>> rowzombie, boolean[][] isPlant, Image zombie,
                  LawnMower lawnMower, ArrayList<Timeline> timelines, int levelnumber, Stage primaryStage) throws FileNotFoundException {
        this.gameinterfacepane = gameinterfacepane;
        this.speed = 2;
        this.primaryStage=primaryStage;
        this.levelnumber=levelnumber;
        rowZombies=rowzombie;
        isCollided = false;
        zombieView = new ImageView(zombie);
        this.timelines=timelines;
        this.y = y+40;
        zombieView.setX(this.x);
        zombieView.setY(this.y);
        this.row = row;
        this.health = health;
        this.gameinterfacepane.getChildren().add(zombieView);
        move(plantGrid, isPlant, lawnMower);
    }

    public void move(Creature[][] plantGrid, boolean[][] isPlant, LawnMower lawnMower){
        zombietimeline = new Timeline(new KeyFrame(Duration.millis(100), actionEvent ->
        {
            if(!isCollided) {
                zombieView.setX(zombieView.getX() - speed);
                x = x - speed;
                for (int i = 0; i < plantGrid[row].length; i++) {
                    if (plantGrid[row][i] instanceof Plant)
                    {
                        if (Math.abs(zombieView.getX() - plantGrid[row][i].getImage().getX()) < 35) {
                            isCollided = true;
                            firstPlantPos = i;
                        }
                    }
                }
            }
            else {
                Creature firstPlant = plantGrid[row][firstPlantPos];
                firstPlant.health -= 2;
                if(firstPlant.health<=0){
                    if(firstPlant.getClass()==Cherrybomb.class)
                    {
                        ((Cherrybomb) firstPlant).explode(rowZombies, this);
                    }
                    if(firstPlant.getClass()==Potatomine.class)
                    {
                        ((Potatomine) firstPlant).explode(rowZombies, this);
                    }
                    firstPlant.getImage().setVisible(false);
                    this.gameinterfacepane.getChildren().remove(firstPlant.getImage());
                    isCollided = false;

                    plantGrid[row][firstPlantPos] = new Creature(firstPlant.getX(), firstPlant.getY(), new ImageView(fakeImage), gameinterfacepane, 100);
//                    firstPlant=null;

                    isPlant[row][firstPlantPos] = false;
                }
            }

            if(zombieView.getX() <= 5){
                lawnMower.moveAndKillAll(rowZombies.get(row));
            }

            if(zombieView.getX() <= -20)
            {
                for(int i=0;i<timelines.size();i++){
                    timelines.get(i).pause();
                }
                try {
                    gamelost();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }));
        zombietimeline.setCycleCount(Timeline.INDEFINITE);
        zombietimeline.play();
        timelines.add(zombietimeline);
    }

    public void gamelost() throws FileNotFoundException {
        GameLostMenu gameLostMenu= new GameLostMenu(gameinterfacepane, primaryStage, timelines, levelnumber);
    }

    public ImageView getZombieView() {
        return zombieView;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
