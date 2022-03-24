package robotgame.model;

import robotgame.model.pathfinding.ExitTargetFilter;
import robotgame.model.pathfinding.KeyTargetFilter;

import java.util.Random;

public class Utils {

    public static Random rnd = new Random();
    public static KeyTargetFilter KEY_TARGET_FILTER = new KeyTargetFilter();
    public static ExitTargetFilter EXIT_TARGET_FILTER = new ExitTargetFilter();

    public static Position getRandomPoint(int xMax, int yMax){
        return new Position(rnd.nextInt(xMax), rnd.nextInt(yMax));
    }
}
