package robotgame.model.pathfinding;

import robotgame.model.HexagonDirection;
import robotgame.model.HexagonField;
import robotgame.model.cell.Cell;
import robotgame.model.cellobject.Robot;

import java.util.ArrayList;
import java.util.List;

public class NeighborPathFinder implements PathFinder {

    private final HexagonField field;

    public NeighborPathFinder(HexagonField field) {
        this.field = field;
    }

    @Override
    public boolean isRobotCanReach(TargetFilter targetFilter) {
        Robot robot = (Robot) field.getSpawnedObjects().stream().filter(obj -> obj instanceof Robot).findFirst().orElse(null);

        List<Cell> checkedCells = new ArrayList<>();
        List<Cell> toCheckCells = new ArrayList<>();
        toCheckCells.add(robot.getCell());

        while (!toCheckCells.isEmpty()){
            Cell currentCell = toCheckCells.get(0);

            for (HexagonDirection direction : HexagonDirection.values()){
                Cell nextCell = currentCell.getNeighbor(direction);

                if (nextCell != null && robot.isCellValidForMove(nextCell) && !checkedCells.contains(nextCell)){
                    toCheckCells.add(nextCell);
                    checkedCells.add(nextCell);
                }
            }

            if (targetFilter.IsTargetCell(currentCell))
            {
                return true;
            }

            toCheckCells.remove(0);
        }

        return false;
    }
}
