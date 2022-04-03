package robotgame.model.fieldgeneration;

import robotgame.model.HexagonField;
import robotgame.model.Position;
import robotgame.model.cellobject.Key;
import robotgame.model.cellobject.Robot;

import java.awt.*;

// робот всегда по центру, левее на 2 клетки ключ, правее на 2 клетки выход
public class TestsFieldFactory implements FieldFactory {

    private final int fieldWidth;
    private final int fieldHeight;

    public TestsFieldFactory(int fieldWidth, int fieldHeight){
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
    }

    @Override
    public HexagonField create() {
        Position center = new Position(fieldWidth / 2, fieldHeight / 2);
        HexagonField field = new HexagonField(fieldWidth, fieldHeight, center.add(2, 0));

        field.spawnObject(new Robot(field, Color.ORANGE), field.getCell(center));
        field.spawnObject(new Key(), field.getCell(center.add(-2, 0)));

        return field;
    }
}
