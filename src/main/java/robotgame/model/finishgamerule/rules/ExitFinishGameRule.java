package robotgame.model.finishgamerule.rules;

import robotgame.model.HexagonField;
import robotgame.model.Utils;
import robotgame.model.cell.ExitCell;
import robotgame.model.cellobject.CellObject;
import robotgame.model.cellobject.Robot;
import robotgame.model.finishgamerule.BaseGameRule;

import java.util.List;

public class ExitFinishGameRule extends BaseGameRule {

    public ExitFinishGameRule(HexagonField field) {
        super(field);
    }

    @Override
    protected boolean isComplete() {
        Robot robot = (Robot) field.getSpawnedObjects().stream().filter(obj -> obj instanceof Robot).findFirst().orElse(null);
        return robot.getCell() instanceof ExitCell;
    }

    @Override
    protected boolean isFail() {
        return !field.isRobotCanReach(true);
    }

    @Override
    public String toString() {
        return "достигнуть выхода (метка ET)";
    }
}
