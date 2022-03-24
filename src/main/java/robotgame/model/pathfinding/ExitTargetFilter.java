package robotgame.model.pathfinding;

import robotgame.model.cell.Cell;
import robotgame.model.cell.ExitCell;

public class ExitTargetFilter implements TargetFilter {

    @Override
    public boolean IsTargetCell(Cell cell) {
        return cell instanceof ExitCell;
    }
}
