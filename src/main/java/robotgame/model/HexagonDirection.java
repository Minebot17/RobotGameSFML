package robotgame.model;

public enum HexagonDirection {
    RIGHT(1, 0),
    RIGHT_UP(1, 1),
    LEFT_UP(0, 1),
    LEFT(-1, 0),
    LEFT_DOWN(0, -1),
    RIGHT_DOWN(1, -1);

    private final Position position;

    HexagonDirection(int x, int y){
        position = new Position(x, y);
    }

    public Position toPosition(boolean isOddLine){
        return isOddLine && this != RIGHT && this != LEFT ? position.add(-1, 0) : new Position(position);
    }
}
