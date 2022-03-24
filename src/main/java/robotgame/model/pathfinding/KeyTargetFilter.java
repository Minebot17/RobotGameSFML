package robotgame.model.pathfinding;

import robotgame.model.cell.Cell;
import robotgame.model.cellobject.Key;

public class KeyTargetFilter implements TargetFilter {

    @Override
    public boolean IsTargetCell(Cell cell) {
        return cell.getContainedObject() instanceof Key;
    }
}
