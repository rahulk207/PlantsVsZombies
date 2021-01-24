package sample;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Cherrybomb extends Bombplant {
    public transient Pane gameinterfacepane;
    public Cherrybomb(double x, double y, ImageView plant, Pane gameinterfacepane, double health) {
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
                double tempx=zombies.get(i).get(j).getX();
                double tempy=zombies.get(i).get(j).getY();
                if(tempx>this.getX()-180 && tempx<this.getX()+90)
                {
                    if (tempy>this.getY()-150 && tempy<this.getY()+90)
                    {
                        zombies.get(i).get(j).getZombieView().setVisible(false);
                        zombies.get(i).get(j).zombietimeline.stop();
                        gameinterfacepane.getChildren().remove(zombies.get(i).get(j).getZombieView());
                        zombies.get(i).remove(zombies.get(i).get(j));
                    }
                }

            }
        }
    }

}
