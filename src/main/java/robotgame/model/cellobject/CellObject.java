package robotgame.model.cellobject;

import robotgame.model.Position;

import java.awt.*;

public class CellObject {

    protected Position currentPosition;

    public void onSpawned(Position position){
        currentPosition = position;
    }

    public Position getPosition(){
        return currentPosition;
    }
}
