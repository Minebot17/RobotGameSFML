package robotgame.model.finishgamerule.rules;

import robotgame.model.HexagonField;
import robotgame.model.Position;
import robotgame.model.cellobject.CellObject;
import robotgame.model.cellobject.Robot;
import robotgame.model.finishgamerule.BaseGameRule;

import java.awt.*;
import java.util.List;

public class RobotStepsFinishGameRule extends BaseGameRule {

    private final RuleMode ruleMode;
    private final int value;
    private Position lastRobotPosition = null;
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

        if (!robot.getPosition().equals(lastRobotPosition)){
            lastRobotPosition = new Position(robot.getPosition());
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

    @Override
    public String toString() {
        return "количество шагов должно быть "
                + (ruleMode == RuleMode.LESS ? "меньше" : ruleMode == RuleMode.EQUALS ? "равно" : "больше")
                + " " + value;
    }

    public enum RuleMode {
        LESS, EQUALS, MORE
    }
}
