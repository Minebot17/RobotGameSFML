package robotgame.model;

import java.awt.*;
import java.util.Random;

public class Utils {

    public static Random rnd = new Random();

    public static Point getRandomPoint(int xMax, int yMax){
        return new Point(rnd.nextInt(xMax), rnd.nextInt(yMax));
    }
}
