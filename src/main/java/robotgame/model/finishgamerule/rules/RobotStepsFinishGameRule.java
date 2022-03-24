package robotgame.model.finishgamerule.rules;

import robotgame.model.HexagonField;
import robotgame.model.cell.Cell;
import robotgame.model.cellobject.Robot;
import robotgame.model.finishgamerule.BaseGameRule;

public class RobotStepsFinishGameRule extends BaseGameRule {

    private final RuleMode ruleMode;
    private final int value;
    private Cell lastRobotCell = null;
    private int stepsCount;

    public RobotStepsFinishGameRule(HexagonField field, RuleMode ruleMode, int value) {
        super(field);

        this.ruleMode = ruleMode;
        this.value = value;
    }

    @Override
    public void updateGameState() {
        Robot robot = (Robot) field.getSpawnedObjects().stream().filter(obj -> obj instanceof Robot).findFirst().orElse(null);

        if (!robot.getCell().equals(lastRobotCell)){
            lastRobotCell = robot.getCell();
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
