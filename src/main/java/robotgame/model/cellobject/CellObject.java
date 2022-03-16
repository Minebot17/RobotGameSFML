package robotgame.model.cellobject;

import robotgame.model.cell.Cell;

public class CellObject {

    protected Cell currentCell;

    public void onSpawned(Cell cell){
        currentCell = cell;
    }

    public Cell getCurrentCell(){
        return currentCell;
    }
}
