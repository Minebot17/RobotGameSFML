package robotgame.model.cell;

import robotgame.model.HexagonField;
import robotgame.model.Position;

import java.awt.*;

public class ColoredCell extends Cell {

    private Color currentColor = Color.white;

    public ColoredCell(HexagonField field, Position currentPosition) {
        super(field, currentPosition);
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    @Override
    public boolean canContainsOnlyRobot() {
        return true;
    }
}
