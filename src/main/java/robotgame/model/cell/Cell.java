package robotgame.model.cell;

import robotgame.model.cellobject.CellObject;

public class Cell {

    private CellObject containedObject;

    public CellObject getContainedObject() {
        return containedObject;
    }

    public void setContainedObject(CellObject containedObject) {
        this.containedObject = containedObject;
    }
}
