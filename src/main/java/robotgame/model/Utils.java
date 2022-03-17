package robotgame.model;

import java.util.Random;

public class Utils {

    public static Random rnd = new Random();

    public static Position getRandomPoint(int xMax, int yMax){
        return new Position(rnd.nextInt(xMax), rnd.nextInt(yMax));
    }
}
