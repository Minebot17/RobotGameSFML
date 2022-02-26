package robotgame.model.cell;

import robotgame.model.cellobject.CellObject;

public abstract class Cell {

    private CellObject containedObject;

    public CellObject getContainedObject() {
        return containedObject;
    }

    public void setContainedObject(CellObject containedObject) {
        this.containedObject = containedObject;
    }

    public abstract boolean canContainsOnlyRobot();
}
