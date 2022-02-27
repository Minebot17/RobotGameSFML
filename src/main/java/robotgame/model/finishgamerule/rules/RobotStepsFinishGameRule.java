package robotgame.model.finishgamerule.rules;

import robotgame.model.HexagonField;
import robotgame.model.cellobject.CellObject;
import robotgame.model.cellobject.Robot;
import robotgame.model.finishgamerule.BaseGameRule;

import java.awt.*;
import java.util.List;

public class RobotStepsFinishGameRule extends BaseGameRule {

    private final RuleMode ruleMode;
    private final int value;
    private Point lastRobotPosition = null;
    private int stepsCount;

    public RobotStepsFinishGameRule(HexagonField field, RuleMode ruleMode, int value) {
        super(field);

        this.ruleMode = ruleMode;
        this.value = value;
    }

    @Override
    public void updateGameState() {
        List<CellObject> spawnedObjects = field.getSpawnedObjects();
        Robot robot = null;

        for (CellObject cellObject : spawnedObjects){
            if (cellObject instanceof Robot){
                robot = (Robot) cellObject;
                break;
            }
        }

        if (lastRobotPosition != robot.getPosition()){
            lastRobotPosition = robot.getPosition();
            stepsCount++;
        }

        super.updateGameState();
    }

    @Override
    protected boolean isComplete() {
        switch (ruleMode){
            case LESS:
                return stepsCount < value;
            case EQUALS:
                return stepsCount == value;
            case MORE:
                return stepsCount > value;
            default:
                return false;
        }
    }

    @Override
    protected boolean isFail() {
        switch (ruleMode){
            case LESS:
                return !isComplete();
            case EQUALS:
                return stepsCount > value;
            case MORE:
            default:
                return false;
        }
    }

    public enum RuleMode {
        LESS, EQUALS, MORE
    }
}
