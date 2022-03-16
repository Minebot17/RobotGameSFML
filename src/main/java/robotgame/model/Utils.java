package robotgame.model;

import robotgame.model.cell.Cell;
import robotgame.model.cell.ExitCell;
import robotgame.model.cellobject.Key;
import robotgame.model.cellobject.Robot;
import robotgame.model.finishgamerule.FinishGameRulesHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {

    public static Random rnd = new Random();

    public static Position getRandomPoint(int xMax, int yMax){
        return new Position(rnd.nextInt(xMax), rnd.nextInt(yMax));
    }

    public static boolean isRobotCanReach(HexagonField field, boolean exit){
        Robot robot = (Robot) field.getSpawnedObjects().stream().filter(obj -> obj instanceof Robot).findFirst().orElse(null);

        List<Cell> checkedCells = new ArrayList<>();
        List<Cell> toCheckCells = new ArrayList<>();
        toCheckCells.add(robot.getCurrentCell());

        while (!toCheckCells.isEmpty()){
            Cell currentCell = toCheckCells.get(0);

            for (HexagonDirection direction : HexagonDirection.values()){
                Cell nextCell = currentCell.getNeighbor(direction);

                if (nextCell != null && robot.isCellValidForMove(nextCell) && !checkedCells.contains(nextCell)){
                    toCheckCells.add(nextCell);
                    checkedCells.add(nextCell);
                }
            }

            if (exit && currentCell instanceof ExitCell){
                return true;
            }

            if (!exit && currentCell.getContainedObject() instanceof Key){
                return true;
            }

            toCheckCells.remove(0);
        }

        return false;
    }

    public static String toString(List<FinishGameRulesHandler.RuleParameters> gameRulesParameters) {
        StringBuilder result = new StringBuilder();
        for (FinishGameRulesHandler.RuleParameters parameters : gameRulesParameters) {
            if (parameters.isIndependent){
                continue;
            }

            if (result.length() != 0) {
                result.append(" И ");
            }

            if (parameters.completeConditionsIsNegative){
                result.append("НЕ ");
            }

            result.append(parameters.toStringRule());
        }

        for (FinishGameRulesHandler.RuleParameters parameters : gameRulesParameters) {
            if (!parameters.isIndependent) {
                continue;
            }

            if (result.length() != 0) {
                result.append(" ИЛИ ");
            }

            if (parameters.completeConditionsIsNegative){
                result.append("НЕ ");
            }

            result.append(parameters.toStringRule());
        }

        return result.toString();
    }
}
