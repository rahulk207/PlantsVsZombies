package sample;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Potatomine extends Bombplant {
    transient private Pane gameinterfacepane;

    public Potatomine(double x, double y, ImageView plant, Pane gameinterfacepane, double health) {
        super(x, y, plant, gameinterfacepane, health);
        this.gameinterfacepane=gameinterfacepane;
    }

    @Override
    public void explode(ArrayList<ArrayList<Zombie>> zombies, Zombie zombie)
    {
        for(int i=0;i<5;i++)
        {
            for(int j=0;j<zombies.get(i).size();j++)
            {
                if (zombies.get(i).get(j)==zombie)
                {
                    zombies.get(i).get(j).getZombieView().setVisible(false);
                    zombies.get(i).get(j).zombietimeline.stop();
                    gameinterfacepane.getChildren().remove(zombies.get(i).get(j).getZombieView());
                    zombies.get(i).remove(zombies.get(i).get(j));
                    zombie=null;
                }
            }
        }
    }
}
