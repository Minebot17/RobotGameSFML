package robotgame.model.fieldgeneration;

import robotgame.model.HexagonField;
import robotgame.model.Position;
import robotgame.model.Utils;
import robotgame.model.cellobject.CellObject;
import robotgame.model.cellobject.Key;
import robotgame.model.cellobject.Robot;

import java.awt.*;
import java.security.InvalidParameterException;

public class RandomPositionsFieldFactory implements FieldFactory {

    private int fieldWidth;
    private int fieldHeight;
    private int maxKeysCount;

    public RandomPositionsFieldFactory(int fieldWidth, int fieldHeight, int maxKeysCount){
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.maxKeysCount = maxKeysCount;
    }

    @Override
    public HexagonField create() {
        HexagonField field = new HexagonField(fieldWidth, fieldHeight);
        int keysCount = Utils.rnd.nextInt(maxKeysCount) + 1;

        for (int i = 0; i < keysCount; i++){
            spawnObjectInRandomPosition(field, new Key());
        }

        spawnObjectInRandomPosition(field, new Robot(field, Color.ORANGE));
        return field;
    }

    private void spawnObjectInRandomPosition(HexagonField field, CellObject cellObject){
        do {
            try {
                Position position = Utils.getRandomPoint(field.getWidth(), field.getHeight());
                field.spawnObject(cellObject, field.getCell(position));
                return;
            }
            catch (InvalidParameterException ignored){}
        }
        while (true);
    }
}
