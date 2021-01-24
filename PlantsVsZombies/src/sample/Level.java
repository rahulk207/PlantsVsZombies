package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Level implements Serializable {

    int wave_number = 0;
    int level_number;
    private Lawn lawn;
    transient private Pane gameinterfacepane;
    boolean flag1=true,flag2=true,flag3=true,flag4=true,flag5=true;
    private ArrayList<ArrayList<Zombie>> rowZombies;
    private ArrayList<ArrayList<Pea>> rowPeas;
    private Creature[][] plantGrid;
    private boolean[][] isPlant;
    transient private ArrayList<Timeline> timelines;

//    ImageView peaShooter, sunFlower, cherryBomb, wallNut, potatoMine, rePeater;
    transient private Text sunCount;
    String sunAmount;
    boolean isPeashooterSeedClicked = false, isSunflowerSeedClicked = false, ischerryBombSeedClicked = false,
            isWallnutSeedClicked = false, isPotatomineSeedClicked = false, isRepeaterSeedClicked = false;

    public Level(int level_number, Stage primaryStage) throws FileNotFoundException {
        this.level_number = level_number;
        timelines = new ArrayList<>();
        this.gameinterfacepane = new Pane();
        Image peashooter = new Image(new FileInputStream("PvZ_PNGs/Plants/peashooter/peashooter_moving.gif"));
        Image sunflower = new Image(new FileInputStream("PvZ_PNGs/Plants/sunflower/sunflower_moving.gif"));
        Image cherrybomb = new Image(new FileInputStream("PvZ_PNGs/Plants/cherrybomb/cherrybomb_1.png"));
        Image wallnut = new Image(new FileInputStream("PvZ_PNGs/Plants/wallnut/wallnut_1.gif"));
        Image potatomine = new Image(new FileInputStream("PvZ_PNGs/Plants/potatomine/potatomine_1_armed.png"));
        Image repeater = new Image(new FileInputStream("PvZ_PNGs/Plants/repeater/repeater_1.png"));


        lawn = new Lawn(gameinterfacepane);
        this.rowZombies = new ArrayList<>();
        this.rowPeas = new ArrayList<>();

        this.isPlant = new boolean[lawn.getN_rows()][lawn.getN_columns()];
        for(int i=0;i<lawn.getN_rows();i++){
            for(int j=0;j<lawn.getN_columns();j++){
                isPlant[i][j] = false;
            }
        }

        this.plantGrid = new Creature[lawn.getN_rows()][lawn.getN_columns()];

        for(int i=0; i<lawn.getN_rows(); i++){
            this.rowZombies.add(new ArrayList<>());
            this.rowPeas.add(new ArrayList<>());
        }

        this.sunCount = new Text("300"); sunAmount = this.sunCount.getText();
        sunCount.setX(55); sunCount.setY(82);
        sunCount.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD,22));

        this.gameinterfacepane.getChildren().add(sunCount);

        lawn.getPeashooterSeedView().setOnMouseClicked(mouseEvent -> {

            isPeashooterSeedClicked = true;
            isSunflowerSeedClicked = false;
            ischerryBombSeedClicked = false;
            isWallnutSeedClicked = false;
            isPotatomineSeedClicked = false;
            isRepeaterSeedClicked = false;
            lawn.getPeashooterSeedView().setOpacity(1);
        });

        lawn.getSunflowerSeedView().setOnMouseClicked(mouseEvent -> {
            isPeashooterSeedClicked = false;
            isSunflowerSeedClicked = true;
            ischerryBombSeedClicked = false;
            isWallnutSeedClicked = false;
            isPotatomineSeedClicked = false;
            isRepeaterSeedClicked = false;
            lawn.getSunflowerSeedView().setOpacity(1);
        });

        lawn.getCherrybombSeedView().setOnMouseClicked(mouseEvent -> {
            isPeashooterSeedClicked = false;
            isSunflowerSeedClicked = false;
            ischerryBombSeedClicked = true;
            isWallnutSeedClicked = false;
            isPotatomineSeedClicked = false;
            isRepeaterSeedClicked = false;
            lawn.getCherrybombSeedView().setOpacity(1);
        });

        lawn.getWallnutSeedView().setOnMouseClicked(mouseEvent -> {
            isPeashooterSeedClicked = false;
            isSunflowerSeedClicked = false;
            ischerryBombSeedClicked = false;
            isWallnutSeedClicked = true;
            isPotatomineSeedClicked = false;
            isRepeaterSeedClicked = false;
            lawn.getWallnutSeedView().setOpacity(1);
        });

        lawn.getPotatomineSeedView().setOnMouseClicked(mouseEvent -> {
            isPeashooterSeedClicked = false;
            isSunflowerSeedClicked = false;
            ischerryBombSeedClicked = false;
            isWallnutSeedClicked = false;
            isPotatomineSeedClicked = true;
            isRepeaterSeedClicked = false;
            lawn.getPotatomineSeedView().setOpacity(1);
        });

        lawn.getRepeaterSeedView().setOnMouseClicked(mouseEvent -> {
            isPeashooterSeedClicked = false;
            isSunflowerSeedClicked = false;
            ischerryBombSeedClicked = false;
            isWallnutSeedClicked = false;
            isPotatomineSeedClicked = false;
            isRepeaterSeedClicked = true;
            lawn.getRepeaterSeedView().setOpacity(1);
        });

        Image fakeImage = new Image(new FileInputStream("PvZ_PNGs/Lawn/fakeImage.jpg"));

        for(int i=0;i<lawn.getN_rows();i++){
            for(int j=0;j<lawn.getN_columns();j++){
                plantGrid[i][j] = new Creature(40+(106.6)*j, 95+(98*i), new ImageView(fakeImage), gameinterfacepane, 100);
                int finalI = i;
                int finalJ = j;
                plantGrid[i][j].getImage().setOnMouseClicked(mouseEvent -> {
                    if(isPeashooterSeedClicked) {
                        if (!isPlant[finalI][finalJ] && (Integer.parseInt((sunCount.getText())) >= 100)) {
                            if (flag1==true) {
                                ImageView peaShooter = new ImageView(peashooter);
                                plantGrid[finalI][finalJ] = new PeaShooter(plantGrid[finalI][finalJ].getX(), plantGrid[finalI][finalJ].getY(), peaShooter, 1.5, gameinterfacepane, 100);
                                isPeashooterSeedClicked = false;
                                lawn.getPeashooterSeedView().setOpacity(0.75);
                                plantGrid[finalI][finalJ].getImage().setOpacity(1);
                                isPlant[finalI][finalJ] = true;
                                int currSunCount = Integer.parseInt((sunCount.getText()));
                                sunCount.setText(String.valueOf(currSunCount - 100));

                                Timeline peaShootInterval = new Timeline(new KeyFrame(Duration.seconds(((PeaShooter) plantGrid[finalI][finalJ]).getShootInterval()), event -> {
                                    try {
                                        if (!rowZombies.get(finalI).isEmpty()) {
                                            try {
                                                ((PeaShooter) plantGrid[finalI][finalJ]).shoot(rowPeas.get(finalI), rowZombies.get(finalI), timelines);
                                            }
                                            catch (ClassCastException c){

                                            }
                                        }
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                }));
                                peaShootInterval.setCycleCount(Timeline.INDEFINITE);
                                peaShootInterval.play();
                                ((PeaShooter) plantGrid[finalI][finalJ]).shootTimer = peaShootInterval;
                                timelines.add(peaShootInterval);
                                flag1 = false;
                                Timeline refreshtime = new Timeline(new KeyFrame(Duration.seconds(7), actionEvent -> {
                                    flag1=true;
                                }));
                                refreshtime.setCycleCount(1);
                                refreshtime.play();
                                timelines.add(refreshtime);
                            }
                        }
                    }
                    else if (isSunflowerSeedClicked && (Integer.parseInt((sunCount.getText()))>=50)) {
                        if (!isPlant[finalI][finalJ] && flag2==true) {

                            ImageView sunFlower = new ImageView(sunflower);
                            plantGrid[finalI][finalJ] = new Sunflower(plantGrid[finalI][finalJ].getX(), plantGrid[finalI][finalJ].getY(), sunFlower, 16, gameinterfacepane, 100);

                            isSunflowerSeedClicked = false;
                            lawn.getSunflowerSeedView().setOpacity(0.75);
                            plantGrid[finalI][finalJ].getImage().setOpacity(1);
                            isPlant[finalI][finalJ] = true;
                            AtomicInteger currSunCount = new AtomicInteger(Integer.parseInt((sunCount.getText())));
                            sunCount.setText(String.valueOf(currSunCount.get() - 50));
                            sunAmount = sunCount.getText();

                            Timeline sunAppearSunflower = new Timeline(new KeyFrame(Duration.seconds(((Sunflower) plantGrid[finalI][finalJ]).getSunProducingInterval()), actionEvent -> {

                                try {
                                    ((Sunflower) plantGrid[finalI][finalJ]).produceSun(plantGrid[finalI][finalJ].getX() + 60, plantGrid[finalI][finalJ].getY() + 20, sunCount);
                                } catch (SunCollectedException s) {
                                    currSunCount.set(Integer.parseInt((sunCount.getText())));
                                    sunCount.setText(String.valueOf(currSunCount.get() + 25));
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                  catch (ClassCastException c){

                                  }
                            }));
                            sunAppearSunflower.setCycleCount(Timeline.INDEFINITE);
                            sunAppearSunflower.play();
                            ((Sunflower)plantGrid[finalI][finalJ]).sunProduceTimer = sunAppearSunflower;
                            timelines.add(sunAppearSunflower);
                            flag2 = false;
                            Timeline refreshtime2 = new Timeline(new KeyFrame(Duration.seconds(7), actionEvent -> {
                                flag2=true;
                            }));
                            refreshtime2.setCycleCount(1);
                            refreshtime2.play();
                            timelines.add(refreshtime2);
                        }
                    }
                    else if (ischerryBombSeedClicked && (Integer.parseInt((sunCount.getText()))>=150)) {
                        if (!isPlant[finalI][finalJ] && flag3==true) {
                            ImageView cherryBomb = new ImageView(cherrybomb);
                            plantGrid[finalI][finalJ] = new Cherrybomb(plantGrid[finalI][finalJ].getX(), plantGrid[finalI][finalJ].getY(), cherryBomb, gameinterfacepane, 30);

                            ischerryBombSeedClicked = false;
                            lawn.getCherrybombSeedView().setOpacity(0.75);
                            plantGrid[finalI][finalJ].getImage().setOpacity(1);
                            isPlant[finalI][finalJ] = true;
                            int currSunCount = Integer.parseInt((sunCount.getText()));
                            sunCount.setText(String.valueOf(currSunCount - 150));
                            flag3 = false;
                            Timeline refreshtime3 = new Timeline(new KeyFrame(Duration.seconds(40), actionEvent -> {
                                flag3=true;
                            }));
                            refreshtime3.setCycleCount(1);
                            refreshtime3.play();
                            timelines.add(refreshtime3);
                        }
                    }
                    else if(isWallnutSeedClicked && (Integer.parseInt((sunCount.getText()))>=50)){
                        if(!isPlant[finalI][finalJ] && flag4==true) {
                            ImageView wallNut = new ImageView(wallnut);
                            plantGrid[finalI][finalJ] = new Wallnut(plantGrid[finalI][finalJ].getX(), plantGrid[finalI][finalJ].getY(), wallNut,  gameinterfacepane, 1000);
                            isWallnutSeedClicked=false;
                            lawn.getWallnutSeedView().setOpacity(0.75);
                            plantGrid[finalI][finalJ].getImage().setOpacity(1);
                            isPlant[finalI][finalJ] = true;
                            int currSunCount = Integer.parseInt((sunCount.getText()));
                            sunCount.setText(String.valueOf(currSunCount - 50));
                            flag4 = false;
                            Timeline refreshtime4 = new Timeline(new KeyFrame(Duration.seconds(25), actionEvent -> {
                                flag4=true;
                            }));
                            refreshtime4.setCycleCount(1);
                            refreshtime4.play();
                            timelines.add(refreshtime4);
                        }
                    }
                    else if (isPotatomineSeedClicked && (Integer.parseInt((sunCount.getText()))>=25)){
                        if(!isPlant[finalI][finalJ] && flag5==true) {
                            ImageView potatoMine = new ImageView(potatomine);
                            plantGrid[finalI][finalJ] = new Potatomine(plantGrid[finalI][finalJ].getX(), plantGrid[finalI][finalJ].getY(), potatoMine, gameinterfacepane, 20);
                            isPotatomineSeedClicked = false;
                            lawn.getPotatomineSeedView().setOpacity(0.75);
                            plantGrid[finalI][finalJ].getImage().setOpacity(1);
                            isPlant[finalI][finalJ] = true;
                            int currSunCount = Integer.parseInt((sunCount.getText()));
                            sunCount.setText(String.valueOf(currSunCount - 25));
                            flag5 = false;
                            Timeline refreshtime5 = new Timeline(new KeyFrame(Duration.seconds(25), actionEvent -> {
                                flag5=true;
                            }));
                            refreshtime5.setCycleCount(1);
                            refreshtime5.play();
                            timelines.add(refreshtime5);
                        }
                    }
//                    else if (isRepeaterSeedClicked && (Integer.parseInt((sunCount.getText()))>=200)){
//                        if(!isPlant[finalI][finalJ]) {
//                            ImageView potatoMine = new ImageView(potatomine);
//                            plantGrid[finalI][finalJ] = new Potatomine(plantGrid[finalI][finalJ].getX(), plantGrid[finalI][finalJ].getY(), potatoMine, gameinterfacepane, 20);
//                            isPotatomineSeedClicked = false;
//                            lawn.getPotatomineSeedView().setOpacity(0.75);
//                            plantGrid[finalI][finalJ].getImage().setOpacity(1);
//                            int currSunCount = Integer.parseInt((sunCount.getText()));
//                            sunCount.setText(String.valueOf(currSunCount - 200));
//                        }
//                    }
                });
            }
        }

        Timeline sunFallInterval = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
//                System.out.println("this is called every 3 seconds on UI thread");
            Random random = new Random();
            Sun sun = null;
            try {
                sun = new Sun(gameinterfacepane, random.nextInt(800),0);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Sun finalSun = sun;
            Timeline sunDisappear2 = new Timeline(new KeyFrame(Duration.seconds(14), actionEvent -> finalSun.getSunView().setVisible(false)));
            sunDisappear2.setCycleCount(1);
            sunDisappear2.play();
            timelines.add(sunDisappear2);
            Sun finalSun1 = sun;

            sun.getSunView().setOnMouseClicked(mouseEvent -> {
                finalSun1.getSunView().setVisible(false);
                int currSunCount = Integer.parseInt((sunCount.getText()));
                sunCount.setText(String.valueOf(currSunCount + 25));
                sunAmount = sunCount.getText();
            });
            sun.fall();
        }));
        sunFallInterval.setCycleCount(Timeline.INDEFINITE);
        sunFallInterval.play();
        timelines.add(sunFallInterval);

        Image normalzombie = new Image(new FileInputStream("PvZ_PNGs/Zombies/normal/zombie_normal.gif"));
        Image coneheadzombie = new Image(new FileInputStream("PvZ_PNGs/Zombies/conehead/ezgif.com-crop.gif"));
        Image bucketheadzombie = new Image(new FileInputStream("PvZ_PNGs/Zombies/buckethead/ezgif.com-optimize.gif"));
        Image flagzombie = new Image(new FileInputStream("PvZ_PNGs/Zombies/flag/ezgif.com-resize.gif"));

        Timeline createZombies = new Timeline(new KeyFrame(Duration.seconds(20), actionEvent -> { //reduce the 10 second limit for higher levels
            wave_number++;
            Random random = new Random();
            int numofzombies = random.nextInt(3)+2;
            for(int i=0;i<numofzombies;i++) {
                int r = random.nextInt(5);
                int zombietype = random.nextInt(level_number+1);
                try {
                    if (zombietype == 0) {
                        rowZombies.get(r).add(new normalZombie(gameinterfacepane, r * 100, 10, r, plantGrid, rowZombies, isPlant, normalzombie, lawn.getLawnMowers()[r], timelines, level_number, primaryStage));
                    } else if (zombietype == 1) {
                        rowZombies.get(r).add(new coneHeadZombie(gameinterfacepane, r * 100, 28, r, plantGrid, rowZombies, isPlant, coneheadzombie, lawn.getLawnMowers()[r], timelines, level_number, primaryStage));
                    } else if (zombietype == 2) {
                        rowZombies.get(r).add(new bucketHeadZombie(gameinterfacepane, r * 100, 65, r, plantGrid, rowZombies, isPlant, bucketheadzombie, lawn.getLawnMowers()[r], timelines, level_number, primaryStage));
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }));
        createZombies.setCycleCount(4);
        createZombies.play();
        timelines.add(createZombies);


        //      Final wave
        Timeline finalcreateZombies = new Timeline(new KeyFrame(Duration.seconds(110), actionEvent -> { //reduce the 10 second limit for higher levels
            Random random = new Random();
            int numofzombies = random.nextInt(3)+5;
            Text FinalWave = new Text("Final wave of Zombies is Coming!!");
            FinalWave.setX(250); FinalWave.setY(170);
            FinalWave.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD, FontPosture.ITALIC,40));
            this.gameinterfacepane.getChildren().add(FinalWave);

            Timeline finalwavetimeline= new Timeline(new KeyFrame(Duration.seconds(3), actionEvent2 ->
            {
                FinalWave.setVisible(false);
            }));
            finalwavetimeline.setCycleCount(1);
            finalwavetimeline.play();
            timelines.add(finalwavetimeline);

            try {
                rowZombies.get(2).add(new flagZombie(gameinterfacepane, 200, 15, 2, plantGrid, rowZombies, isPlant, flagzombie, lawn.getLawnMowers()[2],timelines,level_number,primaryStage));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            for(int i=0;i<numofzombies;i++) {
                int r = random.nextInt(5);
                int zombietype = random.nextInt(level_number);
                try {
                    if (zombietype == 0) {
                        rowZombies.get(r).add(new normalZombie(gameinterfacepane, r * 100, 10, r, plantGrid, rowZombies, isPlant, normalzombie, lawn.getLawnMowers()[r], timelines, level_number, primaryStage));
                    } else if (zombietype == 1) {
                        rowZombies.get(r).add(new coneHeadZombie(gameinterfacepane, r * 100, 28, r, plantGrid, rowZombies, isPlant, coneheadzombie, lawn.getLawnMowers()[r], timelines, level_number, primaryStage));
                    } else if (zombietype == 2) {
                        rowZombies.get(r).add(new bucketHeadZombie(gameinterfacepane, r * 100, 65, r, plantGrid, rowZombies, isPlant, bucketheadzombie, lawn.getLawnMowers()[r], timelines, level_number, primaryStage));
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }));
        finalcreateZombies.setCycleCount(1);
        finalcreateZombies.play();
        timelines.add(finalcreateZombies);

        Timeline checkGameWinStart = new Timeline(new KeyFrame(Duration.seconds(112), actionEvent -> {
            Timeline checkGameWin = new Timeline(new KeyFrame(Duration.millis(500), actionEvent1 -> {
               boolean flag=true;
               for (int i=0;i<rowZombies.size();i++)
               {
                   if (rowZombies.get(i).size()!=0)
                   {
                       flag=false;
                   }
               }
               if(flag){
                   try {
                       GameWonMenu gameWon = new GameWonMenu(gameinterfacepane, primaryStage, timelines, level_number + 1);
                   } catch (FileNotFoundException e) {
                       e.printStackTrace();
                   }
               }

            }));
            checkGameWin.setCycleCount(Timeline.INDEFINITE);
            checkGameWin.play();
            timelines.add(checkGameWin);
        }));
        checkGameWinStart.setCycleCount(1);
        checkGameWinStart.play();
        timelines.add(checkGameWinStart);

        Text pauseGame = new Text("Pause Game");
        pauseGame.setX(920); pauseGame.setY(18);
        pauseGame.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD, FontPosture.ITALIC,20));
        gameinterfacepane.getChildren().add(pauseGame);

        Button pauseGameButton = new Button();
        pauseGameButton.setLayoutX(900); pauseGameButton.setLayoutY(3);
        pauseGameButton.setPrefWidth(150); pauseGameButton.setPrefHeight(20);
        pauseGameButton.setOpacity(0)
        ;
        gameinterfacepane.getChildren().add(pauseGameButton);
        pauseGameButton.setOnMouseClicked(mouseEvent -> {
            try {
                System.out.println("yo");
                PauseMenu pauseMenu= new PauseMenu(gameinterfacepane,primaryStage, timelines, level_number, this);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        primaryStage.setScene(new Scene(gameinterfacepane, 1054, 612));
        primaryStage.show();
    }


    public void rebuild(Stage primaryStage) throws FileNotFoundException {

        gameinterfacepane = new Pane();
        Image peashooter = new Image(new FileInputStream("PvZ_PNGs/Plants/peashooter/peashooter_moving.gif"));
        Image sunflower = new Image(new FileInputStream("PvZ_PNGs/Plants/sunflower/sunflower_moving.gif"));
        Image cherrybomb = new Image(new FileInputStream("PvZ_PNGs/Plants/cherrybomb/cherrybomb_1.png"));
        Image wallnut = new Image(new FileInputStream("PvZ_PNGs/Plants/wallnut/wallnut_1.gif"));
        Image potatomine = new Image(new FileInputStream("PvZ_PNGs/Plants/potatomine/potatomine_1_armed.png"));
        Image fakeImage = new Image(new FileInputStream("PvZ_PNGs/Lawn/fakeImage.jpg"));

        lawn.gameinterfacepane = gameinterfacepane;

        FileInputStream bginput=new FileInputStream("PvZ_PNGs/Lawn/mainBG_1054x612.png");
        Image backgroundimage=new Image(bginput);
        BackgroundSize bgsize=new BackgroundSize(1054,612,true,true,true,true);
        BackgroundImage backgroundImage=new BackgroundImage(backgroundimage, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bgsize);
        Background background=new Background(backgroundImage);
        gameinterfacepane.setBackground(background);

        Image sunflowerSeed = new Image(new FileInputStream("PvZ_PNGs/Plants/sunflower/sunflower_seed.png"));
        Image peashooterSeed = new Image(new FileInputStream("PvZ_PNGs/Plants/peashooter/peashooter_seed.png"));
        Image repeaterSeed = new Image(new FileInputStream("PvZ_PNGs/Plants/repeater/repeater_seed.png"));
        Image wallnutSeed = new Image(new FileInputStream("PvZ_PNGs/Plants/wallnut/wallnut_seed.jpg"));
        Image potatomineSeed = new Image(new FileInputStream("PvZ_PNGs/Plants/potatomine/potatomine_seed.png"));
        Image cherrybombSeed = new Image(new FileInputStream("PvZ_PNGs/Plants/cherrybomb/cherrybomb_seed.png"));

        lawn.sunflowerSeedView = new ImageView(sunflowerSeed);
        lawn.peashooterSeedView = new ImageView(peashooterSeed);
        lawn.wallnutSeedView = new ImageView(wallnutSeed);
        lawn.potatomineSeedView = new ImageView(potatomineSeed);
        lawn.cherrybombSeedView = new ImageView(cherrybombSeed);

        lawn.peashooterSeedView.setX(115); lawn.peashooterSeedView.setY(7);
        lawn.peashooterSeedView.setFitHeight(74); lawn.peashooterSeedView.setFitWidth(53);
        lawn.peashooterSeedView.setOpacity(0.75);

        gameinterfacepane.getChildren().add(lawn.peashooterSeedView);

        lawn.sunflowerSeedView.setX(170); lawn.sunflowerSeedView.setY(7);
        lawn.sunflowerSeedView.setFitHeight(74); lawn.sunflowerSeedView.setFitWidth(53);
        lawn.sunflowerSeedView.setOpacity(075);

        gameinterfacepane.getChildren().add(lawn.sunflowerSeedView);

        lawn.cherrybombSeedView.setX(225); lawn.cherrybombSeedView.setY(7);
        lawn.cherrybombSeedView.setFitHeight(74); lawn.cherrybombSeedView.setFitWidth(53);
        lawn.cherrybombSeedView.setOpacity(0.75);

        gameinterfacepane.getChildren().add(lawn.cherrybombSeedView);

        lawn.wallnutSeedView.setX(280); lawn.wallnutSeedView.setY(7);
        lawn.wallnutSeedView.setFitHeight(74); lawn.wallnutSeedView.setFitWidth(53);
        lawn.wallnutSeedView.setOpacity(0.75);

        gameinterfacepane.getChildren().add(lawn.wallnutSeedView);

        lawn.potatomineSeedView.setX(335); lawn.potatomineSeedView.setY(7);
        lawn.potatomineSeedView.setFitHeight(74); lawn.potatomineSeedView.setFitWidth(53);

        gameinterfacepane.getChildren().add(lawn.potatomineSeedView);

        lawn.progressBar.gameinterfacepane = gameinterfacepane;

        Image barimage = new Image(new FileInputStream("PvZ_PNGs/Progress_Bar/emptyprogressbar.png"));
        Image finalwaveflagimage=new Image(new FileInputStream("PvZ_PNGs/Progress_Bar/finalwaveflag.png"));
        Image zombieheadimage=new Image(new FileInputStream("PvZ_PNGs/Progress_Bar/zombiehead.png"));

        lawn.progressBar.emptyprogressbar = new ImageView(barimage);
        lawn.progressBar.finalwaveflagimageview = new ImageView(finalwaveflagimage);
        lawn.progressBar.zombieheadimageview = new ImageView(zombieheadimage);

        lawn.progressBar.getEmptyprogressbar().setX(lawn.progressBar.emptyX);
        lawn.progressBar.getEmptyprogressbar().setY(lawn.progressBar.emptyY);
        lawn.progressBar.getEmptyprogressbar().setFitWidth(260); lawn.progressBar.getEmptyprogressbar().setFitHeight(35);

        lawn.progressBar.getFinalwaveflagimageview().setX(lawn.progressBar.flagX);
        lawn.progressBar.getFinalwaveflagimageview().setY(lawn.progressBar.flagY);
        lawn.progressBar.getFinalwaveflagimageview().setFitWidth(30); lawn.progressBar.getFinalwaveflagimageview().setFitHeight(30);

        lawn.progressBar.getZombieheadimageview().setX(lawn.progressBar.headX);
        lawn.progressBar.getZombieheadimageview().setY(lawn.progressBar.headY);
        lawn.progressBar.getZombieheadimageview().setFitWidth(30); lawn.progressBar.getZombieheadimageview().setFitHeight(30);

        gameinterfacepane.getChildren().add(lawn.progressBar.getEmptyprogressbar());
        gameinterfacepane.getChildren().add(lawn.progressBar.getFinalwaveflagimageview());
        gameinterfacepane.getChildren().add(lawn.progressBar.getZombieheadimageview());

        lawn.progressBar.increment();

        this.sunCount = new Text(sunAmount);
        sunCount.setX(55); sunCount.setY(82);
        sunCount.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD,22));

        gameinterfacepane.getChildren().add(sunCount);

        lawn.getPeashooterSeedView().setOnMouseClicked(mouseEvent -> {

            isPeashooterSeedClicked = true;
            isSunflowerSeedClicked = false;
            ischerryBombSeedClicked = false;
            isWallnutSeedClicked = false;
            isPotatomineSeedClicked = false;
            isRepeaterSeedClicked = false;
            lawn.getPeashooterSeedView().setOpacity(1);
        });

        lawn.getSunflowerSeedView().setOnMouseClicked(mouseEvent -> {
            isPeashooterSeedClicked = false;
            isSunflowerSeedClicked = true;
            ischerryBombSeedClicked = false;
            isWallnutSeedClicked = false;
            isPotatomineSeedClicked = false;
            isRepeaterSeedClicked = false;
            lawn.getSunflowerSeedView().setOpacity(1);
        });

        lawn.getCherrybombSeedView().setOnMouseClicked(mouseEvent -> {
            isPeashooterSeedClicked = false;
            isSunflowerSeedClicked = false;
            ischerryBombSeedClicked = true;
            isWallnutSeedClicked = false;
            isPotatomineSeedClicked = false;
            isRepeaterSeedClicked = false;
            lawn.getCherrybombSeedView().setOpacity(1);
        });

        lawn.getWallnutSeedView().setOnMouseClicked(mouseEvent -> {
            isPeashooterSeedClicked = false;
            isSunflowerSeedClicked = false;
            ischerryBombSeedClicked = false;
            isWallnutSeedClicked = true;
            isPotatomineSeedClicked = false;
            isRepeaterSeedClicked = false;
            lawn.getWallnutSeedView().setOpacity(1);
        });

        lawn.getPotatomineSeedView().setOnMouseClicked(mouseEvent -> {
            isPeashooterSeedClicked = false;
            isSunflowerSeedClicked = false;
            ischerryBombSeedClicked = false;
            isWallnutSeedClicked = false;
            isPotatomineSeedClicked = true;
            isRepeaterSeedClicked = false;
            lawn.getPotatomineSeedView().setOpacity(1);
        });

//        lawn.getRepeaterSeedView().setOnMouseClicked(mouseEvent -> {
//            isPeashooterSeedClicked = false;
//            isSunflowerSeedClicked = false;
//            ischerryBombSeedClicked = false;
//            isWallnutSeedClicked = false;
//            isPotatomineSeedClicked = false;
//            isRepeaterSeedClicked = true;
//            lawn.getRepeaterSeedView().setOpacity(1);
//        });

        FileInputStream lm = new FileInputStream("PvZ_PNGs/Lawn/lawnmower.png");
        Image lawnmowerImage = new Image(lm);
        for(int i=0;i<lawn.getLawnMowers().length;i++) {
            lawn.getLawnMowers()[i].lawnMowerImage = new ImageView(lawnmowerImage);
            lawn.getLawnMowers()[i].lawnMowerImage.setX(lawn.getLawnMowers()[i].x);
            lawn.getLawnMowers()[i].lawnMowerImage.setY(lawn.getLawnMowers()[i].y);
            lawn.getLawnMowers()[i].lawnMowerImage.setFitWidth(60);
            lawn.getLawnMowers()[i].lawnMowerImage.setFitHeight(54);

            lawn.getLawnMowers()[i].gameinterfacepane = gameinterfacepane;
            gameinterfacepane.getChildren().add(lawn.getLawnMowers()[i].lawnMowerImage);
        }

        timelines = new ArrayList<>();

        Image normalzombie = new Image(new FileInputStream("PvZ_PNGs/Zombies/normal/zombie_normal.gif"));
        Image coneheadzombie = new Image(new FileInputStream("PvZ_PNGs/Zombies/conehead/ezgif.com-crop.gif"));
        Image bucketheadzombie = new Image(new FileInputStream("PvZ_PNGs/Zombies/buckethead/ezgif.com-optimize.gif"));
        Image flagzombie = new Image(new FileInputStream("PvZ_PNGs/Zombies/flag/ezgif.com-resize.gif"));

        for(int i=0;i<lawn.getN_rows();i++){
            for(int j=0;j<lawn.getN_columns();j++){
                   plantGrid[i][j].gameinterfacepane = gameinterfacepane;
                   if(plantGrid[i][j].getClass() == Creature.class){
                       plantGrid[i][j].fakeImage = new ImageView(fakeImage);
                       plantGrid[i][j].fakeImage.setOpacity(0);
                   }
                   else if(plantGrid[i][j].getClass() == PeaShooter.class){
                       plantGrid[i][j].fakeImage = new ImageView(peashooter);
                       plantGrid[i][j].fakeImage.setOpacity(1);
                       int finalI = i;
                       int finalJ = j;
                       ((PeaShooter)plantGrid[i][j]).shootTimer = new Timeline(new KeyFrame(Duration.seconds(((PeaShooter) plantGrid[i][j]).getShootInterval()), event -> {
                               try {
                                   if (!rowZombies.get(finalI).isEmpty()) {
                                       try {
                                           ((PeaShooter) plantGrid[finalI][finalJ]).shoot(rowPeas.get(finalI), rowZombies.get(finalI), timelines);
                                       }
                                       catch (ClassCastException c){

                                       }
                                   }
                               } catch (FileNotFoundException e) {
                                   e.printStackTrace();
                               }
                       }));
                       ((PeaShooter)plantGrid[i][j]).shootTimer.setCycleCount(Timeline.INDEFINITE);
                       ((PeaShooter)plantGrid[i][j]).shootTimer.play();
                       timelines.add(((PeaShooter)plantGrid[i][j]).shootTimer);
                   }
                   else if(plantGrid[i][j].getClass() == Sunflower.class){
                       plantGrid[i][j].fakeImage = new ImageView(sunflower);
                       plantGrid[i][j].fakeImage.setOpacity(1);
                       int finalI = i;
                       int finalJ = j;
                       AtomicInteger currSunCount = new AtomicInteger(Integer.parseInt((sunCount.getText())));
                       ((Sunflower)plantGrid[i][j]).sunProduceTimer = new Timeline(new KeyFrame(Duration.seconds(((Sunflower) plantGrid[finalI][finalJ]).getSunProducingInterval()), actionEvent -> {
                           try {
                               ((Sunflower) plantGrid[finalI][finalJ]).produceSun(plantGrid[finalI][finalJ].getX() + 60, plantGrid[finalI][finalJ].getY() + 20, sunCount);
                           } catch (SunCollectedException s) {
                               currSunCount.set(Integer.parseInt((sunCount.getText())));
                               sunCount.setText(String.valueOf(currSunCount.get() + 25));
                           } catch (FileNotFoundException e) {
                               e.printStackTrace();
                           }
                           catch (ClassCastException c){

                           }
                       }));
                       ((Sunflower) plantGrid[finalI][finalJ]).sunProduceTimer.setCycleCount(Timeline.INDEFINITE);
                       ((Sunflower) plantGrid[finalI][finalJ]).sunProduceTimer.play();
                       timelines.add(((Sunflower) plantGrid[finalI][finalJ]).sunProduceTimer);
                   }
                   else if(plantGrid[i][j].getClass() == Cherrybomb.class){
                       plantGrid[i][j].fakeImage = new ImageView(cherrybomb);
                       plantGrid[i][j].fakeImage.setOpacity(1);
                   }
                   else if(plantGrid[i][j].getClass() == Potatomine.class){
                       plantGrid[i][j].fakeImage = new ImageView(potatomine);
                       plantGrid[i][j].fakeImage.setOpacity(1);
                   }
                   else if(plantGrid[i][j].getClass() == Wallnut.class){
                       plantGrid[i][j].fakeImage = new ImageView(wallnut);
                       plantGrid[i][j].fakeImage.setOpacity(1);
                   }

                   plantGrid[i][j].fakeImage.setX(plantGrid[i][j].x);
                   plantGrid[i][j].fakeImage.setY(plantGrid[i][j].y);
                   plantGrid[i][j].fakeImage.setFitWidth(106.6); plantGrid[i][j].fakeImage.setFitHeight(98);

                   gameinterfacepane.getChildren().add(plantGrid[i][j].fakeImage);

            }
        }

        for(int i=0;i<lawn.getN_rows();i++){
            for(int j=0;j<lawn.getN_columns();j++){
                int finalI = i;
                int finalJ = j;
                plantGrid[i][j].getImage().setOnMouseClicked(mouseEvent -> {
                    if(isPeashooterSeedClicked) {
                        if (!isPlant[finalI][finalJ] && (Integer.parseInt((sunCount.getText())) >= 100)) {
                            if (flag1==true) {
                                ImageView peaShooter = new ImageView(peashooter);
                                plantGrid[finalI][finalJ] = new PeaShooter(plantGrid[finalI][finalJ].getX(), plantGrid[finalI][finalJ].getY(), peaShooter, 1.5, gameinterfacepane, 100);
                                isPeashooterSeedClicked = false;
                                lawn.getPeashooterSeedView().setOpacity(0.75);
                                plantGrid[finalI][finalJ].getImage().setOpacity(1);
                                isPlant[finalI][finalJ] = true;
                                int currSunCount = Integer.parseInt((sunCount.getText()));
                                sunCount.setText(String.valueOf(currSunCount - 100));

                                Timeline peaShootInterval = new Timeline(new KeyFrame(Duration.seconds(((PeaShooter) plantGrid[finalI][finalJ]).getShootInterval()), event -> {
                                    try {
                                        if (!rowZombies.get(finalI).isEmpty()) {
                                            try {
                                                ((PeaShooter) plantGrid[finalI][finalJ]).shoot(rowPeas.get(finalI), rowZombies.get(finalI), timelines);
                                            }
                                            catch (ClassCastException c){

                                            }
                                        }
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                }));
                                peaShootInterval.setCycleCount(Timeline.INDEFINITE);
                                peaShootInterval.play();
                                ((PeaShooter) plantGrid[finalI][finalJ]).shootTimer = peaShootInterval;
                                timelines.add(peaShootInterval);
                                flag1 = false;
                                Timeline refreshtime = new Timeline(new KeyFrame(Duration.seconds(7), actionEvent -> {
                                    flag1=true;
                                }));
                                refreshtime.setCycleCount(1);
                                refreshtime.play();
                                timelines.add(refreshtime);
                            }
                        }
                    }
                    else if (isSunflowerSeedClicked && (Integer.parseInt((sunCount.getText()))>=50)) {
                        if (!isPlant[finalI][finalJ] && flag2==true) {

                            ImageView sunFlower = new ImageView(sunflower);
                            plantGrid[finalI][finalJ] = new Sunflower(plantGrid[finalI][finalJ].getX(), plantGrid[finalI][finalJ].getY(), sunFlower, 16, gameinterfacepane, 100);

                            isSunflowerSeedClicked = false;
                            lawn.getSunflowerSeedView().setOpacity(0.75);
                            plantGrid[finalI][finalJ].getImage().setOpacity(1);
                            isPlant[finalI][finalJ] = true;
                            AtomicInteger currSunCount = new AtomicInteger(Integer.parseInt((sunCount.getText())));
                            sunCount.setText(String.valueOf(currSunCount.get() - 50));
                            sunAmount = sunCount.getText();

                            Timeline sunAppearSunflower = new Timeline(new KeyFrame(Duration.seconds(((Sunflower) plantGrid[finalI][finalJ]).getSunProducingInterval()), actionEvent -> {

                                try {
                                    ((Sunflower) plantGrid[finalI][finalJ]).produceSun(plantGrid[finalI][finalJ].getX() + 60, plantGrid[finalI][finalJ].getY() + 20, sunCount);
                                } catch (SunCollectedException s) {
                                    currSunCount.set(Integer.parseInt((sunCount.getText())));
                                    sunCount.setText(String.valueOf(currSunCount.get() + 25));
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                catch (ClassCastException c){

                                }
                            }));
                            sunAppearSunflower.setCycleCount(Timeline.INDEFINITE);
                            sunAppearSunflower.play();
                            ((Sunflower)plantGrid[finalI][finalJ]).sunProduceTimer = sunAppearSunflower;
                            timelines.add(sunAppearSunflower);
                            flag2 = false;
                            Timeline refreshtime2 = new Timeline(new KeyFrame(Duration.seconds(7), actionEvent -> {
                                flag2=true;
                            }));
                            refreshtime2.setCycleCount(1);
                            refreshtime2.play();
                            timelines.add(refreshtime2);
                        }
                    }
                    else if (ischerryBombSeedClicked && (Integer.parseInt((sunCount.getText()))>=150)) {
                        if (!isPlant[finalI][finalJ] && flag3==true) {
                            ImageView cherryBomb = new ImageView(cherrybomb);
                            plantGrid[finalI][finalJ] = new Cherrybomb(plantGrid[finalI][finalJ].getX(), plantGrid[finalI][finalJ].getY(), cherryBomb, gameinterfacepane, 30);

                            ischerryBombSeedClicked = false;
                            lawn.getCherrybombSeedView().setOpacity(0.75);
                            plantGrid[finalI][finalJ].getImage().setOpacity(1);
                            isPlant[finalI][finalJ] = true;
                            int currSunCount = Integer.parseInt((sunCount.getText()));
                            sunCount.setText(String.valueOf(currSunCount - 150));
                            flag3 = false;
                            Timeline refreshtime3 = new Timeline(new KeyFrame(Duration.seconds(40), actionEvent -> {
                                flag3=true;
                            }));
                            refreshtime3.setCycleCount(1);
                            refreshtime3.play();
                            timelines.add(refreshtime3);
                        }
                    }
                    else if(isWallnutSeedClicked && (Integer.parseInt((sunCount.getText()))>=50)){
                        if(!isPlant[finalI][finalJ] && flag4==true) {
                            ImageView wallNut = new ImageView(wallnut);
                            plantGrid[finalI][finalJ] = new Wallnut(plantGrid[finalI][finalJ].getX(), plantGrid[finalI][finalJ].getY(), wallNut,  gameinterfacepane, 1000);
                            isWallnutSeedClicked=false;
                            lawn.getWallnutSeedView().setOpacity(0.75);
                            plantGrid[finalI][finalJ].getImage().setOpacity(1);
                            isPlant[finalI][finalJ] = true;
                            int currSunCount = Integer.parseInt((sunCount.getText()));
                            sunCount.setText(String.valueOf(currSunCount - 50));
                            flag4 = false;
                            Timeline refreshtime4 = new Timeline(new KeyFrame(Duration.seconds(25), actionEvent -> {
                                flag4=true;
                            }));
                            refreshtime4.setCycleCount(1);
                            refreshtime4.play();
                            timelines.add(refreshtime4);
                        }
                    }
                    else if (isPotatomineSeedClicked && (Integer.parseInt((sunCount.getText()))>=25)){
                        if(!isPlant[finalI][finalJ] && flag5==true) {
                            ImageView potatoMine = new ImageView(potatomine);
                            plantGrid[finalI][finalJ] = new Potatomine(plantGrid[finalI][finalJ].getX(), plantGrid[finalI][finalJ].getY(), potatoMine, gameinterfacepane, 20);
                            isPotatomineSeedClicked = false;
                            lawn.getPotatomineSeedView().setOpacity(0.75);
                            plantGrid[finalI][finalJ].getImage().setOpacity(1);
                            isPlant[finalI][finalJ] = true;
                            int currSunCount = Integer.parseInt((sunCount.getText()));
                            sunCount.setText(String.valueOf(currSunCount - 25));
                            flag5 = false;
                            Timeline refreshtime5 = new Timeline(new KeyFrame(Duration.seconds(25), actionEvent -> {
                                flag5=true;
                            }));
                            refreshtime5.setCycleCount(1);
                            refreshtime5.play();
                            timelines.add(refreshtime5);
                        }
                    }
//                    else if (isRepeaterSeedClicked && (Integer.parseInt((sunCount.getText()))>=200)){
//                        if(!isPlant[finalI][finalJ]) {
//                            ImageView potatoMine = new ImageView(potatomine);
//                            plantGrid[finalI][finalJ] = new Potatomine(plantGrid[finalI][finalJ].getX(), plantGrid[finalI][finalJ].getY(), potatoMine, gameinterfacepane, 20);
//                            isPotatomineSeedClicked = false;
//                            lawn.getPotatomineSeedView().setOpacity(0.75);
//                            plantGrid[finalI][finalJ].getImage().setOpacity(1);
//                            int currSunCount = Integer.parseInt((sunCount.getText()));
//                            sunCount.setText(String.valueOf(currSunCount - 200));
//                        }
//                    }
                });
            }
        }

        Timeline sunFallInterval = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
//                System.out.println("this is called every 3 seconds on UI thread");
            Random random = new Random();
            Sun sun = null;
            try {
                sun = new Sun(gameinterfacepane, random.nextInt(800),0);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Sun finalSun = sun;
            Timeline sunDisappear2 = new Timeline(new KeyFrame(Duration.seconds(14), actionEvent -> finalSun.getSunView().setVisible(false)));
            sunDisappear2.setCycleCount(1);
            sunDisappear2.play();
            timelines.add(sunDisappear2);
            Sun finalSun1 = sun;

            sun.getSunView().setOnMouseClicked(mouseEvent -> {
                finalSun1.getSunView().setVisible(false);
                int currSunCount = Integer.parseInt((sunCount.getText()));
                sunCount.setText(String.valueOf(currSunCount + 25));
                sunAmount = sunCount.getText();
            });
            sun.fall();
        }));
        sunFallInterval.setCycleCount(Timeline.INDEFINITE);
        sunFallInterval.play();
        timelines.add(sunFallInterval);

        for(int i=0;i<rowZombies.size();i++){
            for(int j=0;j<rowZombies.get(i).size();j++){
                rowZombies.get(i).get(j).primaryStage = primaryStage; rowZombies.get(i).get(j).gameinterfacepane = gameinterfacepane;
                if(rowZombies.get(i).get(j).getClass() == normalZombie.class){
                    rowZombies.get(i).get(j).zombieView = new ImageView(normalzombie);
                }
                else if(rowZombies.get(i).get(j).getClass() == coneHeadZombie.class){
                    rowZombies.get(i).get(j).zombieView = new ImageView(coneheadzombie);
                }
                else if(rowZombies.get(i).get(j).getClass() == bucketHeadZombie.class){
                    rowZombies.get(i).get(j).zombieView = new ImageView(bucketheadzombie);
                }
                else if(rowZombies.get(i).get(j).getClass() == flagZombie.class) {
                    rowZombies.get(i).get(j).zombieView = new ImageView(flagzombie);
                }
                rowZombies.get(i).get(j).timelines = timelines;
                rowZombies.get(i).get(j).getZombieView().setX(rowZombies.get(i).get(j).getX()) ;
                rowZombies.get(i).get(j).getZombieView().setY(rowZombies.get(i).get(j).getY());
                rowZombies.get(i).get(j).fakeImage = new Image(new FileInputStream("PvZ_PNGs/Lawn/fakeImage.jpg"));
                gameinterfacepane.getChildren().add(rowZombies.get(i).get(j).zombieView);
                rowZombies.get(i).get(j).move(plantGrid, isPlant, lawn.getLawnMowers()[i]);
            }
        }

        Timeline createZombies = new Timeline(new KeyFrame(Duration.seconds(20), actionEvent -> { //reduce the 10 second limit for higher levels
            wave_number++;
            Random random = new Random();
            int numofzombies = random.nextInt(3)+2;
            for(int i=0;i<numofzombies;i++) {
                int r = random.nextInt(5);
                System.out.println(level_number);
                int zombietype = random.nextInt(level_number);
                try {
                    if (zombietype == 0) {
                        rowZombies.get(r).add(new normalZombie(gameinterfacepane, r * 100, 10, r, plantGrid, rowZombies, isPlant, normalzombie, lawn.getLawnMowers()[r], timelines, level_number, primaryStage));
                    } else if (zombietype == 1) {
                        rowZombies.get(r).add(new coneHeadZombie(gameinterfacepane, r * 100, 28, r, plantGrid, rowZombies, isPlant, coneheadzombie, lawn.getLawnMowers()[r], timelines, level_number, primaryStage));
                    } else if (zombietype == 2) {
                        rowZombies.get(r).add(new bucketHeadZombie(gameinterfacepane, r * 100, 65, r, plantGrid, rowZombies, isPlant, bucketheadzombie, lawn.getLawnMowers()[r], timelines, level_number, primaryStage));
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }));
        createZombies.setCycleCount(4-wave_number);
        createZombies.play();
        timelines.add(createZombies);

        Timeline finalcreateZombies = new Timeline(new KeyFrame(Duration.seconds(110-20*wave_number), actionEvent -> { //reduce the 10 second limit for higher levels
            Random random = new Random();
            int numofzombies = random.nextInt(3)+5;
            Text FinalWave = new Text("Final wave of Zombies is Coming!!");
            FinalWave.setX(250); FinalWave.setY(170);
            FinalWave.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD, FontPosture.ITALIC,40));
            this.gameinterfacepane.getChildren().add(FinalWave);

            Timeline finalwavetimeline= new Timeline(new KeyFrame(Duration.seconds(3), actionEvent2 ->
            {
                FinalWave.setVisible(false);
            }));
            finalwavetimeline.setCycleCount(1);
            finalwavetimeline.play();
            timelines.add(finalwavetimeline);

            try {
                rowZombies.get(2).add(new flagZombie(gameinterfacepane, 200, 15, 2, plantGrid, rowZombies, isPlant, flagzombie, lawn.getLawnMowers()[2],timelines,level_number,primaryStage));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            for(int i=0;i<numofzombies;i++) {
                int r = random.nextInt(5);
                int zombietype = random.nextInt(level_number);
                try {
                    if (zombietype == 0) {
                        rowZombies.get(r).add(new normalZombie(gameinterfacepane, r * 100, 10, r, plantGrid, rowZombies, isPlant, normalzombie, lawn.getLawnMowers()[r], timelines, level_number, primaryStage));
                    } else if (zombietype == 1) {
                        rowZombies.get(r).add(new coneHeadZombie(gameinterfacepane, r * 100, 28, r, plantGrid, rowZombies, isPlant, coneheadzombie, lawn.getLawnMowers()[r], timelines, level_number, primaryStage));
                    } else if (zombietype == 2) {
                        rowZombies.get(r).add(new bucketHeadZombie(gameinterfacepane, r * 100, 65, r, plantGrid, rowZombies, isPlant, bucketheadzombie, lawn.getLawnMowers()[r], timelines, level_number, primaryStage));
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }));
        finalcreateZombies.setCycleCount(1);
        finalcreateZombies.play();
        timelines.add(finalcreateZombies);

        Timeline checkGameWinStart = new Timeline(new KeyFrame(Duration.seconds(112-20*wave_number), actionEvent -> {
            Timeline checkGameWin = new Timeline(new KeyFrame(Duration.millis(500), actionEvent1 -> {
                boolean flag=true;
                for (int i=0;i<rowZombies.size();i++)
                {
                    if (rowZombies.get(i).size()!=0)
                    {
                        flag=false;
                    }
                }
                if(flag){
                    try {
                        GameWonMenu gameWon = new GameWonMenu(gameinterfacepane, primaryStage, timelines, level_number + 1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            }));
            checkGameWin.setCycleCount(Timeline.INDEFINITE);
            checkGameWin.play();
            timelines.add(checkGameWin);
        }));
        checkGameWinStart.setCycleCount(1);
        checkGameWinStart.play();
        timelines.add(checkGameWinStart);


        Text pauseGame = new Text("Pause Game");
        pauseGame.setX(920); pauseGame.setY(18);
        pauseGame.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD, FontPosture.ITALIC,20));
        gameinterfacepane.getChildren().add(pauseGame);

        Button pauseGameButton = new Button();
        pauseGameButton.setLayoutX(900); pauseGameButton.setLayoutY(3);
        pauseGameButton.setPrefWidth(150); pauseGameButton.setPrefHeight(20);
        pauseGameButton.setOpacity(0)
        ;
        gameinterfacepane.getChildren().add(pauseGameButton);
        pauseGameButton.setOnMouseClicked(mouseEvent -> {
            try {
                System.out.println("yo");
                PauseMenu pauseMenu= new PauseMenu(gameinterfacepane,primaryStage, timelines, level_number, this);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        primaryStage.setScene(new Scene(gameinterfacepane, 1054, 612));
        primaryStage.show();

    }
}
