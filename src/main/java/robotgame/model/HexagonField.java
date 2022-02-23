package robotgame.model;

import robotgame.model.cell.Cell;
import robotgame.model.cellobject.CellObject;

import java.awt.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class HexagonField {

    private final Cell[][] cells;
    private final List<CellObject> spawnedObjects = new ArrayList<>();

    public HexagonField(int width, int height){
        if (width < 1 || height < 1){
            throw new InvalidParameterException();
        }

        cells = new Cell[height][width];
    }

    public void spawnObject(CellObject cellObject, Point position){
        if (cells.length >= position.x || cells[0].length >= position.y){
            throw new ArrayIndexOutOfBoundsException(position.toString());
        }

        if (spawnedObjects.contains(cellObject)){
            throw new InvalidParameterException();
        }

        spawnedObjects.add(cellObject);
        cells[position.x][position.y].setContainedObject(cellObject);
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
}
