package sample;

import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GameWonMenu {

    transient private Pane gameinterfacepane;

    GameWonMenu(Pane gameinterfacepane, Stage primaryStage, ArrayList<Timeline> timelines, int levelNumber) throws FileNotFoundException {

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

        Text resumeGame = new Text("Game Won");
        resumeGame.setX(450); resumeGame.setY(290);
        resumeGame.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD, 30));
        this.gameinterfacepane.getChildren().add(resumeGame);

        Text continueGame = new Text("Continue Game");
        continueGame.setX(460); continueGame.setY(340);
        continueGame.setFont(Font.font("Comic Sans MS",  FontPosture.ITALIC,23));
        this.gameinterfacepane.getChildren().add(continueGame);

        Button continueGameButton = new Button();
        continueGameButton.setLayoutX(440); continueGameButton.setLayoutY(305);
        continueGameButton.setPrefWidth(170); continueGameButton.setPrefHeight(40);
        this.gameinterfacepane.getChildren().add(continueGameButton);

        continueGameButton.setOnMouseClicked(mouseEvent -> {
            try {
                Level level = new Level(levelNumber, primaryStage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        Text exitGame = new Text("Exit Game");
        exitGame.setX(470); exitGame.setY(390);
        exitGame.setFont(Font.font("Comic Sans MS",  FontPosture.ITALIC,23));
        this.gameinterfacepane.getChildren().add(exitGame);

        Button exitGameButton = new Button();
        exitGameButton.setLayoutX(440); exitGameButton.setLayoutY(355);
        exitGameButton.setPrefWidth(170); exitGameButton.setPrefHeight(40);
        this.gameinterfacepane.getChildren().add(exitGameButton);
        exitGameButton.setOnMouseClicked(mouseEvent ->
        {
            try {
                MainMenu mainMenu= new MainMenu(primaryStage, levelNumber);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        continueGameButton.setOpacity(0);
        exitGameButton.setOpacity(0);

    }
}
