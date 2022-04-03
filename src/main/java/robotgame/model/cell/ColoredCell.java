package robotgame.model.cell;

import robotgame.model.HexagonField;
import robotgame.model.Position;

import java.awt.*;

public class ColoredCell extends Cell {

    public static final Color defaultFootprint = Color.white;
    private Color currentFootprint = defaultFootprint;

    public ColoredCell(HexagonField field, Position currentPosition) {
        super(field, currentPosition);
    }

    public Color getCurrentFootprint() {
        return currentFootprint;
    }

    public void setCurrentFootprint(Color currentFootprint) {
        this.currentFootprint = currentFootprint;
    }
}
