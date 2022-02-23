package robotgame.model;

import java.awt.*;

public enum HexagonDirection {
    RIGHT(1, 0),
    RIGHT_UP(1, 1),
    LEFT_UP(0, 1),
    LEFT(-1, 0),
    LEFT_DOWN(0, -1),
    RIGHT_DOWN(1, -1);

    private final Point point;

    HexagonDirection(int x, int y){
        point = new Point(x, y);
    }

    public Point toPoint(boolean isOddLine){
        Point resultPoint = new Point(point);

        if (isOddLine && this != RIGHT && this != LEFT){
            resultPoint.translate(-1, 0);
        }

        return resultPoint;
    }
}
