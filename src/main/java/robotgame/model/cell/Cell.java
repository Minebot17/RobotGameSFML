package robotgame.model.cell;

import robotgame.model.HexagonDirection;
import robotgame.model.HexagonField;
import robotgame.model.Position;
import robotgame.model.cellobject.CellObject;

public abstract class Cell {

    private HexagonField field;
    private Position position;
    private CellObject containedObject;

    public Cell(HexagonField field, Position position){
        this.field = field;
        this.position = position;
    }

    public Cell getNeighbor(HexagonDirection direction){
        return field.getCell(direction.toPosition(position.y % 2 == 1).add(position));
    }

    public void setContainedObject(CellObject mutualCellObject) {
        if (this.containedObject != null && mutualCellObject == null) {
            CellObject old = this.containedObject;
            this.containedObject = null;
            old.setCell(null);
        }
        else if (this.containedObject == null && mutualCellObject != null) {
            this.containedObject = mutualCellObject;
            this.containedObject.setCell(this);
        }
    }

    public CellObject getContainedObject() { return containedObject; }
}
