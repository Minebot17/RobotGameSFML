package robotgame.model.cell;

import java.awt.*;

public class ColoredCell extends Cell {

    private Color currentColor = Color.white;

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
