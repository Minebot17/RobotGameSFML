package robotgame.model.cellobject;

import java.awt.*;

public class CellObject {

    protected Point currentPosition;

    public void onSpawned(Point position){
        currentPosition = position;
    }

    public Point getPosition(){
        return currentPosition;
    }
}
