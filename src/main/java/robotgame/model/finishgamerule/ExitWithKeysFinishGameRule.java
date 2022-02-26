package robotgame.model.finishgamerule;

import robotgame.model.HexagonDirection;
import robotgame.model.HexagonField;
import robotgame.model.PathChecker;
import robotgame.model.cell.Cell;
import robotgame.model.cell.ExitCell;
import robotgame.model.cellobject.CellObject;
import robotgame.model.cellobject.Key;
import robotgame.model.cellobject.Robot;

import java.awt.*;
import java.util.List;

public class ExitWithKeysFinishGameRule implements FinishGameRule {

    private boolean isGameOver;
    private boolean isPlayerWin;

    @Override
    public void handleGameState(HexagonField field) {
        List<CellObject> spawnedObjects = field.getSpawnedObjects();

        Robot robot = null;
        int keysCount = 0;
        for (CellObject cellObject : spawnedObjects){
            if (cellObject instanceof Key){
                keysCount++;
            }
            else if (cellObject instanceof Robot){
                robot = (Robot) cellObject;
            }
        }

        if (field.getCell(robot.getPosition()) instanceof ExitCell && keysCount == 0){
            isGameOver = true;
            isPlayerWin = true;
        }
        else if (!new PathChecker(field).IsRobotCanReachExit()) {
            isGameOver = true;
            isPlayerWin = false;
        }
    }

    @Override
    public boolean isGameOver() {
        return isGameOver;
    }

    @Override
    public boolean isPlayerWin() {
        return isPlayerWin;
    }
}
