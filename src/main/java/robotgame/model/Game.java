package robotgame.model;

import robotgame.model.cellobject.CellObject;
import robotgame.model.cellobject.Key;
import robotgame.model.cellobject.Robot;
import robotgame.model.finishgamerule.FinishGameRulesHandler;
import robotgame.model.finishgamerule.FinishGameRulesHandlerFactory;

import java.awt.*;
import java.security.InvalidParameterException;

public class Game {

    private final HexagonField field;
    private final FinishGameRulesHandler finishGameRulesHandler;
    private final Robot robot;

    public Game(FinishGameRulesHandlerFactory finishGameRulesHandlerFactoryFactory, int fieldWidth, int fieldHeight, int toSpawnKeysCount){
        field = new HexagonField(fieldWidth, fieldHeight);
        this.finishGameRulesHandler = finishGameRulesHandlerFactoryFactory.create(field);
        int keysCount = Utils.rnd.nextInt(toSpawnKeysCount) + 1;

        for (int i = 0; i < keysCount; i++){
            spawnObjectInRandomPosition(new Key());
        }

        robot = new Robot(field, Color.ORANGE);
        spawnObjectInRandomPosition(robot);
    }

    public void moveRobot(HexagonDirection direction){
        if (finishGameRulesHandler.isGameOver()){
            return;
        }

        robot.move(direction);
        finishGameRulesHandler.updateGameState();
    }

    public boolean isGameOver(){
        return finishGameRulesHandler.isGameOver();
    }

    public boolean isPlayerWin(){
        return finishGameRulesHandler.isPlayerWin();
    }

    public HexagonField getField() {
        return field;
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
