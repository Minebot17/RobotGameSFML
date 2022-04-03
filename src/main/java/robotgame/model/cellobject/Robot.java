package robotgame.model.cellobject;

import robotgame.model.HexagonDirection;
import robotgame.model.HexagonField;
import robotgame.model.cell.Cell;
import robotgame.model.cell.ColoredCell;

import java.awt.*;

public class Robot extends CellObject {

    private final HexagonField field;
    private final Color footprintColor;

    public Robot(HexagonField field, Color footprintColor){
        this.field = field;
        this.footprintColor = footprintColor;
    }

    public void move(HexagonDirection direction){
        Cell targetCell = getCell().getNeighbor(direction);

        if (isCellValidForMove(targetCell)){

            CellObject objectInCell = targetCell.getContainedObject();
            if (objectInCell != null){
                field.despawnObject(objectInCell);
            }

            field.despawnObject(this);
            field.spawnObject(this, targetCell);
        }
    }

    public boolean isCellValidForMove(Cell cell){
        return cell != null
                && (!(cell instanceof ColoredCell)
                    || !footprintColor.equals(((ColoredCell)cell).getCurrentFootprint()));
    }

    @Override
    public void setCell(Cell cell) {
        super.setCell(cell);

        if (cell instanceof ColoredCell){
            ((ColoredCell)cell).setCurrentFootprint(footprintColor);
        }
    }

    public Color getFootprintColor() {
        return footprintColor;
    }
}
