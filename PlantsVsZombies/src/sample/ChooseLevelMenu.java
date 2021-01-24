package sample;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ChooseLevelMenu {
    transient private Pane root;
    public ChooseLevelMenu(Stage primaryStage,int levelunlocked) throws FileNotFoundException {
        root = new Pane();

        // Adding the Background
        FileInputStream bginput=new FileInputStream("PvZ_PNGs/MenuWallpaper.jpeg");
        Image backgroundimage=new Image(bginput);
        BackgroundSize bgsize=new BackgroundSize(1054,612,false,false,true,true);
        BackgroundImage backgroundImage=new BackgroundImage(backgroundimage, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bgsize);
        Background background=new Background(backgroundImage);
        root.setBackground(background);

        //Adding the four wooden panels
        FileInputStream wpinput=new FileInputStream("PvZ_PNGs/woodenpanel.png");
        Image woodenpanelimage=new Image(wpinput);
        ImageView imageview1=new ImageView(woodenpanelimage);
        ImageView imageview2=new ImageView(woodenpanelimage);
        ImageView imageview3=new ImageView(woodenpanelimage);
        ImageView imageview4=new ImageView(woodenpanelimage);
        ImageView imageview5=new ImageView(woodenpanelimage);
        imageview1.setX(380);
        imageview1.setY(190);
        imageview1.setFitHeight(65);
        imageview1.setFitWidth(240);
        imageview1.setOpacity(0.8);
        imageview1.setOnMouseEntered(mouseEvent -> {
            if(imageview1.isHover())
            {
                imageview1.setOpacity(1);
            }
        });
        imageview1.setOnMouseExited(mouseEvent -> imageview1.setOpacity(0.8));
        imageview1.setOnMouseClicked(mouseEvent -> {
            try {
                Level level = new Level(1, primaryStage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        imageview2.setX(380);
        imageview2.setY(270);
        imageview2.setFitHeight(65);
        imageview2.setFitWidth(240);
        imageview2.setOpacity(0.8);
        imageview2.setOnMouseEntered(mouseEvent -> imageview2.setOpacity(1));
        imageview2.setOnMouseExited(mouseEvent -> imageview2.setOpacity(0.8));
        imageview2.setOnMouseClicked(mouseEvent -> {
            try {
                if(levelunlocked>=1)
                {
                    Level level = new Level(2, primaryStage);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        imageview3.setX(380);
        imageview3.setY(350);
        imageview3.setFitHeight(65);
        imageview3.setFitWidth(240);
        imageview3.setOpacity(0.7);
        imageview3.setOnMouseEntered(mouseEvent -> imageview3.setOpacity(1));
        imageview3.setOnMouseExited(mouseEvent -> imageview3.setOpacity(0.7));
        imageview3.setOnMouseClicked(mouseEvent -> {
            try {
                if(levelunlocked>=1)
                {
                    Level level = new Level(3, primaryStage);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        imageview4.setX(380);
        imageview4.setY(430);
        imageview4.setFitHeight(65);
        imageview4.setFitWidth(240);
        imageview4.setOpacity(0.8);
        imageview4.setOnMouseEntered(mouseEvent -> imageview4.setOpacity(1));
        imageview4.setOnMouseExited(mouseEvent -> imageview4.setOpacity(0.8));
        imageview4.setOnMouseClicked(mouseEvent -> {
            try {
                if(levelunlocked>=1)
                {
                    Level level = new Level(4, primaryStage);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        imageview5.setX(380);
        imageview5.setY(510);
        imageview5.setFitHeight(65);
        imageview5.setFitWidth(240);
        imageview5.setOpacity(0.8);
        imageview5.setOnMouseEntered(mouseEvent -> {
            if(imageview5.isHover())
            {
                imageview5.setOpacity(1);
            }
        });
        imageview5.setOnMouseExited(mouseEvent -> imageview5.setOpacity(0.8));
        imageview5.setOnMouseClicked(mouseEvent -> {
            try {
                if(levelunlocked>=1)
                {
                    Level level = new Level(5, primaryStage);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        root.getChildren().add(imageview1);
        root.getChildren().add(imageview2);
        root.getChildren().add(imageview3);
        root.getChildren().add(imageview4);
        root.getChildren().add(imageview5);

        //Adding the four texts
        Text text1=new Text("Level 1");
        text1.setX(420);
        text1.setY(232);
        text1.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD, FontPosture.ITALIC,30));
        Text text2=new Text("Level 2");
        text2.setX(424);
        text2.setY(312);
        text2.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD, FontPosture.ITALIC,30));
        Text text3=new Text("Level 3");
        text3.setX(424);
        text3.setY(392);
        text3.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD, FontPosture.ITALIC,30));
        Text text4=new Text("Level 4");
        text4.setX(410);
        text4.setY(472);
        text4.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD, FontPosture.ITALIC,30));
        Text text5=new Text("Level 5");
        text5.setX(420);
        text5.setY(552);
        text5.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD, FontPosture.ITALIC,30));
        root.getChildren().add(text1);
        root.getChildren().add(text2);
        root.getChildren().add(text3);
        root.getChildren().add(text4);
        root.getChildren().add(text5);
        primaryStage.setScene(new Scene(root, 1054, 612));
        primaryStage.show();
    }
}
