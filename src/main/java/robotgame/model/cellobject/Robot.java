package robotgame.model.cellobject;

import robotgame.model.HexagonDirection;
import robotgame.model.HexagonField;
import robotgame.model.cell.Cell;
import robotgame.model.cell.ColoredCell;

import java.awt.*;

public class Robot implements CellObject {

    private final HexagonField field;
    private final Color footprintColor;
    private Point currentPosition;

    public Robot(HexagonField field, Color footprintColor){
        this.field = field;
        this.footprintColor = footprintColor;
    }

    public void move(HexagonDirection direction){
        Point oldPosition = new Point(currentPosition);
        Point deltaPosition = direction.toPoint(currentPosition.y % 2 == 1);
        currentPosition.translate(deltaPosition.x, deltaPosition.y);

        if (isPositionValidForMove(currentPosition)){
            Cell cell = field.getCell(currentPosition);

            // Color current cell in robot's footprint color
            if (cell instanceof ColoredCell coloredCell){
                coloredCell.setCurrentColor(footprintColor);
            }

            // If cell contains key robot will collect it
            CellObject objectInCell = cell.getContainedObject();
            if (objectInCell != null){
                field.despawnObject(objectInCell);
            }

            // Move robot through despawn and spawn in next position
            field.despawnObject(this);
            field.spawnObject(this, currentPosition);
        }
        else {
            currentPosition = oldPosition;
        }
    }

    public boolean isPositionValidForMove(Point point){
        try {
            Cell cell = field.getCell(point);
            return !(cell instanceof ColoredCell coloredCell) || !footprintColor.equals(coloredCell.getCurrentColor());
        }
        catch (ArrayIndexOutOfBoundsException e){
            return false;
        }
    }

    @Override
    public void onSpawned(Point position) { // TODO добавить вместо деспавна/спавна перенос без вызова onSpawn
        currentPosition = position;
        Cell cell = field.getCell(currentPosition);

        if (cell instanceof ColoredCell coloredCell){
            coloredCell.setCurrentColor(footprintColor);
        }
    }
}
