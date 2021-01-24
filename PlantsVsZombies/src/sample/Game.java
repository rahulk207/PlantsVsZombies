//package sample;
//
//import javafx.animation.*;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.*;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontPosture;
//import javafx.scene.text.FontWeight;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//import javafx.animation.TranslateTransition;
//
//import java.applet.Applet;
//import java.awt.*;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.sql.BatchUpdateException;
//import java.sql.SQLOutput;
//import java.sql.Time;
//import java.util.ArrayList;
//import java.util.Random;
//
//public class Game  {
//    private Lawn lawn;
//    private MainMenu MainMenu;
//    private Pane gameinterfacepane;
//    public ImageView zombieView;
//
//    boolean isPeashooterSeedClicked = false, isSunflowerSeedClicked = false, ischerryBombSeedClicked = false,
//            isWallnutSeedClicked = false, isPotatomineSeedClicked = false, isRepeaterSeedClicked = false;
//    ArrayList<ImageView> peashooters = new ArrayList<>();
//    ArrayList<ImageView> sunflowers = new ArrayList<>();
//
//    Game(Stage primaryStage) throws FileNotFoundException, InterruptedException {
//        gameinterfacepane = new Pane();
//
//        Image peaShooter = new Image(new FileInputStream("/Users/pranayjain/Desktop/PvZ_PNGs/Plants/peashooter/peashooter_moving.gif"));
//        Image sunflower = new Image(new FileInputStream("/Users/pranayjain/Desktop/PvZ_PNGs/Plants/sunflower/sunflower_moving.gif"));
//        Image cherrybomb = new Image(new FileInputStream("/Users/pranayjain/Desktop/PvZ_PNGs/Plants/cherrybomb/cherrybomb_1.png"));
//        Image wallnut = new Image(new FileInputStream("/Users/pranayjain/Desktop/PvZ_PNGs/Plants/wallnut/wallnut_1.png"));
//        Image potatomine = new Image(new FileInputStream("/Users/pranayjain/Desktop/PvZ_PNGs/Plants/potatomine/potatomine_1_unarmed.png"));
//        Image repeater = new Image(new FileInputStream("/Users/pranayjain/Desktop/PvZ_PNGs/Plants/repeater/repeater_1.png"));
//
//        Image sun = new Image(new FileInputStream("/Users/pranayjain/Desktop/PvZ_PNGs/Plants/sunflower/sun.png"));
//
//        Image pea  = new Image(new FileInputStream("/Users/pranayjain/Desktop/PvZ_PNGs/Plants/peashooter/pea.png"));
//
//        // Adding the Background
//        FileInputStream bginput=new FileInputStream("/Users/pranayjain/Desktop/PvZ_PNGs/Lawn/mainBG_1054x612.png");
//        Image backgroundimage=new Image(bginput);
//        BackgroundSize bgsize=new BackgroundSize(1054,612,true,true,true,true);
//        BackgroundImage backgroundImage=new BackgroundImage(backgroundimage, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bgsize);
//        Background background=new Background(backgroundImage);
//        gameinterfacepane.setBackground(background);
//
//        // Adding the lawnmowers
//        FileInputStream lm=new FileInputStream("/Users/pranayjain/Desktop/PvZ_PNGs/Lawn/lawnmower.png");
//        Image lawnmowerimage=new Image(lm);
//        ImageView lawnmower1=new ImageView(lawnmowerimage);
//        ImageView lawnmower2=new ImageView(lawnmowerimage);
//        ImageView lawnmower3=new ImageView(lawnmowerimage);
//        ImageView lawnmower4=new ImageView(lawnmowerimage);
//        ImageView lawnmower5=new ImageView(lawnmowerimage);
//
//        lawnmower1.setX(0); lawnmower1.setY(100);
//        lawnmower1.setFitWidth(60); lawnmower1.setFitHeight(54);
//        lawnmower2.setX(0); lawnmower2.setY(200);
//        lawnmower2.setFitWidth(60); lawnmower2.setFitHeight(54);
//        lawnmower3.setX(0); lawnmower3.setY(300);
//        lawnmower3.setFitWidth(60); lawnmower3.setFitHeight(54);
//        lawnmower4.setX(0); lawnmower4.setY(400);
//        lawnmower4.setFitWidth(60); lawnmower4.setFitHeight(54);
//        lawnmower5.setX(0); lawnmower5.setY(500);
//        lawnmower5.setFitWidth(60); lawnmower5.setFitHeight(54);
//
//        gameinterfacepane.getChildren().add(lawnmower1);
//        gameinterfacepane.getChildren().add(lawnmower2);
//        gameinterfacepane.getChildren().add(lawnmower3);
//        gameinterfacepane.getChildren().add(lawnmower4);
//        gameinterfacepane.getChildren().add(lawnmower5);
//
//        moveLawnMower(lawnmower1);
//
//        //Adding the progress bar
//
//        Image barimage = new Image(new FileInputStream("/Users/pranayjain/Desktop/PvZ_PNGs/Progress_Bar/emptyprogressbar.png"));
//        ImageView emptyprogressbar = new ImageView(barimage);
//        emptyprogressbar.setX(620); emptyprogressbar.setY(5);
//        emptyprogressbar.setFitWidth(260); emptyprogressbar.setFitHeight(35);
//
//        Image finalwaveflagimage=new Image(new FileInputStream("/Users/pranayjain/Desktop/PvZ_PNGs/Progress_Bar/finalwaveflag.png"));
//        ImageView finalwaveflagimageview = new ImageView(finalwaveflagimage);
//        finalwaveflagimageview.setX(630); finalwaveflagimageview.setY(7);
//        finalwaveflagimageview.setFitWidth(30); finalwaveflagimageview.setFitHeight(30);
//
//
//        Image zombieheadimage=new Image(new FileInputStream("/Users/pranayjain/Desktop/PvZ_PNGs/Progress_Bar/zombiehead.png"));
//        ImageView zombieheadimageview = new ImageView(zombieheadimage);
//        zombieheadimageview.setX(840); zombieheadimageview.setY(7);
//        zombieheadimageview.setFitWidth(30); zombieheadimageview.setFitHeight(30);
//
//        gameinterfacepane.getChildren().add(emptyprogressbar);
//        gameinterfacepane.getChildren().add(zombieheadimageview);
//        gameinterfacepane.getChildren().add(finalwaveflagimageview);
//
//        incrementProgressBar(zombieheadimageview);
//
//        Image sunflowerSeed = new Image(new FileInputStream("/Users/pranayjain/Desktop/PvZ_PNGs/Plants/sunflower/sunflower_seed.png"));
//        Image peashooterSeed = new Image(new FileInputStream("/Users/pranayjain/Desktop/PvZ_PNGs/Plants/peashooter/peashooter_seed.png"));
//        Image repeaterSeed = new Image(new FileInputStream("/Users/pranayjain/Desktop/PvZ_PNGs/Plants/repeater/repeater_seed.png"));
//        Image wallnutSeed = new Image(new FileInputStream("/Users/pranayjain/Desktop/PvZ_PNGs/Plants/wallnut/wallnut_seed.jpg"));
//        Image potatomineSeed = new Image(new FileInputStream("/Users/pranayjain/Desktop/PvZ_PNGs/Plants/potatomine/potatomine_1_armed.png"));
//        Image cherrybombSeed = new Image(new FileInputStream("/Users/pranayjain/Desktop/PvZ_PNGs/Plants/cherrybomb/cherrybomb_seed.png"));
//
//        ImageView sunflowerSeedView = new ImageView(sunflowerSeed);
//        ImageView peashooterSeedView = new ImageView(peashooterSeed);
//        ImageView repeaterSeedView = new ImageView(repeaterSeed);
//        ImageView wallnutSeedView = new ImageView(wallnutSeed);
//        ImageView potatomineSeedView = new ImageView(potatomineSeed);
//        ImageView cherrybombSeedView = new ImageView(cherrybombSeed);
//
//        peashooterSeedView.setX(115); peashooterSeedView.setY(7);
//        peashooterSeedView.setFitHeight(74); peashooterSeedView.setFitWidth(53);
//        peashooterSeedView.setOpacity(0.75);
//
//        gameinterfacepane.getChildren().add(peashooterSeedView);
//
//        sunflowerSeedView.setX(170); sunflowerSeedView.setY(7);
//        sunflowerSeedView.setFitHeight(74); sunflowerSeedView.setFitWidth(53);
//        sunflowerSeedView.setOpacity(075);
//
//        gameinterfacepane.getChildren().add(sunflowerSeedView);
//
//        cherrybombSeedView.setX(225); cherrybombSeedView.setY(7);
//        cherrybombSeedView.setFitHeight(74); cherrybombSeedView.setFitWidth(53);
//        cherrybombSeedView.setOpacity(0.75);
//
//        gameinterfacepane.getChildren().add(cherrybombSeedView);
//
//        wallnutSeedView.setX(280); wallnutSeedView.setY(7);
//        wallnutSeedView.setFitHeight(74); wallnutSeedView.setFitWidth(53);
//        wallnutSeedView.setOpacity(0.75);
//
//        gameinterfacepane.getChildren().add(wallnutSeedView);
//
//        potatomineSeedView.setX(335); potatomineSeedView.setY(7);
//        potatomineSeedView.setFitHeight(74); potatomineSeedView.setFitWidth(53);
//
//        gameinterfacepane.getChildren().add(potatomineSeedView);
//
//        repeaterSeedView.setX(390); repeaterSeedView.setY(7);
//        repeaterSeedView.setFitHeight(74); repeaterSeedView.setFitWidth(53);
//        repeaterSeedView.setOpacity(0.75);
//
//        gameinterfacepane.getChildren().add(repeaterSeedView);
//
//        peashooterSeedView.setOnMouseClicked(mouseEvent -> {
//
//            isPeashooterSeedClicked = true;
//            isSunflowerSeedClicked = false;
//            ischerryBombSeedClicked = false;
//            isWallnutSeedClicked = false;
//            isPotatomineSeedClicked = false;
//            isRepeaterSeedClicked = false;
//            peashooterSeedView.setOpacity(1);
//        });
//
//        sunflowerSeedView.setOnMouseClicked(mouseEvent -> {
//            isPeashooterSeedClicked = false;
//            isSunflowerSeedClicked = true;
//            ischerryBombSeedClicked = false;
//            isWallnutSeedClicked = false;
//            isPotatomineSeedClicked = false;
//            isRepeaterSeedClicked = false;
//            sunflowerSeedView.setOpacity(1);
//        });
//
//        cherrybombSeedView.setOnMouseClicked(mouseEvent -> {
//            isPeashooterSeedClicked = false;
//            isSunflowerSeedClicked = false;
//            ischerryBombSeedClicked = true;
//            isWallnutSeedClicked = false;
//            isPotatomineSeedClicked = false;
//            isRepeaterSeedClicked = false;
//            cherrybombSeedView.setOpacity(1);
//        });
//
//        wallnutSeedView.setOnMouseClicked(mouseEvent -> {
//            isPeashooterSeedClicked = false;
//            isSunflowerSeedClicked = false;
//            ischerryBombSeedClicked = false;
//            isWallnutSeedClicked = true;
//            isPotatomineSeedClicked = false;
//            isRepeaterSeedClicked = false;
//            wallnutSeedView.setOpacity(1);
//        });
//
//        potatomineSeedView.setOnMouseClicked(mouseEvent -> {
//            isPeashooterSeedClicked = false;
//            isSunflowerSeedClicked = false;
//            ischerryBombSeedClicked = false;
//            isWallnutSeedClicked = false;
//            isPotatomineSeedClicked = true;
//            isRepeaterSeedClicked = false;
//            potatomineSeedView.setOpacity(1);
//        });
//
//        repeaterSeedView.setOnMouseClicked(mouseEvent -> {
//            isPeashooterSeedClicked = false;
//            isSunflowerSeedClicked = false;
//            ischerryBombSeedClicked = false;
//            isWallnutSeedClicked = false;
//            isPotatomineSeedClicked = false;
//            isRepeaterSeedClicked = true;
//            repeaterSeedView.setOpacity(1);
//        });
//
//
//        Text sunCount = new Text("0");
//        sunCount.setX(55); sunCount.setY(82);
//        sunCount.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD,22));
//
//        gameinterfacepane.getChildren().add(sunCount);
//
//        Image fakeImage = new Image(new FileInputStream("/Users/pranayjain/Desktop/PvZ_PNGs/Lawn/fakeImage.jpg"));
//        ImageView[][] gridView = new ImageView[5][9];
//
//        for(int i=0;i<5;i++){
//            for(int j=0;j<9;j++){
//                gridView[i][j] = new ImageView(fakeImage);
//                gridView[i][j].setX(40+(106.6)*j); gridView[i][j].setY(95+(98*i));
//                gridView[i][j].setFitWidth(106.6); gridView[i][j].setFitHeight(98);
//                gridView[i][j].setOpacity(0);
//                gameinterfacepane.getChildren().add(gridView[i][j]);
//                int finalI = i;
//                int finalJ = j;
//                gridView[i][j].setOnMouseClicked(mouseEvent -> {
//                    if(isPeashooterSeedClicked){
//                        gridView[finalI][finalJ].setImage(peaShooter);
////                            peashooters.add(gridView[finalI][finalJ]);
//                        isPeashooterSeedClicked = false;
//                        peashooterSeedView.setOpacity(0.75);
//                        gridView[finalI][finalJ].setOpacity(1);
//
//                        Timeline peaShootInterval = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
//
//                            @Override
//                            public void handle(ActionEvent event) {
////                System.out.println("this is called every 3 seconds on UI thread");
//                                try {
//                                    ImageView peaView = shootpeas(gridView[finalI][finalJ], pea);
//                                } catch (FileNotFoundException e) {
//                                    e.printStackTrace();
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }));
//                        peaShootInterval.setCycleCount(Timeline.INDEFINITE);
//                        peaShootInterval.play();
//                    }
//                    else if (isSunflowerSeedClicked){
//                        gridView[finalI][finalJ].setImage(sunflower);
////                            sunflowers.add(gridView[finalI][finalJ]);
//                        isSunflowerSeedClicked = false;
//                        sunflowerSeedView.setOpacity(0.75);
//                        gridView[finalI][finalJ].setOpacity(1);
//                        Timeline sunAppearSunflower = new Timeline(new KeyFrame(Duration.seconds(15), actionEvent -> {
//                            ImageView sunView = createSun(sun, gridView[finalI][finalJ].getX() + 60, gridView[finalI][finalJ].getY() + 20);
//
////                suns.add(sunView);
//                            Timeline sunDisappear1 = new Timeline(new KeyFrame(Duration.seconds(7), actionEvent1 -> sunView.setVisible(false)));
//                            sunDisappear1.setCycleCount(1);
//                            sunDisappear1.play();
//                            sunView.setOnMouseClicked(mouseEvent1 -> {
//                                sunView.setVisible(false);
//                                int currSunCount = Integer.parseInt((sunCount.getText()));
//                                sunCount.setText(String.valueOf(currSunCount + 25));
//                            });
//                        }));
//                        sunAppearSunflower.setCycleCount(Timeline.INDEFINITE);
//                        sunAppearSunflower.play();
//                    }
//                    else if (ischerryBombSeedClicked){
//                        gridView[finalI][finalJ].setImage(cherrybomb);
////                            peashooters.add(gridView[finalI][finalJ]);
//                        ischerryBombSeedClicked = false;
//                        cherrybombSeedView.setOpacity(0.75);
//                        gridView[finalI][finalJ].setOpacity(1);
//                    }
//                    else if(isWallnutSeedClicked){
//                        gridView[finalI][finalJ].setImage(wallnut);
////                            peashooters.add(gridView[finalI][finalJ]);
//                        isWallnutSeedClicked = false;
//                        wallnutSeedView.setOpacity(0.75);
//                        gridView[finalI][finalJ].setOpacity(1);
//                    }
//                    else if (isPotatomineSeedClicked){
//                        gridView[finalI][finalJ].setImage(potatomine);
////                            peashooters.add(gridView[finalI][finalJ]);
//                        isPotatomineSeedClicked = false;
//                        potatomineSeedView.setOpacity(0.75);
//                        gridView[finalI][finalJ].setOpacity(1);
//                    }
//                    else if (isRepeaterSeedClicked){
//                        gridView[finalI][finalJ].setImage(repeater);
////                            peashooters.add(gridView[finalI][finalJ]);
//                        isRepeaterSeedClicked = false;
//                        repeaterSeedView.setOpacity(0.75);
//                        gridView[finalI][finalJ].setOpacity(1);
//                    }
//                });
//            }
//        }
//
////        Image[] zombie1 = new Image[92]; ImageView[] zombieview1 = new ImageView[92];
////        for(int i=0;i<zombie1.length;i++){
////            String s = String.valueOf(i);
////            String path = "/Users/pranayjain/Desktop/PvZ_PNGs/Zombies/normal/zombie_moving_frames/" + s + ".gif";
////            zombie1[i] = new Image(new FileInputStream(path));
////            zombieview1[i] = new ImageView(zombie1[i]);
////        }
////
//////        zombieview1[0].setX(500); zombieview1[0].setY(300);
////        Group zombie = new Group(zombieview1[0]);
////        zombie.setTranslateX(500); zombie.setTranslateY(300);//THIS IS DOUBTFUL
////        gameinterfacepane.getChildren().add(zombie);
////
////        moveNormalZombie(zombieview1, zombie);
//
//        Timeline sunFallInterval = new Timeline(new KeyFrame(Duration.seconds(10), new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
////                System.out.println("this is called every 3 seconds on UI thread");
//                Random random = new Random();
//                ImageView sunView = createSun(sun,random.nextInt(800),0);
////                suns.add(sunView);
//                Timeline sunDisappear2 = new Timeline(new KeyFrame(Duration.seconds(7), new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent actionEvent) {
//                        sunView.setVisible(false);
//                    }
//                }));
//                sunDisappear2.setCycleCount(1);
//                sunDisappear2.play();
//                sunView.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent mouseEvent) {
//                        sunView.setVisible(false);
//                        int currSunCount = Integer.parseInt((sunCount.getText()));
//                        sunCount.setText(String.valueOf(currSunCount + 25));
//                    }
//                });
//                moveSun(sunView);
//            }
//        }));
//        sunFallInterval.setCycleCount(Timeline.INDEFINITE);
//        sunFallInterval.play();
//
////        Image zombie = new Image(new FileInputStream("/Users/pranayjain/Desktop/PvZ_PNGs/Zombies/conehead/conehead_zombie_moving.gif"));
////        zombieView = new ImageView(zombie);
////        zombieView.setX(950);
////        zombieView.setY(300);
////        gameinterfacepane.getChildren().add(zombieView);
////
////        moveConeheadZombie(zombieView);
//
////        Image zombie1 = new Image(new FileInputStream("/Users/pranayjain/Desktop/PvZ_PNGs/Zombies/normal/zombie_moving.gif"));
////        ImageView zombieView1 = new ImageView(zombie1);
////        zombieView1.setX(900);
////        zombieView1.setY(130);
////        gameinterfacepane.getChildren().add(zombieView1);
//
////        moveConeheadZombie(zombieView1);
//
//        Image inGameMenuimage = new Image(new FileInputStream("/Users/pranayjain/Desktop/PvZ_PNGs/inGameMenu.png"));
//        ImageView inGameMenuimageView = new ImageView(inGameMenuimage);
//        inGameMenuimageView.setOpacity(0.90);
//        inGameMenuimageView.setX(270); inGameMenuimageView.setY(170);
//        inGameMenuimageView.setFitHeight(300); inGameMenuimageView.setFitWidth(500);
//        inGameMenuimageView.setVisible(false);
//
//        gameinterfacepane.getChildren().add(inGameMenuimageView);
//
//        Button resumeGameButton = new Button();
//        resumeGameButton.setLayoutX(440); resumeGameButton.setLayoutY(255);
//        resumeGameButton.setPrefWidth(170); resumeGameButton.setPrefHeight(40);
//
//
//        Text resumeGame = new Text("Resume Game");
//        resumeGame.setX(450); resumeGame.setY(290);
//        resumeGame.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD, FontPosture.ITALIC,23));
//        resumeGame.setVisible(false);
//        gameinterfacepane.getChildren().add(resumeGame);
//
//        Button saveGameButton = new Button();
//        saveGameButton.setLayoutX(440); saveGameButton.setLayoutY(305);
//        saveGameButton.setPrefWidth(170); saveGameButton.setPrefHeight(40);
//
//        Text saveGame = new Text("Save Game");
//        saveGame.setX(460); saveGame.setY(340);
//        saveGame.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD, FontPosture.ITALIC,23));
//        saveGame.setVisible(false);
//        gameinterfacepane.getChildren().add(saveGame);
//
//        Button exitGameButton = new Button();
//        exitGameButton.setLayoutX(440); exitGameButton.setLayoutY(355);
//        exitGameButton.setPrefWidth(170); exitGameButton.setPrefHeight(40);
//        exitGameButton.setOnMouseClicked(mouseEvent ->
//        {
//            try {
//                MainMenu = new MainMenu(primaryStage);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        });
//
//        Text exitGame = new Text("Exit Game");
//        exitGame.setX(470); exitGame.setY(390);
//        exitGame.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD, FontPosture.ITALIC,23));
//        exitGame.setVisible(false);
//        gameinterfacepane.getChildren().add(exitGame);
//
//        Button pauseGameButton = new Button();
//        pauseGameButton.setLayoutX(900); pauseGameButton.setLayoutY(3);
//        pauseGameButton.setPrefWidth(150); pauseGameButton.setPrefHeight(20);
//        pauseGameButton.setOnMouseClicked(mouseEvent -> {
//            inGameMenuimageView.setVisible(true);
//            resumeGame.setVisible(true);
//            saveGame.setVisible(true);
//            exitGame.setVisible(true);
//        });
//
//        resumeGameButton.setOnMouseClicked(mouseEvent -> {
//            inGameMenuimageView.setVisible(false);
//            resumeGame.setVisible(false);
//            saveGame.setVisible(false);
//            exitGame.setVisible(false);
//        });
//
//        Text pauseGame = new Text("Pause Game");
//        pauseGame.setX(920); pauseGame.setY(18);
//        pauseGame.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD, FontPosture.ITALIC,20));
//        gameinterfacepane.getChildren().add(pauseGame);
//
//        pauseGameButton.setOpacity(0);
//        resumeGameButton.setOpacity(0);
//        saveGameButton.setOpacity(0);
//        exitGameButton.setOpacity(0);
//
//        gameinterfacepane.getChildren().add(pauseGameButton);
//        gameinterfacepane.getChildren().add(resumeGameButton);
//        gameinterfacepane.getChildren().add(saveGameButton);
//        gameinterfacepane.getChildren().add(exitGameButton);
//
//        primaryStage.setScene(new Scene(gameinterfacepane, 1054, 612));
//        primaryStage.show();
//    }
//
//    private void incrementProgressBar(ImageView zombieHead){
//        TranslateTransition incrementProgressBar = new TranslateTransition(Duration.seconds(98), zombieHead);
//        incrementProgressBar.setByX(-210);
//        incrementProgressBar.setCycleCount(1);
//        incrementProgressBar.play();
//    }
//
//    private ImageView createSun(Image sun, double x, double y){
//        ImageView sunView = new ImageView(sun);
//        sunView.setX(x); sunView.setY(y);
//        sunView.setFitHeight(40); sunView.setFitWidth(40);
//        gameinterfacepane.getChildren().add(sunView);
//
//        return sunView;
//    }
//
//    private void moveSun(ImageView sun){
//        TranslateTransition moveSun = new TranslateTransition(Duration.seconds(5),sun);
////        moveLawnMover1.setAutoReverse(true);
//        Random random = new Random();
//        int r = random.nextInt(500);
//        moveSun.setByY(r);
//        moveSun.setCycleCount(1);
//        moveSun.play();
//    }
//
//    private ImageView shootpeas(ImageView peashooter, Image pea) throws FileNotFoundException, InterruptedException {
//        ImageView peaView = new ImageView(pea);
//        peaView.setX(peashooter.getX()+60); peaView.setY(peashooter.getY()+20);
//        peaView.setFitWidth(25); peaView.setFitHeight(25);
//        gameinterfacepane.getChildren().add(peaView);
//        movePeas(peaView);
//
//        return peaView;
//    }
//
//    private void movePeas(ImageView pea){
//        Timeline peatimeline = new Timeline(new KeyFrame(Duration.millis(40), new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                pea.setX(pea.getX()+7);
//                System.out.println(pea.getX()+" hi "+zombieView.getX());
//                if (Math.abs(pea.getX()-zombieView.getX())<5)
//                {
//                    System.out.println(pea.getX()+" hi "+zombieView.getX());
//                    System.out.println("Yes its a hit");
//                    pea.setVisible(false);
//                    gameinterfacepane.getChildren().remove(pea);
//                }
//            }
//        }));
//        peatimeline.setCycleCount(Timeline.INDEFINITE);
//        peatimeline.play();
//    }
//
//    private void moveLawnMower(ImageView lawnmower)
//    {
//        TranslateTransition moveLawnMower = new TranslateTransition(Duration.seconds(4),lawnmower);
////        moveLawnMover1.setAutoReverse(true);
//        moveLawnMower.setByX(1100);
//        moveLawnMower.setCycleCount(1);
//        moveLawnMower.play();
//    }
//
//    private void moveConeheadZombie(ImageView zombie){
//        Timeline zombietimeline = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                zombie.setX(zombie.getX()-0.7);
//            }
//        }));
//            zombietimeline.setCycleCount(Timeline.INDEFINITE);
//            zombietimeline.play();
//    }
//    private void moveNormalZombie(ImageView[] zombieview, Group zombie){
//
////        zombieview[0].setX(1000); zombieview[0].setY(600);
//        Timeline zombieTimeline = new Timeline();
//        zombieTimeline.setCycleCount(10);
//        for(int i=1;i<=zombieview.length;i++) {
//            int finalI = i;
//            zombieTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(i*10),
//                    actionEvent -> zombie.getChildren().setAll(zombieview[finalI - 1]),
//                    new KeyValue(zombie.translateXProperty(), 500-i*0.1, Interpolator.EASE_BOTH)));
//        }
//
//        zombieTimeline.play();
//    }
//
//    public Pane getGameinterfacepane()
//    {
//        return gameinterfacepane;
//    }
//}
//
