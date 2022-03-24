package robotgame.model.pathfinding;

import robotgame.model.cell.Cell;

public interface TargetFilter {

    boolean IsTargetCell(Cell cell);
}
