package robotgame.model.cellobject;

import robotgame.model.HexagonDirection;
import robotgame.model.HexagonField;
import robotgame.model.Position;
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
        Position oldPosition = currentPosition;
        currentPosition = currentPosition.add(direction.toPosition(currentPosition.y % 2 == 1));

        if (isPositionValidForMove(currentPosition)){
            Cell cell = field.getCell(currentPosition);

            CellObject objectInCell = cell.getContainedObject();
            if (objectInCell != null){
                field.despawnObject(objectInCell);
            }

            field.despawnObject(this);
            field.spawnObject(this, currentPosition);
        }
        else {
            currentPosition = oldPosition;
        }
    }

    public boolean isPositionValidForMove(Position position){
        try {
            Cell cell = field.getCell(position);
            return !(cell instanceof ColoredCell) || !footprintColor.equals(((ColoredCell)cell).getCurrentColor());
        }
        catch (ArrayIndexOutOfBoundsException e){
            return false;
        }
    }

    @Override
    public void onSpawned(Position position) {
        super.onSpawned(position);
        Cell cell = field.getCell(currentPosition);

        if (cell instanceof ColoredCell){
            ((ColoredCell)cell).setCurrentColor(footprintColor);
        }
    }
}
