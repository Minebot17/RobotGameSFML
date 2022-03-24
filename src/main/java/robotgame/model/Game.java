package robotgame.model;

import robotgame.model.cellobject.Robot;
import robotgame.model.fieldgeneration.FieldFactory;
import robotgame.model.finishgamerule.FinishGameRulesHandler;
import robotgame.model.finishgamerule.FinishGameRulesHandlerFactory;

public class Game {

    private final HexagonField field;
    private final FinishGameRulesHandler finishGameRulesHandler;
    private final Robot robot;

    public Game(FinishGameRulesHandlerFactory finishGameRulesHandlerFactoryFactory, FieldFactory fieldFactory){
        field = fieldFactory.create();
        this.finishGameRulesHandler = finishGameRulesHandlerFactoryFactory.create(field);
        robot = (Robot) field.getSpawnedObjects().stream().filter(obj -> obj instanceof Robot).findFirst().get();
    }

    public void doStep(HexagonDirection direction){
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
}
