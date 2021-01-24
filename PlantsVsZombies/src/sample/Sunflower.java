package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;

public class Sunflower extends SunProducer {

    transient Timeline sunProduceTimer;
    public Sunflower(double x, double y, ImageView plant, int sunProducingInterval, Pane gameinterfacePane, double health) {
        super(x, y, plant, sunProducingInterval, gameinterfacePane, health);
    }

    @Override
    public void produceSun(double x, double y, Text sunCount) throws FileNotFoundException {
        Sun sun = new Sun(getGameinterfacepane(), x, y);

        Timeline sunDisappear1 = new Timeline(new KeyFrame(Duration.seconds(7), actionEvent1 -> sun.getSunView().setVisible(false)));
        sunDisappear1.setCycleCount(1);
        sunDisappear1.play();

        sun.getSunView().setOnMouseClicked(mouseEvent1 -> {
            sun.getSunView().setVisible(false);
            getGameinterfacepane().getChildren().remove(sun);
            int currSunCount = Integer.parseInt((sunCount.getText()));
            sunCount.setText(String.valueOf(currSunCount + 25));

        });
    }
}
