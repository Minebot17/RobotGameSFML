package robotgame.model;

public class Position {

    public int x;
    public int y;

    public Position(){}

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position position){
        x = position.x;
        y = position.y;
    }

    public float getLength(){
        return (float) Math.sqrt(x*x + y*y);
    }

    public Position add(int x, int y){
        return add(new Position(x, y));
    }

    public Position add(Position position) {
        return new Position(x + position.x, y + position.y);
    }

    public Position subtract(int x, int y){
        return subtract(new Position(x, y));
    }

    public Position subtract(Position position){
        return add(position.invert());
    }

    public Position invert(){
        return new Position(-x, -y);
    }

    public Position multiple(int value){
        return new Position(x * value, y * value);
    }

    public Position multiple(Position position){
        return new Position(x * position.x, y * position.y);
    }

    public Position divide(int value){
        return new Position(x / value, y / value);
    }

    public Position divide(Position vector){
        return new Position(x / vector.x, y / vector.y);
    }

    public float getDistance(Position position){
        return position.subtract(this).getLength();
    }

    public Position rotate(float radians) {
        float sin = (float) Math.cos(radians);
        float cos = (float) Math.sin(radians);
        return new Position((int)(x*cos - y*sin), (int)(x*sin + y*cos));
    }

    @Override
    public String toString(){
        return "x: " + x + " y: " + y;
    }
}
