package robotgame.model.finishgamerule;

import robotgame.model.HexagonDirection;
import robotgame.model.HexagonField;
import robotgame.model.cell.Cell;
import robotgame.model.cell.ExitCell;
import robotgame.model.cellobject.CellObject;
import robotgame.model.cellobject.Key;
import robotgame.model.cellobject.Robot;

import java.awt.*;

public class ExitWithKeysFinishGameRule implements FinishGameRule {

    private boolean isGameOver;
    private boolean isPlayerWin;

    @Override
    public void handleGameState(HexagonField field) {
        Point robotPosition = null;
        Robot robot = null;
        int keysCount = 0;

        for (int x = 0; x < field.getWidth(); x++){
            for (int y = 0; y < field.getHeight(); y++){
                Cell cell = field.getCell(new Point(x, y));
                CellObject objectInCell = cell.getContainedObject();

                if (objectInCell instanceof Key){
                    keysCount++;
                }
                else if (objectInCell instanceof Robot r){
                    robotPosition = new Point(x, y);
                    robot = r;
                }
            }
        }

        if (field.getCell(robotPosition) instanceof ExitCell && keysCount == 0){
            isGameOver = true;
            isPlayerWin = true;
        }
        else {
            for (HexagonDirection direction : HexagonDirection.values()){ // TODO change to path find to exit
                Point deltaPosition = direction.toPoint(robotPosition.y % 2 == 1);
                Point neighborPosition = new Point(robotPosition);
                neighborPosition.translate(deltaPosition.x, deltaPosition.y);

                if (robot.isPositionValidForMove(neighborPosition)){
                    return;
                }
            }

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
