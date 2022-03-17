package robotgame.model.cellobject;

import robotgame.model.cell.Cell;

public class CellObject {

    private Cell currentCell;

    public void setCell(Cell mutualCell) {
        if (this.currentCell != null && mutualCell == null) {
            Cell old = this.currentCell;
            this.currentCell = null;
            old.setContainedObject(null);
        }
        else if (this.currentCell == null && mutualCell != null) {
            this.currentCell = mutualCell;
            this.currentCell.setContainedObject(this);
        }
    }

    public Cell getCell() { return currentCell; }

}
