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
        List<CellObject> spawnedObjects = field.getSpawnedObjects();
        Robot robot = null;

        for (CellObject cellObject : spawnedObjects){
            if (cellObject instanceof Robot){
                robot = (Robot) cellObject;
                break;
            }
        }

        return field.getCell(robot.getPosition()) instanceof ExitCell;
    }

    @Override
    protected boolean isFail() {
        return !Utils.isRobotCanReach(field, true);
    }

    @Override
    public String toString() {
        return "достигнуть выхода (метка ET)";
    }
}
