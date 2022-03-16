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

    public CellObject getContainedObject() {
        return containedObject;
    }

    public void setContainedObject(CellObject containedObject) {
        this.containedObject = containedObject;
    }

    public abstract boolean canContainsOnlyRobot();

    public Position getPosition() {
        return position;
    }
}
