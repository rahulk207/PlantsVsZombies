package sample;

import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class PauseMenu {
    transient private Pane gameinterfacepane;

    PauseMenu(Pane gameinterfacepane, Stage primaryStage, ArrayList<Timeline> timelines, int levelnumber, Level level) throws FileNotFoundException {
        for(int i=0;i<timelines.size();i++){
            timelines.get(i).pause();
        }
        this.gameinterfacepane=gameinterfacepane;
        Image inGameMenuimage = new Image(new FileInputStream("PvZ_PNGs/inGameMenu.png"));
        ImageView inGameMenuimageView = new ImageView(inGameMenuimage);
        inGameMenuimageView.setOpacity(0.90);
        inGameMenuimageView.setX(270); inGameMenuimageView.setY(170);
        inGameMenuimageView.setFitHeight(300); inGameMenuimageView.setFitWidth(500);

        this.gameinterfacepane.getChildren().add(inGameMenuimageView);


        Text resumeGame = new Text("Resume Game");
        resumeGame.setX(450); resumeGame.setY(290);
        resumeGame.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD, FontPosture.ITALIC,23));
        this.gameinterfacepane.getChildren().add(resumeGame);

        Button resumeGameButton = new Button();
        resumeGameButton.setLayoutX(440); resumeGameButton.setLayoutY(255);
        resumeGameButton.setPrefWidth(170); resumeGameButton.setPrefHeight(40);
        this.gameinterfacepane.getChildren().add(resumeGameButton);

        Text saveGame = new Text("Save Game");
        saveGame.setX(460); saveGame.setY(340);
        saveGame.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD, FontPosture.ITALIC,23));
        this.gameinterfacepane.getChildren().add(saveGame);

        Button saveGameButton = new Button();
        saveGameButton.setLayoutX(440); saveGameButton.setLayoutY(305);
        saveGameButton.setPrefWidth(170); saveGameButton.setPrefHeight(40);
        this.gameinterfacepane.getChildren().add(saveGameButton);

        //serialize
        saveGameButton.setOnMouseClicked(mouseEvent -> {
            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Game1.txt"));
                out.writeObject(level);

                out.close();
                System.out.println("Object has been serialized");

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        Text exitGame = new Text("Exit Game");
        exitGame.setX(470); exitGame.setY(390);
        exitGame.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD, FontPosture.ITALIC,23));
        this.gameinterfacepane.getChildren().add(exitGame);

        Button exitGameButton = new Button();
        exitGameButton.setLayoutX(440); exitGameButton.setLayoutY(355);
        exitGameButton.setPrefWidth(170); exitGameButton.setPrefHeight(40);
        this.gameinterfacepane.getChildren().add(exitGameButton);
        exitGameButton.setOnMouseClicked(mouseEvent ->
        {
            try {
                MainMenu mainMenu= new MainMenu(primaryStage, levelnumber);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        resumeGameButton.setOpacity(0);
        saveGameButton.setOpacity(0);
        exitGameButton.setOpacity(0);

        resumeGameButton.setOnMouseClicked(mouseEvent -> {
            inGameMenuimageView.setVisible(false);
            resumeGame.setVisible(false); resumeGameButton.setVisible(false);
            saveGame.setVisible(false); saveGameButton.setVisible(false);
            exitGame.setVisible(false); exitGameButton.setVisible(false);
            for(int i=0;i<timelines.size();i++){
                timelines.get(i).play();
            }
        });

    }
}
