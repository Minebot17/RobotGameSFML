package robotgame.model.finishgamerule.rules;

import robotgame.model.HexagonField;
import robotgame.model.Utils;
import robotgame.model.cell.ExitCell;
import robotgame.model.cellobject.Robot;
import robotgame.model.finishgamerule.BaseGameRule;
import robotgame.model.pathfinding.PathFinder;

public class ExitFinishGameRule extends BaseGameRule {

    private final PathFinder pathFinder;

    public ExitFinishGameRule(HexagonField field, PathFinder pathFinder) {
        super(field);
        this.pathFinder = pathFinder;
    }

    @Override
    protected boolean isComplete() {
        Robot robot = (Robot) field.getSpawnedObjects().stream().filter(obj -> obj instanceof Robot).findFirst().orElse(null);
        return robot.getCell() instanceof ExitCell;
    }

    @Override
    protected boolean isFail() {
        return !pathFinder.isRobotCanReach(Utils.EXIT_TARGET_FILTER);
    }

    @Override
    public String toString() {
        return "достигнуть выхода (метка ET)";
    }
}
