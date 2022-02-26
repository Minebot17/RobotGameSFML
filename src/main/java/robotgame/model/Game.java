package robotgame.model;

import robotgame.model.cellobject.CellObject;
import robotgame.model.cellobject.Key;
import robotgame.model.cellobject.Robot;
import robotgame.model.finishgamerule.FinishGameRule;

import java.awt.*;
import java.security.InvalidParameterException;

public class Game {

    private final HexagonField field;
    private final FinishGameRule finishGameRule;
    private final Robot robot;

    public Game(FinishGameRule finishGameRule, int fieldWidth, int fieldHeight, int toSpawnKeysCount){
        this.finishGameRule = finishGameRule;

        field = new HexagonField(fieldWidth, fieldHeight);
        int keysCount = Utils.rnd.nextInt(toSpawnKeysCount) + 1;

        for (int i = 0; i < keysCount; i++){
            spawnObjectInRandomPosition(new Key());
        }

        robot = new Robot(field, Color.ORANGE);
        spawnObjectInRandomPosition(robot);
    }

    public void MoveRobot(HexagonDirection direction){
        if (finishGameRule.isGameOver()){
            return;
        }

        robot.move(direction);
        finishGameRule.handleGameState(field);
    }

    public boolean isGameOver(){
        return finishGameRule.isGameOver();
    }

    public boolean isPlayerWin(){
        return finishGameRule.isPlayerWin();
    }

    private void spawnObjectInRandomPosition(CellObject cellObject){
        do {
            try {
                Point position = Utils.getRandomPoint(field.getWidth(), field.getHeight());
                field.spawnObject(cellObject, position);
                return;
            }
            catch (InvalidParameterException ignored){}
        }
        while (true);
    }
}
