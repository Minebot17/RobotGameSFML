package robotgame.model;

import robotgame.model.cell.Cell;
import robotgame.model.cell.ColoredCell;
import robotgame.model.cell.ExitCell;
import robotgame.model.cellobject.CellObject;
import robotgame.model.cellobject.Robot;

import java.awt.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class HexagonField {

    private final Cell[][] cells;
    private final List<CellObject> spawnedObjects = new ArrayList<>();
    private final int width;
    private final int height;

    public HexagonField(int width, int height){
        if (width < 1 || height < 1){
            throw new InvalidParameterException();
        }

        this.width = width;
        this.height = height;

        cells = new Cell[height][width];
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                cells[x][y] = new ColoredCell();
            }
        }

        Point exitPosition = Utils.getRandomPoint(width, height);
        cells[exitPosition.x][exitPosition.y] = new ExitCell();
    }

    public void spawnObject(CellObject cellObject, Point position){
        Cell currentCell = cells[position.x][position.y];
        if (spawnedObjects.contains(cellObject)
                || currentCell.getContainedObject() != null
                || (!(cellObject instanceof Robot) && !currentCell.canContainsOnlyRobot())){
            throw new InvalidParameterException();
        }

        spawnedObjects.add(cellObject);
        cells[position.x][position.y].setContainedObject(cellObject);
        cellObject.onSpawned(position);
    }

    public void despawnObject(CellObject cellObject){
        if (!spawnedObjects.contains(cellObject)){
            return;
        }

        for (int x = 0; x < cells.length; x++){
            for (int y = 0; y < cells[0].length; y++){
                if (cellObject == cells[x][y].getContainedObject()){
                    cells[x][y].setContainedObject(null);
                }
            }
        }

        spawnedObjects.remove(cellObject);
    }

    public Cell getCell(Point position){
        return cells[position.x][position.y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<CellObject> getSpawnedObjects() {
        return spawnedObjects;
    }
}
