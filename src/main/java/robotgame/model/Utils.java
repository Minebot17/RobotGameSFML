package robotgame.model;

import robotgame.model.cell.Cell;
import robotgame.model.cell.ExitCell;
import robotgame.model.cellobject.CellObject;
import robotgame.model.cellobject.Key;
import robotgame.model.cellobject.Robot;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {

    public static Random rnd = new Random();

    public static Point getRandomPoint(int xMax, int yMax){
        return new Point(rnd.nextInt(xMax), rnd.nextInt(yMax));
    }

    public static boolean isRobotCanReach(HexagonField field, boolean exit){
        Point robotPosition = null;
        robotgame.model.cellobject.Robot robot = null;

        for (int x = 0; x < field.getWidth(); x++){
            for (int y = 0; y < field.getHeight(); y++){
                Cell cell = field.getCell(new Point(x, y));
                CellObject objectInCell = cell.getContainedObject();

                if (objectInCell instanceof Robot r){
                    robotPosition = new Point(x, y);
                    robot = r;
                }
            }
        }

        java.util.List<Point> checkedPositions = new ArrayList<>();
        List<Point> toCheckPositions = new ArrayList<>();
        toCheckPositions.add(robotPosition);

        while (!toCheckPositions.isEmpty()){
            Point currentPosition = toCheckPositions.get(0);

            for (HexagonDirection direction : HexagonDirection.values()){
                Point deltaPosition = direction.toPoint(currentPosition.y % 2 == 1);
                Point nextPosition = new Point(currentPosition);
                nextPosition.translate(deltaPosition.x, deltaPosition.y);

                if (robot.isPositionValidForMove(nextPosition) && !checkedPositions.contains(nextPosition)){
                    toCheckPositions.add(nextPosition);
                    checkedPositions.add(nextPosition);
                }
            }

            if (exit && field.getCell(currentPosition) instanceof ExitCell){
                return true;
            }

            if (!exit && field.getCell(currentPosition).getContainedObject() instanceof Key){
                return true;
            }

            toCheckPositions.remove(0);
        }

        return false;
    }
}
