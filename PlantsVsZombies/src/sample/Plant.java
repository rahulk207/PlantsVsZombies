package sample;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

abstract public class Plant extends Creature{
    public double health;
    private double x,y;
    private int rechargeTime;
    private int sunCost;
    transient Pane gameinterfacepane;
    transient ImageView plantImage;

    public Plant(double x, double y, ImageView plant, Pane gameinterfacepane, double health){
        super(x,y, plant, gameinterfacepane, health);
    }

//    public int getRechargeTime() {
//        return rechargeTime;
//    }
//
//    public int getSunCost() {
//        return sunCost;
//    }
//
//    public ImageView getPlantImage() {
//        return plantImage;
//    }

}
