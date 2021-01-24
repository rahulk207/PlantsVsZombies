package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;

public class Lawn implements Serializable {
//    private ArrayList<ArrayList<Zombie>> rowZombies;
//    private ArrayList<ArrayList<Pea>> rowPeas;
//    private Plant[][] plantGrid;
    private LawnMower[] lawnMowers;
    transient public Pane gameinterfacepane;
    private final int n_rows = 5;
    private final int n_columns = 9;
    public ProgressBar progressBar;
    transient ImageView sunflowerSeedView, peashooterSeedView, repeaterSeedView,
            wallnutSeedView, potatomineSeedView, cherrybombSeedView;

    public Lawn(Pane gameinterfacepane)throws FileNotFoundException {
        this.gameinterfacepane = gameinterfacepane;
        this.lawnMowers = new LawnMower[5];
//        this.plantGrid = plantGrid;
//        this.rowPeas = rowPeas; this.rowZombies = rowZombies;

        FileInputStream bginput=new FileInputStream("PvZ_PNGs/Lawn/mainBG_1054x612.png");
        Image backgroundimage=new Image(bginput);
        BackgroundSize bgsize=new BackgroundSize(1054,612,true,true,true,true);
        BackgroundImage backgroundImage=new BackgroundImage(backgroundimage, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bgsize);
        Background background=new Background(backgroundImage);
        this.gameinterfacepane.setBackground(background);

        this.progressBar = new ProgressBar(gameinterfacepane);

        for(int i=0;i<this.n_rows;i++){
            this.lawnMowers[i] = new LawnMower(this.gameinterfacepane, 0, 100*(i+1));
        }

        Image sunflowerSeed = new Image(new FileInputStream("PvZ_PNGs/Plants/sunflower/sunflower_seed.png"));
        Image peashooterSeed = new Image(new FileInputStream("PvZ_PNGs/Plants/peashooter/peashooter_seed.png"));
        Image repeaterSeed = new Image(new FileInputStream("PvZ_PNGs/Plants/repeater/repeater_seed.png"));
        Image wallnutSeed = new Image(new FileInputStream("PvZ_PNGs/Plants/wallnut/wallnut_seed.jpg"));
        Image potatomineSeed = new Image(new FileInputStream("PvZ_PNGs/Plants/potatomine/potatomine_seed.png"));
        Image cherrybombSeed = new Image(new FileInputStream("PvZ_PNGs/Plants/cherrybomb/cherrybomb_seed.png"));

        this.sunflowerSeedView = new ImageView(sunflowerSeed);
        this.peashooterSeedView = new ImageView(peashooterSeed);
        this.repeaterSeedView = new ImageView(repeaterSeed);
        this.wallnutSeedView = new ImageView(wallnutSeed);
        this.potatomineSeedView = new ImageView(potatomineSeed);
        this.cherrybombSeedView = new ImageView(cherrybombSeed);

        peashooterSeedView.setX(115); peashooterSeedView.setY(7);
        peashooterSeedView.setFitHeight(74); peashooterSeedView.setFitWidth(53);
        peashooterSeedView.setOpacity(0.75);

        gameinterfacepane.getChildren().add(peashooterSeedView);

        sunflowerSeedView.setX(170); sunflowerSeedView.setY(7);
        sunflowerSeedView.setFitHeight(74); sunflowerSeedView.setFitWidth(53);
        sunflowerSeedView.setOpacity(075);

        gameinterfacepane.getChildren().add(sunflowerSeedView);

        cherrybombSeedView.setX(225); cherrybombSeedView.setY(7);
        cherrybombSeedView.setFitHeight(74); cherrybombSeedView.setFitWidth(53);
        cherrybombSeedView.setOpacity(0.75);

        gameinterfacepane.getChildren().add(cherrybombSeedView);

        wallnutSeedView.setX(280); wallnutSeedView.setY(7);
        wallnutSeedView.setFitHeight(74); wallnutSeedView.setFitWidth(53);
        wallnutSeedView.setOpacity(0.75);

        gameinterfacepane.getChildren().add(wallnutSeedView);

        potatomineSeedView.setX(335); potatomineSeedView.setY(7);
        potatomineSeedView.setFitHeight(74); potatomineSeedView.setFitWidth(53);

        gameinterfacepane.getChildren().add(potatomineSeedView);

//        repeaterSeedView.setX(390); repeaterSeedView.setY(7);
//        repeaterSeedView.setFitHeight(74); repeaterSeedView.setFitWidth(53);
//        repeaterSeedView.setOpacity(0.75);
//
//        gameinterfacepane.getChildren().add(repeaterSeedView);

    }

    public LawnMower[] getLawnMowers() {
        return lawnMowers;
    }

    public ImageView getSunflowerSeedView() {
        return sunflowerSeedView;
    }

    public ImageView getPeashooterSeedView() {
        return peashooterSeedView;
    }

    public ImageView getRepeaterSeedView() {
        return repeaterSeedView;
    }

    public ImageView getWallnutSeedView() {
        return wallnutSeedView;
    }

    public ImageView getPotatomineSeedView() {
        return potatomineSeedView;
    }

    public ImageView getCherrybombSeedView() {
        return cherrybombSeedView;
    }

    public int getN_columns() {
        return n_columns;
    }

    public int getN_rows() {
        return n_rows;
    }

    public void rebuild(Pane gameinterfacepane){

    }
}
